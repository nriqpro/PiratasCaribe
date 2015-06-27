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
    public Barco barcotemp;
    public ArrayList<Isla> islastemp = new ArrayList<>();
    public ArrayList<Cayo> cayostemp = new ArrayList<>();
    
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
                        System.out.println("Prueba : " + eElement.getTagName());
			System.out.println("Barco id : " + eElement.getAttribute("id"));
			System.out.println("Nombre : " + eElement.getElementsByTagName("nombre").item(0).getTextContent());
			System.out.println("Pirata : " + eElement.getElementsByTagName("pirata").item(0).getTextContent());
			System.out.println("Origen : " + eElement.getElementsByTagName("origen").item(0).getTextContent());
			System.out.println("Destino : " + eElement.getElementsByTagName("destino").item(0).getTextContent());
                        System.out.println("Tripulacion : " + eElement.getElementsByTagName("tripulacion").item(0).getTextContent());
                        System.out.println("Municiones : " + eElement.getElementsByTagName("municiones").item(0).getTextContent());
                        System.out.println("Raciones : " + eElement.getElementsByTagName("raciones").item(0).getTextContent());
                        this.barcotemp = new Barco(eElement.getElementsByTagName("nombre").item(0).getTextContent(),
                                Boolean.parseBoolean(eElement.getElementsByTagName("pirata").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("tripulacion").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("raciones").item(0).getTextContent()),
                                Integer.parseInt(eElement.getElementsByTagName("municiones").item(0).getTextContent()));
                        this.barcotemp.setPuertoOrigen(eElement.getElementsByTagName("origen").item(0).getTextContent());
                        System.out.println("No Llega");
                        }
		}
	}
    } catch (ParserConfigurationException | SAXException | IOException | NumberFormatException | DOMException e) {
    }
  } 
     public void leerMaquinas(int idMaquina){
         try {
 
	File fXmlFile = new File("maquinas1.xml");
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
                                    Cayo cayot = new Cayo();
                                    cayot.setNombre(eLugar.getAttribute("nombre"));
                                    cayot.setCofre(listarTesoros(nLugar));
                                    cayostemp.add(cayot);
                                }else{
                                    ArrayList<Sitio> sitiostemp = new ArrayList<>();
                                    NodeList nSitios = nLugar.getChildNodes();
                                            for(int j=0;j<nSitios.getLength();j++){
                                                Node nSitio = nSitios.item(j);
                                                if(nSitio.getNodeType()==Node.ELEMENT_NODE){
                                                    Element eSitio = (Element) nSitio;
                                                    System.out.println("    Nombre "+ eSitio.getTagName()+ ": "+eSitio.getAttribute("nombre"));
                                                    Sitio sitiot = new Sitio();
                                                    sitiot.setNombre(eSitio.getAttribute("nombre"));
                                                    sitiot.setCofre(listarTesoros(nSitio));
                                                    sitiostemp.add(sitiot);
                                                }
                                            }
                                            Isla islat = new Isla();
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
                 NodeList testemp = eCofre.getElementsByTagName("tesoro");
                 for(int j=0;j<testemp.getLength();j++){
                     System.out.println("Tesoro: "+testemp.item(j).getTextContent());
                     Tesoro tesorotemp = new Tesoro(testemp.item(j).getTextContent());
                     cofretemp.agregarTesoro(tesorotemp);
                 }
                 NodeList maptemp = eCofre.getElementsByTagName("mapa");
                 for(int j=0;j<maptemp.getLength();j++){
                     Node nMapa = maptemp.item(j);
                     if(nMapa.getNodeType()==Node.ELEMENT_NODE){
                         Element eMapa = (Element) nMapa;
                         System.out.println("Destino Mapa: "+eMapa.getElementsByTagName("destino").item(0).getTextContent());
                         if(eMapa.getElementsByTagName("destino").item(0).getTextContent().startsWith("Cayo")){
                             Mapa maptest = new Mapa(eMapa.getElementsByTagName("destino").item(0).getTextContent(),eMapa.getElementsByTagName("maquina").item(0).getTextContent());
                             System.out.println("Esto es un Cayo: "+maptest.getNombreCayo() + " en maquina: " + maptest.getNombreMaquina());
                         }else{
                             Mapa maptest = new Mapa(eMapa.getElementsByTagName("destino").item(0).getTextContent(),eMapa.getElementsByTagName("isla").item(0).getTextContent(),eMapa.getElementsByTagName("maquina").item(0).getTextContent());
                             System.out.println("Esto es una isla: "+maptest.getNombreIsla() + " en maquina: " + maptest.getNombreMaquina());
                         }
                     }
                 }
             }
         }
         return cofretemp;
     }
}
