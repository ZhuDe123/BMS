package com.zhude.dao.impl;

import com.zhude.dao.UserManagerDao;
import com.zhude.entity.UserManager;
import com.zhude.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserManagerDaoImpl implements UserManagerDao {
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public UserManager select(String username) {
        try {
            return queryRunner.query(DbUtils.getConnection(), "select * from userManager where username = ?;", new BeanHandler<>(UserManager.class), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
