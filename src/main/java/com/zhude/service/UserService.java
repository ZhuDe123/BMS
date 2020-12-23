package com.zhude.service;

import com.zhude.entity.Page;
import com.zhude.entity.User;

import java.util.List;

public interface UserService {
    public List<User> showAllUserByPage(Page page);

    public List<User> searchUser(Page page, String username, String phone);

    public User showUser(int id);

    public int addUser(User user);

    public int modify(User user);

    public int remove(int id);

    public User login(String username, String password);

    public User findUserName(String username);
}
