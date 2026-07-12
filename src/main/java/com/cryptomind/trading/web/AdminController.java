package com.cryptomind.trading.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("pageId", "users");
        model.addAttribute("pageTitle", "page.users.title");
        return "admin/users";
    }

    @GetMapping("/topics")
    public String topics(Model model) {
        model.addAttribute("pageId", "topics");
        model.addAttribute("pageTitle", "page.topics.title");
        return "admin/topics";
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("pageId", "posts");
        model.addAttribute("pageTitle", "page.posts.title");
        return "admin/posts";
    }

    @GetMapping("/replies")
    public String replies(Model model) {
        model.addAttribute("pageId", "replies");
        model.addAttribute("pageTitle", "page.replies.title");
        return "admin/replies";
    }


    @GetMapping("/components")
    public String components(Model model) {
        model.addAttribute("pageId", "components");
        model.addAttribute("pageTitle", "page.components.title");
        return "admin/components";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("pageId", "signup");
        model.addAttribute("pageTitle", "page.signup.title");
        return "admin/signup";
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("pageId", "signin");
        model.addAttribute("pageTitle", "page.signin.title");
        return "admin/signin";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("pageId", "forgotPassword");
        model.addAttribute("pageTitle", "page.forgotPassword.title");
        return "admin/forgot-password";
    }

    @GetMapping("/sku-stock")
    public String skuStock(Model model) {
        model.addAttribute("pageId", "skuStock");
        model.addAttribute("pageTitle", "page.skuStock.title");
        return "admin/sku-stock";
    }

    @GetMapping("/sold")
    public String sold(Model model) {
        model.addAttribute("pageId", "sold");
        model.addAttribute("pageTitle", "page.sold.title");
        return "admin/sold";
    }
}
