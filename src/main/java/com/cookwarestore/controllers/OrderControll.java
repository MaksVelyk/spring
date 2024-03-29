package com.cookwarestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cookwarestore.service.IOrderServI;
import com.cookwarestore.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class OrderControll {

    @Autowired
    IOrderServI orderService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order() {
        this.orderService.confirmOrder();
        return "redirect:/main";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model) {
        model.addAttribute("orders", this.orderService.getOrdersForCurrentUser());
        model.addAttribute("logged", this.sessionObject.isLogged());
        return "orders";
    }
}
