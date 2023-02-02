package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


	
	public class ExcelLibrary {


		/**
		 * This method is used to fetch the data from the excel
		 
		 */
		Workbook workbook;
		/**
		 * This method is used to initialize and open the excel workbook
		 * @param excelPath
		 * @return
		 * @throws IOException 
		 */
	 public void openExcel(String excelPath) throws IOException {
		FileInputStream fisExcel=new FileInputStream(excelPath);
		workbook=WorkbookFactory.create(fisExcel);
	}
	 
	 /**
	  * This method is used to fetch data from the excel
	  * @param excelPath
	  * @param sheetName
	 * @param j 
	 * @param i 
	 * @param string 
	  * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	  */
		
		
		public String getDataFromExcel(String path,String sheetName,int rowNumber,int cellNumber ) throws EncryptedDocumentException, IOException {
			
			FileInputStream fis = new FileInputStream(path);
			Workbook workbook= WorkbookFactory.create(fis);
			Sheet sheet=workbook.getSheet(sheetName);
			String data=new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(cellNumber));
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return data;
		}
	 
	 
			

		
		/**
		 * This method is used to close the workbook
		 * @throws IOException 
		 */
		
		public void closeExcelWorkBook() throws IOException {
			workbook.close();
		}
		
		/**
		 * This method is used to set the data into excel
		 * 
		 */
		
		public void setDataIntoExcel(String sheetName,int rowNumber,int cellNumber,String text) {
			Sheet sheet=workbook.getSheet(sheetName);
			sheet.getRow(rowNumber).createCell(cellNumber).setCellValue(text);
		}
		
		/**
		 * This method is used to save the Data int Excel
		 * @throws IOException 
		 */
		
		public void saveDataIntoExcel(String outputPath) throws IOException {
			workbook.write(new FileOutputStream(outputPath));
		
		} 
		/**
		 * This method is used to fetch the data from excel and store in map
		 * @param string 
		 */
		
		public Map<String,String> getDataFromExcelInMap(String sheetName, String string){
			Sheet sheet=workbook.getSheet(sheetName);
			
			Map<String, String> map=new HashMap<String, String>();
			DataFormatter df=new DataFormatter();
			for(int i=0;i<=sheet.getLastRowNum();i++) {
				map.put(df.formatCellValue(sheet.getRow(i).getCell(0)), df.formatCellValue(sheet.getRow(i).getCell(1)));
				
			}
			return map;
			
		}
		
		/**
		 * This method is used to fetch the data from excel and store in key
		 * @return 
		 * @return 
		 * @return 
		 * @throws IOException 
		 * @throws EncryptedDocumentException 
		 */
		
		
		public  String getDataFromExcelInKey(String sheetName,String key)  {
		
			Sheet sheet = workbook.getSheet(sheetName);
			
			String va=null;
			for(int i=0;i<=sheet.getLastRowNum();i++)
			{
		    String r = sheet.getRow(i).getCell(0).getStringCellValue();
		  if(r.equalsIgnoreCase(key))
		  {
			  va=sheet.getRow(i).getCell(1).getStringCellValue();
			  break;
		  }
		  }
			
		return va;	
		}
		public Object[][] readMultipleFromExcel(String sheet) throws EncryptedDocumentException, IOException{
			FileInputStream fis=new FileInputStream("./src/test/resources/DATAFETCH.xlsx");
			Workbook wb=WorkbookFactory.create(fis);
			Sheet sh=wb.getSheet(sheet);
			int lastRows=sh.getLastRowNum();
			int lastCel=sh.getRow(1).getLastCellNum();
			
			Object[][] data=new Object[lastRows][lastCel];
			for(int i=0;i<lastRows;i++) {
				for(int j=0;j<lastCel;j++) {
					data[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
				}
				
			}
			return data;
		}
		

		
		

	}
		
		



