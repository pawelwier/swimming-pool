package pl.akademiakodu.swimmingpool.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private String auth;
    public boolean flag = false;

    @GetMapping("/")
    public String showMain(Model model) {

        SecurityContext context = SecurityContextHolder.getContext();
        auth = context.getAuthentication().getName();
        if (auth.equals("admin")) {flag = true;}

        model.addAttribute("flag", flag);

        return "main";
    }

}
