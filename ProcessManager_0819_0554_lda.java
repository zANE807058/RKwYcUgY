// 代码生成时间: 2025-08-19 05:54:40
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.ArrayList;

// Process类代表一个进程
class Process {
    private int id;
    private String name;
    private boolean isActive;

    // 构造函数
    public Process(int id, String name, boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    // Getter和Setter方法
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}

// ProcessManager类用于管理进程
public class ProcessManager {

    private static SessionFactory sessionFactory;

    // 初始化SessionFactory
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + "Exception: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // 提供SessionFactory的全局访问点
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // 启动一个进程
    public void startProcess(Process process) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(process);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // 停止一个进程
    public void stopProcess(Process process) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSessionFactory().openSession();
            tx = session.beginTransaction();
            process.setActive(false);
            session.update(process);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // 获取所有活跃的进程
    public List<Process> getActiveProcesses() {
        List<Process> activeProcesses = new ArrayList<>();
        Session session = null;
        try {
            session = getSessionFactory().openSession();
            // HQL查询所有活跃的进程
            List results = session.createQuery("from Process where isActive = true").list();
            for (Object result : results) {
                activeProcesses.add((Process) result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return activeProcesses;
    }
}
