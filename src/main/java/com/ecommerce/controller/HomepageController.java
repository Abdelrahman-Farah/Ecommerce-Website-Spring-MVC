package com.ecommerce.controller;

import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    private final ProductService productService;

    @Autowired
    public HomepageController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping("/")
    public String showHomepage(Model theModel){
        theModel.addAttribute("products", productService.findAll());
        return "homepage";
    }
}
