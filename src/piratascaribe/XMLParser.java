/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piratascaribe;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author John
 */
public class XMLParser {
    public Isla islat;
    public Sitio sitiot;
    public Cayo cayot;
    public Barco barcotemp;
    public ArrayList<Sitio> sitiostemp;
    public ArrayList<Isla> islastemp;
    public ArrayList<Cayo> cayostemp;
    
     public void leerBarcos(int idBarco) {
 
    try {
 
	File fXmlFile = new File("barcos.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	NodeList nList = doc.getElementsByTagName("barco");
	System.out.println("----------------------------");
	for (int temp = 0; temp < nList.getLength(); temp++) { 
		Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
                        if(Integer.parseInt(eElement.getAttribute("id"))==idBarco){
			System.out.println("Barco id : " + eElement.getAttribute("id"));
			System.out.println("Nombre : " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
			System.out.println("Pirata : " + eElement.getElementsByTagName("pirata").item(0).getTextContent());
			System.out.println("Origen : " + eElement.getElementsByTagName("origen").item(0).getTextContent());
			System.out.println("Destino : " + eElement.getElementsByTagName("destino").item(0).getTextContent());
                        System.out.println("Tripulacion : " + eElement.getElementsByTagName("tripulacion").item(0).getTextContent());
                        System.out.println("Municiones : " + eElement.getElementsByTagName("municiones").item(0).getTextContent());
                        System.out.println("Raciones : " + eElement.getElementsByTagName("raciones").item(0).getTextContent());
                        barcotemp = new Barco(eElement.getElementsByTagName("nombre").item(0).getTextContent(),
                                Boolean.parseBoolean(eElement.getElementsByTagName("pirata").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("tripulacion").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("raciones").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("municiones").item(0).getTextContent()));
                        barcotemp.setPuertoOrigen(eElement.getElementsByTagName("origen").item(0).getTextContent());
                        }
		}
	}
    } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException | DOMException e) {
    }
  } 
     public void leerMaquinas(int idMaquina){
         try {
 
	File fXmlFile = new File("maquinas.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	NodeList nList = doc.getElementsByTagName("maquina");
	System.out.println("----------------------------");
	for (int temp = 0; temp < nList.getLength(); temp++) { 
		Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
                        if(Integer.parseInt(eElement.getAttribute("id"))==idMaquina){
			System.out.println("Maquina id : " + eElement.getAttribute("id"));
                        NodeList nLugares = nNode.getChildNodes();
                        for(int i=0;i<nLugares.getLength();i++){
                            Node nLugar = nLugares.item(i);
                            if(nLugar.getNodeType()==Node.ELEMENT_NODE){
                                Element eLugar = (Element) nLugar;
                                System.out.println("Nombre "+eLugar.getTagName()+": "+eLugar.getAttribute("nombre"));
                                if("cayo".equals(eLugar.getTagName())){
                                    cayot.setNombre(eLugar.getAttribute("nombre"));
                                    cayot.setCofre(listarTesoros(nLugar));
                                    cayostemp.add(cayot);
                                }else{
                                    NodeList nSitios = nLugar.getChildNodes();
                                            for(int j=0;j<nSitios.getLength();j++){
                                                Node nSitio = nSitios.item(j);
                                                if(nSitio.getNodeType()==Node.ELEMENT_NODE){
                                                    Element eSitio = (Element) nSitio;
                                                    System.out.println("Nombre "+ eSitio.getTagName()+ ": "+eSitio.getAttribute("nombre"));
                                                    sitiot.setNombre(eSitio.getAttribute("nombre"));
                                                    sitiot.setCofre(listarTesoros(nSitio));
                                                    sitiostemp.add(sitiot);
                                                }
                                            }
                                            islat.setNombre(eLugar.getAttribute("nombre"));
                                            islat.setSitios(sitiostemp);
                                            islastemp.add(islat);
                                }
                            }
                        }
                        }
		}
	}
    } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException | DOMException e) {
    }
     }
     
     public Cofre listarTesoros(Node nodo){
         Cofre cofretemp = new Cofre(10000);
         NodeList lista = nodo.getChildNodes();
         for(int i=0; i<lista.getLength();i++){
             Node nCofre = lista.item(i);
             if(nCofre.getNodeType()==Node.ELEMENT_NODE){
                 Element eCofre = (Element) nCofre;
                 System.out.println("Tesoro: "+ eCofre.getAttribute("tesoro"));
                 Tesoro tesorotemp = new Tesoro(eCofre.getAttribute("tesoro"));
                 cofretemp.agregarTesoro(tesorotemp);
             }
         }
         return cofretemp;
     }
}
