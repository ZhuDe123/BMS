package com.zhude.service;

import com.zhude.entity.UserManager;

public interface UserManagerService {
    public UserManager login(String username, String password);

    public UserManager findUserName(String username);
}
