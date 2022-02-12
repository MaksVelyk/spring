package com.cookwarestore.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cookwarestore.database.DB;
import com.cookwarestore.exceptions.LoginAlreadyUseExcept;
import com.cookwarestore.model.User;
import com.cookwarestore.model.view.RegisterUser;
import com.cookwarestore.service.IAuthenticationServI;
import com.cookwarestore.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticateServ implements IAuthenticationServI {

    @Autowired
    DB database;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> user = this.database.getUserByLogin(login);

        if(user.isEmpty() ||
                !user.get().getPass().equals(DigestUtils.md5Hex(password))) {
            return;
        }
        this.sessionObject.setUser(user.get());
    }

    @Override
    public void register(RegisterUser registerUser) {
        Optional<User> userBox = this.database.getUserByLogin(registerUser.getLogin());

        if(userBox.isPresent()) {
            throw new LoginAlreadyUseExcept();
        }

        registerUser.setPass(DigestUtils.md5Hex(registerUser.getPass()));
        this.database.addUser(registerUser);
    }
}