package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import export.*;
import filetemplate.*;

class testNewThing {
	@Test
	void test() {
		TestClass test = new TestClass('A', "Hello World!");

		Field[] fields = TestClass.class.getDeclaredFields();
		for(Field field: fields) {
			try {
				System.out.println(field.get(test).toString());
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		assertNotNull(fields);
	}
	
	@Test
	void testIcLicense() {
		String format = "%1s%6s%10s%15s%8s%6s%10s%10s%6s%8s%6s%10s%10s%6s%2s%25s%8s%10s%30s%50s%25s%1s%6s%1s%1s%129s";

		IcLicense icl = new IcLicense();
		icl.idNo = "1234567890";
		IcLicenseExport ic = new IcLicenseExport();

			String yeah="";
			try {
				yeah = ic.getDataObjectDetail(icl, format);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(yeah.length());
		
		assertNotNull(icl);
	}
	
	@Test
	void testXML() {
		FileFixedLengthInfo ff = new FileFixedLengthInfo();
		ff.getFileFormat("MSLI0100_TDMMYY_BCAP.TXT");
		assertNotNull(ff);
	}
}
class TestClass{
	
	public String sellingAgentCode;
	public char recordType;
	
	public TestClass(char recordType, String sellingAgentCode){
		this.recordType = recordType;
		this.sellingAgentCode = sellingAgentCode;
	}
	
}
