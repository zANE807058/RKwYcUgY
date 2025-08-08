// 代码生成时间: 2025-08-08 21:58:51
// BatchFileRenamer.java
// 批量文件重命名工具

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BatchFileRenamer {

    private final String sourceDirectory;
    private final String targetDirectory;

    // 构造函数，初始化源目录和目标目录
    public BatchFileRenamer(String sourceDirectory, String targetDirectory) {
        this.sourceDirectory = sourceDirectory;
        this.targetDirectory = targetDirectory;
    }

    // 重命名文件的方法
    public void renameFiles() throws IOException {
        // 获取源目录中的文件列表
        List<String> fileNames = listFilesInDirectory(sourceDirectory);

        // 检查目标目录是否存在，如果不存在则创建
        File targetDir = new File(targetDirectory);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        // 遍历文件列表并重命名每个文件
        for (String fileName : fileNames) {
            File sourceFile = new File(sourceDirectory + File.separator + fileName);
            File targetFile = new File(targetDirectory + File.separator + renameFile(fileName));

            if (sourceFile.renameTo(targetFile)) {
                System.out.println("File renamed: " + fileName);
            } else {
                System.out.println("Failed to rename file: " + fileName);
            }
        }
    }

    // 列出目录中的所有文件
    private List<String> listFilesInDirectory(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        List<String> fileNames = new ArrayList<>();
        Files.walk(path)
            .filter(Files::isRegularFile)
            .map(path::getFileName)
            .map(Path::toString)
            .forEach(fileNames::add);

        return fileNames;
    }

    // 文件重命名逻辑
    private String renameFile(String fileName) {
        // 添加自定义的重命名逻辑，例如添加前缀或后缀
        return "New_" + fileName;
    }

    public static void main(String[] args) {
        try {
            BatchFileRenamer renamer = new BatchFileRenamer("sourceDirectoryPath", "targetDirectoryPath");
            renamer.renameFiles();
        } catch (IOException e) {
            System.err.println("Error renaming files: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
