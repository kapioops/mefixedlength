package filetemplate;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class FileFixedLengthInfo {
	public void getFileFormat(String fname) {
		try {
	         File inputFile = new File("config/fixedLengthConfig.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();

	         String expression = "/files/file[@name = '"+ fname +"']/detail";
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
	            doc, XPathConstants.NODE);
	         
	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            /*if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("file name : " 
	                  + eElement.getAttribute("name"));
	               System.out.println("field_name : " 
	                  + eElement
	                  .getElementsByTagName("field_name")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("field_size : " 
	                  + eElement
	                  .getElementsByTagName("field_size")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("field_default : " 
	                  + eElement
	                  .getElementsByTagName("field_default")
	                  .item(0)
	                  .getTextContent());
	            }*/
	         }
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
	}
}
