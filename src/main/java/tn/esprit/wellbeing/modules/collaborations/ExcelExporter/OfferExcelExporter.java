package tn.esprit.wellbeing.modules.collaborations.ExcelExporter;

import java.io.IOException;
import java.util.List;
 
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tn.esprit.wellbeing.modules.collaborations.models.Offre;

public class OfferExcelExporter {
	
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Offre> offersList;
   
    public OfferExcelExporter(List<Offre> offersList) {
        this.offersList = offersList;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Offers");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        
        createCell(row, 0, "TITLE", style);      
        createCell(row, 1, "DSCRIPTION", style);      
        createCell(row, 2, "START-DATE", style);      
        createCell(row, 3, "END-DATE", style);    
        createCell(row, 4, "NUMBER-OF-Available-PLACES", style);
      
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
     
    private void writeDataLines() {
        int rowCount = 1;
 
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
                 
        for (Offre offer : offersList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, offer.getTitle(), style);
            createCell(row, columnCount++, offer.getDescription(), style);
            createCell(row, columnCount++, offer.getStartDate().toString(), style);
            createCell(row, columnCount++, offer.getEndDate().toString(), style);
            createCell(row, columnCount++, offer.getNbOfAvailablePlaces(), style);
                  
        }
    }
     
    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
         
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
         
        outputStream.close();
         
    }
   

}