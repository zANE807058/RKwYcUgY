// 代码生成时间: 2025-09-03 23:03:58
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.ArrayList;

// 用户类
class User {
    private int id;
    private String name;
    private List<Role> roles;

    // 构造函数、getter和setter省略
}

// 角色类
class Role {
    private int id;
    private String name;
    // 构造函数、getter和setter省略
}

// 用户权限实体类
class UserPermission {
    private int id;
    private User user;
    private Role role;
    // 构造函数、getter和setter省略
}

// 用户权限管理服务类
public class UserPermissionManagement {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // 创建SessionFactory对象
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // 日志记录异常或处理错误
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // 添加用户权限
    public void addUserPermission(User user, Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserPermission userPermission = new UserPermission();
            userPermission.setUser(user);
            userPermission.setRole(role);
            session.save(userPermission);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // 日志记录异常或处理错误
            System.err.println("Error in adding user permission" + e);
        } finally {
            session.close();
        }
    }

    // 删除用户权限
    public void removeUserPermission(User user, Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from UserPermission where user = :user and role = :role");
            query.setParameter("user", user);
            query.setParameter("role", role);
            List<UserPermission> userPermissions = query.list();
            for (UserPermission userPermission : userPermissions) {
                session.delete(userPermission);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            // 日志记录异常或处理错误
            System.err.println("Error in removing user permission" + e);
        } finally {
            session.close();
        }
    }

    // 获取用户的所有权限
    public List<Role> getUserPermissions(User user) {
        Session session = sessionFactory.openSession();
        List<Role> roles = new ArrayList<>();
        try {
            Query query = session.createQuery("from UserPermission where user = :user");
            query.setParameter("user", user);
            List<UserPermission> userPermissions = query.list();
            for (UserPermission userPermission : userPermissions) {
                roles.add(userPermission.getRole());
            }
        } finally {
            session.close();
        }
        return roles;
    }

    // Hibernate SessionFactory关闭方法
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}