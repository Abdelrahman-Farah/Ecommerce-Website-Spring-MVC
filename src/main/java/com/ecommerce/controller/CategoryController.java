package com.ecommerce.controller;

import com.ecommerce.dao.CategoryRepository;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.utils.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String showAllCategories(Model theModel){
        theModel.addAttribute("categories", categoryService.findAll());
        return "category/categories-list";
    }

    @GetMapping("/{categoryId}")
    public String showCategory(@PathVariable("categoryId") int categoryId, Model theModel)
    {
        // TODO: handle exception if the category is not found
        // TODO: enter string in PathVariable("categoryId")
        Category category = categoryService.findById(categoryId);

        theModel.addAttribute("category", category);

        return "category/category-details";
    }

    @GetMapping("/add")
    public String newCategoryForm(Model theModel)
    {
        theModel.addAttribute("category", new Category());
        theModel.addAttribute("form_status", "NEW");
        return "category/category-form";
    }

    @GetMapping("/{categoryId}/edit")
    public String updateCategoryForm(@PathVariable("categoryId") int categoryId, Model theModel)
    {
        // TODO: handle exception if the category is not found
        // TODO: enter string in PathVariable("categoryId")
        Category category = categoryService.findById(categoryId);
        theModel.addAttribute("category", category);
        theModel.addAttribute("form_status", "UPDATE");
        return "category/category-form";
    }

    @PostMapping("/save")
    public String processCategoryForm(
            @Valid @ModelAttribute("category") Category theCategory,
            BindingResult theBindingResult,
            @RequestParam("file") MultipartFile multipartFile,
            Model theModel
    ) throws IOException {
        if (theBindingResult.hasErrors()) {
            return "category/category-form";
        }

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        theCategory.setImage(fileName);

        Category savedCategory = categoryService.save(theCategory);

        String uploadDir = "system-images/categories";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:/categories/";
    }

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("categoryId") int categoryId)
    {
        categoryService.deleteById(categoryId);
        return "redirect:/categories/";
    }
}
