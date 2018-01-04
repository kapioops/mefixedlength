package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.Test;

import export.*;
import export.filetemplate.FileFixed;
import export.filetemplate.FileFixedDetail;
import export.filetemplate.FileFixedFieldInfo;
import export.filetemplate.FileFixedFooter;
import export.filetemplate.FileFixedHeader;
import export.filetemplate.FileFixedProcessor;
import export.filetemplate.XmlToJavaObjects;
import export.outputformat.iclicense.IcLicense;
import export.outputformat.iclicense.IcLicenseFooter;
import export.outputformat.iclicense.IcLicenseHeader;
import export.outputformat.iclicense.TestListIcData;
import importfile.ReadHRCsv;
import importfile.inputformat.IcFromHr;

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

		FixedFileExport ic = new FixedFileExport();
		IcLicense icl = new IcLicense();
		icl.idNo = "1234567890";
		
		FileFixedFieldInfo[] formatL = testXML("detail");

		if(formatL!=null) {
			for(FileFixedFieldInfo s: formatL) {
				System.out.println(s.getField_name()+"\t"+s.getField_default()+"\t"+s.getField_length());
			}
		}

		String yeah="";

			yeah = ic.getDataObjectDetail(icl, formatL);
		
		
		System.out.println(yeah);
		
		assertNotNull(icl);
	}
	
	
	
	
	@Test
	void testUnmashall() {
		XmlToJavaObjects xml2java = new XmlToJavaObjects();
		xml2java.test();
		assertNotNull(xml2java);
	}
	
	@Test
	void testGetLength() {
		FileFixedProcessor fprocessor = new FileFixedProcessor();
		TestListIcData test = new TestListIcData();
		FileFixed fileFormat = test.testXML();
		List<String> list = test.getFormatOutputIcData();
		
		FixedFileExport exp = new FixedFileExport();
		exp.writeFileFormatFile(fileFormat, list);

		assertNotNull(list);
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
