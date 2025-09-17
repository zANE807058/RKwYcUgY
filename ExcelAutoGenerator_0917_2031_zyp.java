// 代码生成时间: 2025-09-17 20:31:02
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Excel自动生成器服务类
 */
@Service
public class ExcelAutoGenerator {

    /**
     * 生成Excel文件
     * @param data 数据列表
     * @param sheetName 工作表名称
     * @param fileName 文件名称
     * @throws IOException 如果写入文件失败
     */
    public void generateExcel(List<List<Object>> data, String sheetName, String fileName) throws IOException {
        // 创建Excel工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建一个工作表
        Sheet sheet = workbook.createSheet(sheetName);

        // 创建表头
        Row headerRow = sheet.createRow(0);
        int headerCellNum = 0;
        for (Object header : data.get(0)) {
            Cell cell = headerRow.createCell(headerCellNum++);
            cell.setCellValue(header.toString());
        }

        // 填充数据
        int rowNum = 1;
        for (List<Object> row : data) {
            Row dataRow = sheet.createRow(rowNum++);
            int cellNum = 0;
            for (Object cellData : row) {
                Cell cell = dataRow.createCell(cellNum++);
                cell.setCellValue(cellData.toString());
            }
        }

        // 写入文件
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        // 关闭工作簿
        workbook.close();
    }
}