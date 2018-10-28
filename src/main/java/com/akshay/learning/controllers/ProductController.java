package com.akshay.learning.controllers;

import com.akshay.learning.service.ProductService;
import com.akshay.learning.service.UserService;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @GetMapping(path = "/products")
    public String getProducts(Principal principal, Model model){
        System.out.println("Inside product");
        final List<UserRepresentation> users = userService.getUsers();
        users.forEach(user -> System.out.println(user.getId()));
        model.addAttribute("name",principal.getName());
        model.addAttribute("products", productService.getProducts());
        return "product";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }


}
