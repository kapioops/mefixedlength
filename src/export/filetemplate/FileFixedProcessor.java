package export.filetemplate;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class FileFixedProcessor {
	@SuppressWarnings("finally")
	public FileFixedFieldInfo[] getFileFormat(String fname, String type) {
		
		FileFixedFieldInfo[] ffinfoList = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        factory.setNamespaceAware(true);
	        DocumentBuilder builder;
	        Document doc = null;
	        XPathExpression expr = null;
	        builder = factory.newDocumentBuilder();
	        doc = builder.parse("config/fixedLengthConfig.xml");

	        // create an XPathFactory
	        XPathFactory xFactory = XPathFactory.newInstance();

	        // create an XPath object
	        XPath xpath = xFactory.newXPath();
	        expr = xpath.compile("/files/file[@name=\""+ fname +"\"]/"+type+"/field");
	        // run the query and get a nodeset
	        Object result = expr.evaluate(doc, XPathConstants.NODESET);
	        
	        // cast the result to a DOM NodeList
	        NodeList nodes = (NodeList) result;
	        ffinfoList = new FileFixedFieldInfo[nodes.getLength()];
	        for (int i = 0; i<nodes.getLength() ; i++){
	        	
	        	Element filedListName = (Element) nodes.item(i);
	        	NodeList fnameN = filedListName.getElementsByTagName("fname");
	        	NodeList fsizeN = filedListName.getElementsByTagName("fsize");
	        	NodeList fdefaultN = filedListName.getElementsByTagName("fdefault");
	        	
	        	String field_name = (fnameN.item(0).getTextContent());
	        	int field_length = Integer.parseInt(fsizeN.item(0).getTextContent());
	        	String field_default = (fdefaultN.item(0).getTextContent());
	        	FileFixedFieldInfo ffinfo = new FileFixedFieldInfo(field_name, field_length, field_default);
	        	ffinfoList[i] = ffinfo;
	        }
	        
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      } finally {
	    	  return ffinfoList;
	      }
	}
	
	public int[] getStringFormatList(FileFixedFieldInfo[] objects) {
		int objSize = objects.length;
		int[] formats = null;
		if(objSize > 0) {
			formats = new int[objSize];
			int i = 0;
			for(FileFixedFieldInfo obj: objects) {
				formats[i++] = obj.getField_length();
			}
		}
		return formats;
	}
	
}
