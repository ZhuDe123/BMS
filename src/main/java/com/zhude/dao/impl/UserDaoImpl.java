package com.zhude.dao.impl;

import com.zhude.dao.UserDao;
import com.zhude.entity.Page;
import com.zhude.entity.User;
import com.zhude.entity.UserManager;
import com.zhude.utils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private QueryRunner queryRunner = new QueryRunner();

    @Override
    public List<User> selectAll(Page page) {
        try {
            return queryRunner.query(DbUtils.getConnection(), "select * from user limit ?,?", new BeanListHandler<>(User.class), page.getStartRows(), page.getPageSize());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public long selectCount() {
        try {
            return queryRunner.query(DbUtils.getConnection(), "select count(*) from user", new ScalarHandler<>());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public User select(int id) {
        try {
            return queryRunner.query(DbUtils.getConnection(), "select * from user where id = ?;", new BeanHandler<>(User.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User select(String username) {
        try {
            return queryRunner.query(DbUtils.getConnection(), "select * from user where username = ?;", new BeanHandler<>(User.class), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> searchAll(Page page, String username, String phone) {
        List<User> users = null;
        try {
            if (username != null && username.length() > 0 ) {
                username = "%" + username + "%";
                if (phone != null && phone.length() > 0) {
                    phone = phone + "%";

                    users = queryRunner.query(DbUtils.getConnection(), "select * from  user  where username like ? and phone like ? limit ?,?;", new BeanListHandler<>(User.class), username, phone, page.getStartRows(), page.getPageSize());

                } else {
                    users = queryRunner.query(DbUtils.getConnection(), "select * from  user  where username like ? limit ?,?; ", new BeanListHandler<>(User.class), username, page.getStartRows(), page.getPageSize());
                }
            } else {
                if (phone != null && phone.length() > 0) {
                    phone = phone + "%";
                    users = queryRunner.query(DbUtils.getConnection(), "select * from  user  where phone like ? limit ?,?;", new BeanListHandler<>(User.class), phone, page.getStartRows(), page.getPageSize());

                } else {
                    return null;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public long searchAllCount(String username, String phone) {
        try {
            if (username != null && username.length() > 0 ) {
                username = "%" + username + "%";
                if (phone != null && phone.length() > 0) {
                    phone = phone + "%";
                    return queryRunner.query(DbUtils.getConnection(), "select count(username) from  user  where username like ? and phone like ?;", new ScalarHandler<>(), username, phone);

                } else {
                    return queryRunner.query(DbUtils.getConnection(), "select count(username) from  user  where username like ? ;", new ScalarHandler<>(), username);
                }

            } else {
                phone = phone + "%";
                if (phone != null && phone.length() > 0) {
                    return queryRunner.query(DbUtils.getConnection(), "select count(username) from  user  where phone like ?;", new ScalarHandler<>(), phone);

                } else {
                    return 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(User user) {
        try {
            return queryRunner.update(DbUtils.getConnection(), "insert into user(username,password,phone,email,sex) values (?,?,?,?,?)", user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail(), user.getSex());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(User user) {
        try {
            return queryRunner.update(DbUtils.getConnection(), "update user set username=?,password=?,phone=?,email=?,sex=? where id=?;", user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail(), user.getSex(), user.getId());
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        try {
            return queryRunner.update(DbUtils.getConnection(), "delete from user where id=?;", id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
