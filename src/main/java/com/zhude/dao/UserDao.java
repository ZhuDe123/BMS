package com.zhude.dao;

import com.zhude.entity.Page;
import com.zhude.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> selectAll(Page page);

    public long selectCount();

    public List<User> searchAll(Page page, String username, String phone);

    public long searchAllCount(String username, String phone);

    public User select(int id);

    public User select(String username);



    public int insert(User user);

    public int update(User user);

    public int delete(int id);
}
