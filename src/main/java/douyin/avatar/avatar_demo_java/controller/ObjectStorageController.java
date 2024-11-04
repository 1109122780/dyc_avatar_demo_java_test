package douyin.avatar.avatar_demo_java.controller;

import douyin.avatar.avatar_demo_java.service.ObjectStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectStorageController {
    @Autowired
    private ObjectStorageService objectStorageService;

    @GetMapping("/api/object")
    public ResponseEntity<String> objectOperation(){
        String ak = "AKLTMmY0MzAxNGM3NDczNGIyY2I0NTBkMjYzMGU4NWY1ODg";
        String sk = "T1dZek1UTmpNVGd4WldWaU5EUTVOV0U0TWpGa1ltSXdZV0k0TmpRM1ptWQ==";
        return ResponseEntity.ok(objectStorageService.putObjectDemo(ak,sk));
    }
}
