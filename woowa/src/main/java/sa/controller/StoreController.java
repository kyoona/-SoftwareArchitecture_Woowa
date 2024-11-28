package sa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.service.StoreService;

@RequiredArgsConstructor
@RestController
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public String test(){
        storeService.test();
        return "OK";
    }
}
