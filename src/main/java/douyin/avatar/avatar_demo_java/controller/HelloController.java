package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.model.JsonResponse;
import douyin.avatar.avatar_demo_java.model.SetNameRequest;
import douyin.avatar.avatar_demo_java.model.TextDetectionRequest;
import douyin.avatar.avatar_demo_java.service.HelloService;
import douyin.avatar.avatar_demo_java.service.ObjectStorageService;
import douyin.avatar.avatar_demo_java.service.TextDetectionService;
import douyin.avatar.avatar_demo_java.service.impl.HelloServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private HelloServiceFactory factory;

    @Autowired
    private TextDetectionService textDetectionService;

    @Value("${cloud.env}")
    private String envMark;

    @GetMapping("/api/hello")
    public JsonResponse hello(@RequestParam(value = "target", defaultValue = "mongodb") String target) {
        JsonResponse response = new JsonResponse();
        try {
            HelloService helloService = factory.getHelloService(target);
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
            HelloService helloService = factory.getHelloService(setNameRequest.getTarget());
            helloService.setName(setNameRequest.getTarget(),setNameRequest.getName());
            response.success("");
        }catch (Exception e){
            response.failure("unknown error");
        }
        return response;
    }

    @PostMapping("/api/antidirt")
    public ResponseEntity<String> textDetection(@RequestBody TextDetectionRequest request) {
        String result = textDetectionService.performTextDetection(request);
        return ResponseEntity.ok(result);
    }

    @Autowired
    private ObjectStorageService objectStorageService;
    @GetMapping("/api/object")
    public ResponseEntity<String> objectOperation(){
        String ak = "AKLTMmY0MzAxNGM3NDczNGIyY2I0NTBkMjYzMGU4NWY1ODg";
        String sk = "T1dZek1UTmpNVGd4WldWaU5EUTVOV0U0TWpGa1ltSXdZV0k0TmpRM1ptWQ==";
        return ResponseEntity.ok(objectStorageService.putObjectDemo(ak,sk));
    }
}
