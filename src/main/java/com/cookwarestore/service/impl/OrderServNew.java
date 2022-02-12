package com.cookwarestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cookwarestore.database.DB;
import com.cookwarestore.model.Cookware;
import com.cookwarestore.model.Order;
import com.cookwarestore.model.OrderPosition;
import com.cookwarestore.service.IOrderServI;
import com.cookwarestore.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServNew implements IOrderServI {

    @Resource
    SessionObject sessionObject;

    @Autowired
    DB database;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), this.sessionObject.getCart().getOrderPositions());
        this.database.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Cookware> cookwareBox = database.getCookwareById(orderPosition.getCookware().getId());
            if(cookwareBox.isPresent()) {
                cookwareBox.get().setQuantity(cookwareBox.get().getQuantity() - orderPosition.getQuantity());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.database.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}