package com.rollerspeed.pos.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/")
public class IndexController {
    
    @GetMapping(value = "index")
    public String index(){
        return  "index";
    }    
}
