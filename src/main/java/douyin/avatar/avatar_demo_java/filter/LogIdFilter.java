package douyin.avatar.avatar_demo_java.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebFilter(filterName = "LogIdFilter", urlPatterns = "/*", asyncSupported = true)
public class LogIdFilter implements Filter, AsyncListener {

    private static final String LOG_ID_KEY = "logId";

    private final AtomicReference<String> currentLogId = new AtomicReference<>();


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String logId = request.getHeader("X-TT-LOGID");
        currentLogId.set(logId);
        MDC.put(LOG_ID_KEY, logId);
        if (request.isAsyncStarted()) {
            request.getAsyncContext().addListener(this);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(LOG_ID_KEY);
        }
    }

    @Override
    public void destroy() {

    }


    @Override
    public void onComplete(AsyncEvent asyncEvent) throws IOException {
        MDC.put(LOG_ID_KEY, currentLogId.get());
        currentLogId.set(null);
    }

    @Override
    public void onTimeout(AsyncEvent asyncEvent) throws IOException {
        MDC.put(LOG_ID_KEY, currentLogId.get());
        currentLogId.set(null);
    }

    @Override
    public void onError(AsyncEvent asyncEvent) throws IOException {
        MDC.put(LOG_ID_KEY, currentLogId.get());
        currentLogId.set(null);
    }

    @Override
    public void onStartAsync(AsyncEvent asyncEvent) throws IOException {

    }
}
