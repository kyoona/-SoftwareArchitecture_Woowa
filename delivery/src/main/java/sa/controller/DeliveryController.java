package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sa.domain.User;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @GetMapping(path = "/test")
    public String test(){
        return "OK";
    }

    @PostMapping(path = "/users")
    public String registerUser(@RequestBody User user) {
        StringBuilder sb = new StringBuilder();
        sb.append("User registered: " + user.getUserName());
        return sb.toString();
    }
}
