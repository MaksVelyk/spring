package com.cookwarestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cookwarestore.database.DB;
import com.cookwarestore.model.Cookware;
import com.cookwarestore.service.ICookwareServI;

import java.util.List;

@Service
public class CookwareServ implements ICookwareServI {

    @Autowired
    DB database;

    public List<Cookware> getAllCookwares() {
        return this.database.getCookwares();
    }
}