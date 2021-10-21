package com.intuitivecare.prova.IntuitiveCare.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(value= "/")
	public String index() throws IOException {
		return "index";
	}
	
}
