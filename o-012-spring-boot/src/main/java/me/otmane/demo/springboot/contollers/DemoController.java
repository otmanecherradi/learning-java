package me.otmane.demo.springboot.contollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello(@RequestParam(defaultValue = "dear") String name) {
        return ResponseEntity.ok("Hello %s".formatted(name));
    }

    @GetMapping(value = "/hello/{name}")
    public ResponseEntity<String> helloWithName(@PathVariable String name) {
        return ResponseEntity.ok("Hello %s".formatted(name));
    }
}
