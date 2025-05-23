package api.utilities;
	
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class XLUtility {
		
		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;
        public static CellStyle style;
       public  static String path;
		
		public XLUtility(String path)
		{
			this.path=path;		
		}
		public static int getRowCount(String sheetName) throws IOException 
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			int rowcount=ws.getLastRowNum();
			wb.close();
			fi.close();
			return rowcount;		
		}
		
		
		public static int getCellCount(String sheetName,int rownum) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			row=ws.getRow(rownum);
			int cellcount=row.getLastCellNum();
			wb.close();
			fi.close();
			return cellcount;
		}
		
		
		public static String getCellData(String sheetName,int rownum,int colnum) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			row=ws.getRow(rownum);
			cell=row.getCell(colnum);
			String data;
			try 
			{
				DataFormatter formatter = new DataFormatter();
	            String cellData = formatter.formatCellValue(cell);
	            return cellData;
			}
			catch (Exception e) 
			{
				data="";
			}
			wb.close();
			fi.close();
			return data;
		}
		
		public static void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(sheetName);
			row=ws.getRow(rownum);
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fo=new FileOutputStream(path);
			wb.write(fo);		
			wb.close();
			fi.close();
			fo.close();
		}
		
		
	}

