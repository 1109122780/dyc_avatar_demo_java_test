package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.request.SetNameRequest;
import douyin.avatar.avatar_demo_java.response.JsonResponse;
import douyin.avatar.avatar_demo_java.service.DataBaseService;
import douyin.avatar.avatar_demo_java.service.impl.DataBaseServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class DataBaseController {
    @Autowired
    private DataBaseServiceFactory dataBaseServiceFactory;

    @Value("${cloud.env}")
    private String envMark;

    @GetMapping("/api/hello")
    public JsonResponse hello(@RequestParam(value = "target", defaultValue = "mongodb") String target) {
        JsonResponse response = new JsonResponse();
        try {
            DataBaseService helloService = dataBaseServiceFactory.getHelloService(target);
            response.success("env:" + envMark + " hello " +  helloService.hello(target));
        }catch (Exception e){
            response.failure("unknown error");
        }
        return response;
    }

    @PostMapping("/api/set_name")
    public JsonResponse setName(@RequestBody SetNameRequest setNameRequest) {
        JsonResponse response = new JsonResponse();
        try {
            DataBaseService helloService = dataBaseServiceFactory.getHelloService(setNameRequest.getTarget());
            helloService.setName(setNameRequest.getTarget(),setNameRequest.getName());
            response.success("");
        }catch (Exception e){
            response.failure("unknown error");
        }
        return response;
    }
}
