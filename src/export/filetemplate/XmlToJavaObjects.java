package export.filetemplate;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlToJavaObjects {
	public void test() {
		JAXBContext jc;
		try {
			jc = JAXBContext.newInstance();
			Unmarshaller u = jc.createUnmarshaller();
			Object o = u.unmarshal( new File( "config/fixedLengthConfig.xml" ) );
			System.out.println(o);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
