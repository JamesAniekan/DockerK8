package com.example.DockerK8;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public String helloK8(){
        return "This app is hosted on a Kubernetes cluster";
    }
}
