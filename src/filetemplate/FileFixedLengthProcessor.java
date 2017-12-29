package filetemplate;

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
public class FileFixedLengthProcessor {
	@SuppressWarnings("finally")
	public FileFixedLengthInfo[] getFileFormat(String fname) {
		FileFixedLengthInfo[] ffinfoList = null;
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

	        // compile the XPath expression
	        expr = xpath.compile("/files/file[@name=\""+ fname +"\"]/detail/field");
	        // run the query and get a nodeset
	        Object result = expr.evaluate(doc, XPathConstants.NODESET);

	        // cast the result to a DOM NodeList
	        NodeList nodes = (NodeList) result;
	        ffinfoList = new FileFixedLengthInfo[nodes.getLength()];
	        for (int i = 0; i<nodes.getLength() ; ++i){
	        	Element question = (Element) nodes.item(i);
	            NodeList filedListName = question.getElementsByTagName("name");
	            NodeList filedListSize = question.getElementsByTagName("size");
	            NodeList filedListDefault = question.getElementsByTagName("default");
	            for (int j = 0; j < filedListName.getLength(); ++j)
	            {
	                Element ename = (Element) filedListName.item(j);
	                String name = (ename!=null)?ename.getTextContent():"";
	                Element esize = (Element) filedListSize.item(j);
	                String size = (esize!=null)?esize.getTextContent():"0";
	                Element edefault = (Element) filedListDefault.item(j);
	                String def = (edefault!=null)?edefault.getTextContent():"";
	                FileFixedLengthInfo ffinfo = new FileFixedLengthInfo(name,Integer.parseInt(size),def);
	                ffinfoList[i] = ffinfo;
	            }
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
}
