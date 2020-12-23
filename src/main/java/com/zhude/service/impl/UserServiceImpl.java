package com.zhude.service.impl;

import com.zhude.dao.UserDao;
import com.zhude.dao.impl.UserDaoImpl;
import com.zhude.entity.Page;
import com.zhude.entity.User;
import com.zhude.service.UserService;
import com.zhude.utils.DbUtils;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> showAllUserByPage(Page page) {
        List<User> users = null;
        try {
            DbUtils.begin();
            long count =  dao.selectCount();
            page.setTotalCounts((int) count);//赋值总条数，计算总页数
            users = dao.selectAll(page);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> searchUser(Page page, String username, String phone) {
        List<User> users = null;
        try {
            DbUtils.begin();
            long count = dao.searchAllCount(username, phone);
            page.setTotalCounts((int) count);//赋值总条数，计算总页数
            users = dao.searchAll(page, username, phone);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User showUser(int id) {
        User user = null;
        try {
            DbUtils.begin();
            User temp = dao.select(id);
            if (temp != null) {
                user = temp;
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int addUser(User user) {
        int res = 0;
        try {
            DbUtils.begin();
            res = dao.insert(user);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int modify(User user) {
        int res = 0;
        try {
            DbUtils.begin();
            res = dao.update(user);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public int remove(int id) {
        int res = 0;
        try {
            DbUtils.begin();
            res = dao.delete(id);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public User login(String username, String password) {
        User user = null;
        try{
            DbUtils.begin();
            User temp = dao.select(username);
            if (temp.getPassword().equalsIgnoreCase(password)) {
                user = temp;
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserName(String username) {
        User user = null;
        try{
            DbUtils.begin();
            user = dao.select(username);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return user;
    }
}
