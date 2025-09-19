// 代码生成时间: 2025-09-19 23:48:07
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 批量文件重命名工具
 * @author Your Name
 */
public class BulkFileRename {

    private final String directoryPath;
    private final String fileExtension;
    private final String newPrefix;
    private final int startIndex;

    /**
     * 构造函数
     *
     * @param directoryPath 文件夹路径
     * @param fileExtension 文件扩展名
     * @param newPrefix 新的文件名前缀
     * @param startIndex 文件重命名的起始编号
     */
    public BulkFileRename(String directoryPath, String fileExtension, String newPrefix, int startIndex) {
        this.directoryPath = directoryPath;
        this.fileExtension = fileExtension;
        this.newPrefix = newPrefix;
        this.startIndex = startIndex;
    }

    /**
     * 执行批量文件重命名操作
     *
     * @throws IOException 如果发生IO异常
     */
    public void renameFiles() throws IOException {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("The specified path is not a directory.");
        }

        // 获取文件夹中所有指定扩展名的文件
        File[] files = directory.listFiles((dir, name) -> name.endsWith(fileExtension));
        if (files == null) {
            throw new IOException("Failed to list files in directory.");
        }

        // 按文件名排序
        Arrays.sort(files, (a, b) -> a.getName().compareTo(b.getName()));

        for (int i = 0; i < files.length; i++) {
            String oldName = files[i].getName();
            String newName = newPrefix + (startIndex + i) + fileExtension;
            File newFile = new File(directory, newName);

            // 重命名文件
            if (!files[i].renameTo(newFile)) {
                throw new IOException("Failed to rename file from " + oldName + " to " + newName);
            }
        }
    }

    public static void main(String[] args) {
        try {
            // 示例用法
            BulkFileRename renamer = new BulkFileRename("/path/to/directory", ".txt", "file", 1);
            renamer.renameFiles();
            System.out.println("Files renamed successfully.");
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
