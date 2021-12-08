package apap.TugasAkhir.siFactory.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
//    @RequestMapping("/")
//    public String home(){
//        return "home";
//    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }



    @RequestMapping("/")
    public String home(Model model){
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        model.addAttribute("role",role);
        return "home";
    }

}
