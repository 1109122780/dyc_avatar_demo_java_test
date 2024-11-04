package douyin.avatar.avatar_demo_java.service;

import douyin.avatar.avatar_demo_java.service.dto.OnBoardingDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface IOnBoardingService {

    void OnBoarding(OnBoardingDTO onBoardingRequestDTO, HttpServletResponse resp);
}
