package com.thc.sprboot.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbgrantuser")
@Controller
public class TbgrantuserController {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "tbgrantuser/" + page;
    }


    @GetMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id){
        return "tbgrantuser/" + page;
    }
}
