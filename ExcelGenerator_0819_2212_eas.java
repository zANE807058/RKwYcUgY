// 代码生成时间: 2025-08-19 22:12:53
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel表格自动生成器
 * 该类用于创建Excel文件，并填充数据。
 */
public class ExcelGenerator {

    /**
     * 创建一个新的Excel工作簿
     * @return 返回一个Workbook对象，用于进一步操作
     */
    private Workbook createWorkbook() {
        try {
            return new XSSFWorkbook();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // 在实际应用中应替换为更合理的错误处理方式
        }
    }

    /**
     * 创建一个Excel工作表
     * @param workbook 工作簿对象
     * @param sheetName 工作表名称
     * @return 工作表对象
     */
    private Sheet createSheet(Workbook workbook, String sheetName) {
        try {
            return workbook.createSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // 在实际应用中应替换为更合理的错误处理方式
        }
    }

    /**
     * 向工作表中添加数据行
     * @param sheet 工作表对象
     * @param data 数据行列表
     */
    private void addRows(Sheet sheet, List<List<Object>> data) {
        for (List<Object> row : data) {
            Row excelRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int i = 0; i < row.size(); i++) {
                Cell cell = excelRow.createCell(i);
                cell.setCellValue(row.get(i).toString());
            }
        }
    }

    /**
     * 将工作簿保存为Excel文件
     * @param workbook 工作簿对象
     * @param filePath 文件路径
     */
    private void saveWorkbook(Workbook workbook, String filePath) {
        try (OutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成Excel文件并保存
     * @param filePath 文件保存路径
     * @param sheetName 工作表名称
     * @param data 要写入的数据
     * @return 操作结果，成功返回true，失败返回false
     */
    public boolean generateExcel(String filePath, String sheetName, List<List<Object>> data) {
        Workbook workbook = createWorkbook();
        if (workbook == null) {
            return false;
        }

        Sheet sheet = createSheet(workbook, sheetName);
        if (sheet == null) {
            return false;
        }

        addRows(sheet, data);

        saveWorkbook(workbook, filePath);

        return true;
    }

    // 示例用法
    public static void main(String[] args) {
        ExcelGenerator excelGenerator = new ExcelGenerator();
        String filePath = "./output.xlsx";
        String sheetName = "Example Sheet";
        List<List<Object>> data = new ArrayList<>();

        // 添加一些示例数据
        List<Object> row1 = new ArrayList<>();
        row1.add("Header1");
        row1.add("Header2");
        row1.add("Header3");
        data.add(row1);

        List<Object> row2 = new ArrayList<>();
        row2.add("Data1");
        row2.add("Data2");
        row2.add("Data3");
        data.add(row2);

        boolean result = excelGenerator.generateExcel(filePath, sheetName, data);
        if (result) {
            System.out.println("Excel文件创建成功！");
        } else {
            System.out.println("Excel文件创建失败！");
        }
    }
}