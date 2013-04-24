package org.sakaiproject.authoring.utils;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GraphUtil {

	public static void saveGraphInFile(File file, Node graphXML)
			throws Exception {
	
		FileOutputStream fos = new FileOutputStream(file);
		DOMSource source = new DOMSource(graphXML);
		StreamResult result = new StreamResult(fos);
	
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
	
		transformer.transform(source, result);
		fos.close();
	}

	public static Object openGraphInFile(File file) throws Exception {
	
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		NodeList list = document.getElementsByTagName("mxGraphModel");
		return list.item(0);
	
	}

}
