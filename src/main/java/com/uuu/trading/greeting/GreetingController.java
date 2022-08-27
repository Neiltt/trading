package com.uuu.trading.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class GreetingController {
    @RequestMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = true)
                           String name, Model model) {
        model.addAttribute("name", name);
        log.info(name);
        return "greeting";
    }
}
