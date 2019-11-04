package com.jerry.springboot_test5.controller;

import com.jerry.springboot_test5.entity.user;
import com.jerry.springboot_test5.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class userController {
    private static int pageNumber=0;
    private static final int pageSize;
    static {
        pageSize=3;
    }
    @Autowired
    userService userService;

    @RequestMapping("/")
    public String index() {
        return "user/login";
    }

    @RequestMapping("/list")
    public String list(Model model) {
    int number=0;
        if(pageNumber<1)
        {
            pageNumber=1;
        }else
        {
            List<user> list=userService.getUserList();
            for(user user:list)
            {
                number++;
            }
            int pageTotal=(number+pageSize-1)/pageSize;
          if(pageNumber>pageTotal)
          {
              pageNumber=pageTotal;
          }
        }
        PageRequest pageRequest=PageRequest.of(pageNumber-1,pageSize);
        Page<user> users=userService.findAllOf(pageRequest);
        List<user> all=users.getContent();
        model.addAttribute("users",all);
        return "user/list";
    }

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "user/register";
    }

    @RequestMapping("/register")
    public String register(user user, Model model) {
        if (user.getUsername().equals("") || user.getPassword().equals("")) {
            model.addAttribute("msg", "注册失败");
            return "/user/register";
        }
        user user1 = userService.findUserByName(user.getUsername());
        System.out.println(user1==null);
        if (user1==null) {
            userService.saveUser(user);
            return "user/login";
        } else {
            model.addAttribute("msg", "用户名存在");
            return "user/register";
        }
    }

    @RequestMapping("/login")
    public String login(user user, Model model) {
        if (user.getPassword().equals("") || user.getUsername().equals("")) {
            model.addAttribute("msg", "登录失败");
            return "user/login";
        }
        user user1 = userService.findUserByName(user.getUsername());
        System.out.println(user1);

        if(user1==null)
        {
            model.addAttribute("msg", "用户名错误");
            return "user/login";
        }
        if (! user.getPassword().equals(user1.getPassword())) {
            model.addAttribute("msg", "密码错误");
            return "user/login";
        } else {
            return "redirect:/list";
        }

    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/Add")
    public String add(user user) {
        userService.saveUser(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        user user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(user user) {
        userService.edit(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "user/login";
    }

    @RequestMapping("/toNextPage")
    public String toNextPage(Model model)
    {
        int number=0;
        pageNumber++;
        if(pageNumber<1)
        {
            pageNumber=1;
        }else
        {
            List<user> list=userService.getUserList();
            for(user user:list)
            {
                number++;
            }
           int pageTotal=(number+pageSize-1)/pageSize;

            if(pageNumber>pageTotal)
            {
                pageNumber=pageTotal;
            }
        }
        PageRequest pageRequest=PageRequest.of(pageNumber-1,pageSize);
       Page<user> users=userService.findAllOf(pageRequest);
       List<user> all=users.getContent();
        model.addAttribute("users",all);
        return "user/list";
    }
    @RequestMapping("/toLastPage")
    public String toLastPage(Model model)
    {
        int number=0;
        pageNumber--;
        if(pageNumber<1)
        {
            pageNumber=1;
        }else
        {
            List<user> list=userService.getUserList();
            for(user user:list)
            {
                number++;
            }
            int pageTotal=(number+pageSize-1)/pageSize;
            if(pageNumber>pageTotal)
            {
                pageNumber=pageTotal;
            }
        }
        PageRequest pageRequest=PageRequest.of(pageNumber-1,pageSize);
        Page<user> users=userService.findAllOf(pageRequest);
        List<user> all=users.getContent();
        model.addAttribute("users",all);
        return "user/list";
    }
}
