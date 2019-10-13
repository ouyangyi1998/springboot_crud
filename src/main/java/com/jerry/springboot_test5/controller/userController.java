package com.jerry.springboot_test5.controller;

import com.jerry.springboot_test5.entity.user;
import com.jerry.springboot_test5.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class userController {
    @Autowired
    userService userService;

    @RequestMapping("/")
    public String index()
    {
        return "user/login";
    }
    @RequestMapping("/list")
    public String list(Model model)
    {
        List<user> users=userService.getUserList();
        model.addAttribute("users",users);
        return "user/list";
    }
    @RequestMapping("/toRegister")
    public String toRegister()
    {
        return "user/register";
    }
    @RequestMapping("/register")
    public String register(user user)
    {
        userService.saveUser(user);
        return "user/login";
    }
    @RequestMapping("/login")
    public String login(user user)
    {
        user user1=userService.findUserByName(user.getUsername());
        System.out.print(user1.getUsername().equals(user.getUsername()));
        if(user1==null)
        {
            return "user/register";
        }else
        {
            if(!user.getUsername().equals(user1.getUsername()))
            {
                return "user/login";
            }
            else {
                return "redirect:/list";
            }
        }
    }
    @RequestMapping("/toAdd")
    public String toAdd()
    {
        return "user/userAdd";
    }

    @RequestMapping("/Add")
    public String add(user user)
    {
        userService.saveUser(user);
        return "redirect:/list";
    }
    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id)
    {
        user user=userService.findUserById(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }
    @RequestMapping("/edit")
    public String edit(user user)
    {
        userService.edit(user);
        return "redirect:/list";
    }
    @RequestMapping("/delete")
    public String delete(Long id)
    {
        userService.delete(id);
        return "redirect:/list";
    }

}
