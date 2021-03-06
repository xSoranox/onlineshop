package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.onlineshop.domain.Product;
import com.onlineshop.domain.ResultContainer;
import com.onlineshop.service.ProductService;
import com.onlineshop.service.ShoppingCartService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/products")
    public ModelAndView getAllProducts() {
        List<Product> products = productService.findAllProducts();
        int cartSize = shoppingCartService.getCartSize(1L);
        ModelAndView modelAndView = new ModelAndView("list-products");
        modelAndView.addObject("products", products);
        modelAndView.addObject("cartSize", cartSize);
        return modelAndView;
    }

    @RequestMapping("/products/example")
    public String getExampleOfProducts() {
        productService.createExampleOfProducts();
        return "redirect:/products";
    }

    @RequestMapping("/products/search")
    public ModelAndView findProductsByName(@RequestParam String productName) {
        List<Product> products = productService.findProductsByName(productName);
        ModelAndView modelAndView = new ModelAndView("list-products");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
    
    @RequestMapping("/products/findbytype/{productType}")
    public ModelAndView getProductsByType(@PathVariable("productType") String productType) {
        ResultContainer result = productService.findProductsByType(productType);
        ModelAndView modelAndView = new ModelAndView("list-products");
        modelAndView.addObject("products", result.getProducts());
        return modelAndView;
    }
    
    @RequestMapping("/products/flush")
    public String flush() {
        productService.deleteAllProducts();
        return "redirect:/";
    }
}
