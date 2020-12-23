package com.zhude.dao;

import com.zhude.entity.UserManager;

public interface UserManagerDao {
    public UserManager select(String username);
}
