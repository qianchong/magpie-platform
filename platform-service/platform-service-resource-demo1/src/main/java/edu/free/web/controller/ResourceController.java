package edu.free.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @GetMapping("/res")
    public ResponseEntity<String> res() {
        return ResponseEntity.ok("<h1>这是资源服务器1的受保护的资源</h1>");
    }
}
