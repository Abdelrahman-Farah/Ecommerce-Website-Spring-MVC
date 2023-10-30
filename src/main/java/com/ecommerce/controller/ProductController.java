package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.utils.FileUploadUtil;

import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/add")
    public String newProductForm(Model theModel)
    {
        theModel.addAttribute("product", new Product());
        return "product/product-form";
    }

    @GetMapping("/{productId}/edit")
    public String updateProductForm(@PathVariable("productId") int productId, Model theModel)
    {
        // TODO: handle exception if the product is not found
        // TODO: enter string in PathVariable("productId")
        Product theProduct = productService.findById(productId);
        theModel.addAttribute("product", theProduct);
        return "product/product-form";
    }

    @PostMapping("/save")
    public String processProductForm(
            @Valid @ModelAttribute("product") Product theProduct,
            @RequestParam("file") MultipartFile multipartFile,
            BindingResult theBindingResult
    ) throws IOException {

        if (theBindingResult.hasErrors()){
            return "product/product-form";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        theProduct.setImage(fileName);

        Product savedProduct = productService.save(theProduct);

        String uploadDir = "user-photos/" + savedProduct.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        productService.save(theProduct);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") int productId)
    {
        // TODO: handle exception if the product is not found
        // TODO: enter string in PathVariable("productId")
        productService.deleteById(productId);
        return "redirect:/";
    }
}
