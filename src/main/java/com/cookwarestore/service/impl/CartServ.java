package com.cookwarestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cookwarestore.database.DB;
import com.cookwarestore.model.Cookware;
import com.cookwarestore.model.OrderPosition;
import com.cookwarestore.service.ICartServI;
import com.cookwarestore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartServ implements ICartServI {

    @Autowired
    DB database;

    @Resource
    SessionObject sessionObject;

    public void addCookwareToCart(int cookwareId) {
        Optional<Cookware> cookwareBox = this.database.getCookwareById(cookwareId);

        if(cookwareBox.isEmpty()) {
            return;
        }

        Cookware cookware = cookwareBox.get();
        if(!(cookware.getQuantity() > 0)) {
            return;
        }

        for(OrderPosition orderPosition : this.sessionObject
                .getCart().getOrderPositions()) {
            if(orderPosition.getCookware().getId() == cookwareId) {
                orderPosition.incrementQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(cookware, 1);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}