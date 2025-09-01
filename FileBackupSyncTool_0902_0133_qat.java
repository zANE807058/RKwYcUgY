// 代码生成时间: 2025-09-02 01:33:19
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileBackupSyncTool {

    private static final Logger logger = LoggerFactory.getLogger(FileBackupSyncTool.class);

    private static final String SOURCE_DIR = "/path/to/source";
    private static final String TARGET_DIR = "/path/to/target";
    private static final String BACKUP_DIR = "/path/to/backup";

    // 同步源目录到目标目录，并备份原目标目录文件
    public static void syncAndBackup() {
        try {
            File sourceDir = new File(SOURCE_DIR);
            File targetDir = new File(TARGET_DIR);
            File backupDir = new File(BACKUP_DIR);

            // 确保目标和备份目录存在
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            if (!backupDir.exists()) {
                backupDir.mkdirs();
            }

            // 备份目标目录文件
            backupFiles(targetDir, backupDir);

            // 同步文件
            syncFiles(sourceDir, targetDir);

            logger.info("Files synchronized and backed up successfully.");
        } catch (IOException e) {
            logger.error("Error during file synchronization and backup", e);
        }
    }

    // 备份文件方法
    private static void backupFiles(File targetDir, File backupDir) throws IOException {
        if (!targetDir.isDirectory()) {
            throw new IOException("Target directory is not a directory");
        }

        // 遍历目标目录文件
        File[] files = targetDir.listFiles();
        if (files != null) {
            for (File file : files) {
                File backupFile = new File(backupDir, file.getName());
                Files.copy(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                logger.info("Backed up file: {} to {}", file.getAbsolutePath(), backupFile.getAbsolutePath());
            }
        }
    }

    // 同步文件方法
    private static void syncFiles(File sourceDir, File targetDir) throws IOException {
        if (!sourceDir.isDirectory()) {
            throw new IOException("Source directory is not a directory");
        }

        // 遍历源目录文件
        File[] files = sourceDir.listFiles();
        if (files != null) {
            for (File file : files) {
                File targetFile = new File(targetDir, file.getName());
                Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                logger.info("Synced file: {} to {}", file.getAbsolutePath(), targetFile.getAbsolutePath());
            }
        }
    }

    // 主方法，用于执行同步和备份操作
    public static void main(String[] args) {
        syncAndBackup();
    }
}
