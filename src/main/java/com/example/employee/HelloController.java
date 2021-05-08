package com.example.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/")
    public String index(Model model) {
		String name = "hirano";
		model.addAttribute("name", name);
        return "index";
    }
}
