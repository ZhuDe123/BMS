package com.zhude.service.impl;

import com.zhude.dao.UserManagerDao;
import com.zhude.dao.impl.UserManagerDaoImpl;
import com.zhude.entity.UserManager;
import com.zhude.service.UserManagerService;
import com.zhude.utils.DbUtils;

public class UserManagerServiceImpl implements UserManagerService {
    private UserManagerDao dao = new UserManagerDaoImpl();
    @Override
    public UserManager login(String username, String password) {
        UserManager userManager = null;
        try {
            DbUtils.begin();
            UserManager temp = dao.select(username);
            if (temp.getPassword().equalsIgnoreCase(password)) {
                userManager = temp;
            }
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return userManager;
    }

    @Override
    public UserManager findUserName(String username) {
        UserManager userManager = null;
        try {
            DbUtils.begin();
            userManager = dao.select(username);
            DbUtils.commit();
        } catch (Exception e) {
            DbUtils.rollback();
            e.printStackTrace();
        }
        return userManager;
    }
}
