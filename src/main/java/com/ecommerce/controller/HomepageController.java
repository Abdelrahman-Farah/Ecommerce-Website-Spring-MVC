package com.ecommerce.controller;

import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.ecommerce.utils.UserContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public HomepageController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService= categoryService;
    }



    @GetMapping("/")
    public String showHomepage(Model theModel){
        theModel.addAttribute("categories", categoryService.findAll());
        theModel.addAttribute("products", productService.findAll());
        return "homepage";
    }
}
