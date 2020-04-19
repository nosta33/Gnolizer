package proj.DOM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLDOM {

	private static NodeList setProjectsList() {
		
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = null;
		
		try {
		    final DocumentBuilder builder = factory.newDocumentBuilder();		
		    document= builder.parse(new File("src/proj/DOM/exemple2.xml"));
		}
		catch (final ParserConfigurationException e) {
		    e.printStackTrace();
		}
		catch (final SAXException e) {
		    e.printStackTrace();
		}
		catch (final IOException e) {
		    e.printStackTrace();
		}
		
		final Element racine = document.getDocumentElement();
		
		final Element container = (Element) racine.getChildNodes();
				
		final Node projList = container.getChildNodes().item(1);
		
		return projList.getChildNodes();
		
	}
	
	
	public static ArrayList<Element> getProjectsList() {
		NodeList projList = XMLDOM.setProjectsList();
		ArrayList<Element> res = new ArrayList<Element>();
		for (int i = 0; i<(projList.getLength()); i++) {
		    if(projList.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        res.add((Element) projList.item(i));
		    }			
		}
		
		return res;
	}
	
	
	public static ArrayList<Element> getTasksList(Element projet){
		Element taskList = (Element) projet.getElementsByTagName("gtt:task-list").item(0);
		
		ArrayList<Element> taches = new ArrayList<Element>();
		for (int i = 0; i<taskList.getElementsByTagName("gtt:task").getLength(); i++) {
		    taches.add((Element) taskList.getElementsByTagName("gtt:task").item(i));			
		}
		
		return taches;
	}
	

	@SuppressWarnings("finally")
	public static ArrayList<Element> getIntervallesList(Element task){
		
		Element intervalleList = (Element) task.getElementsByTagName("gtt:interval-list").item(0);
		ArrayList<Element> intervalles = new ArrayList<Element>();
		try{
			for (int i = 0; i<intervalleList.getElementsByTagName("gtt:interval").getLength(); i++) {

				intervalles.add((Element) intervalleList.getElementsByTagName("gtt:interval").item(i));
			
			}
		}
		finally{
			return intervalles;
		}
		
	}

}
