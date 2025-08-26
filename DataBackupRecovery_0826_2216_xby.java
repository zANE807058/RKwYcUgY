// 代码生成时间: 2025-08-26 22:16:27
// 数据备份恢复类
class DataBackupRecovery {

    // Hibernate Session Factory
    private SessionFactory sessionFactory;

    // 构造函数
    public DataBackupRecovery(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // 备份数据到文件
    public void backupData(String backupFilePath) {
        try (Session session = sessionFactory.openSession();
             Transaction transaction = session.beginTransaction();) {
            // 获取所有的实体类
            // 这里以User为例，实际使用时需要根据实际情况调整
            Criteria criteria = session.createCriteria(User.class);
            List<User> users = criteria.list();

            // 将数据序列化到文件
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backupFilePath));
            oos.writeObject(users);
            oos.close();

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从文件恢复数据
    public void restoreData(String backupFilePath) {
        try (Session session = sessionFactory.openSession();
             Transaction transaction = session.beginTransaction();) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupFilePath));
            List<User> users = (List<User>) ois.readObject();
            ois.close();

            // 将数据反序列化到数据库
            for (User user : users) {
                session.saveOrUpdate(user);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
