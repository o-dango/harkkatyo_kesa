/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkatyö;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author camilla
 */
public class webReader {
    private Document doc;
    private HashMap<String, String> map;
    

    public HashMap<String, String> getMap() {
        return map;
    }
    
    public webReader(String content) {
        
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(new InputSource(new StringReader(content)));
            doc.getDocumentElement().normalize();
            map = new HashMap();
            parseCurrentData();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(webReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void parseCurrentData() {
        
        String statement;
        NodeList address = doc.getElementsByTagName("address");
        NodeList codes = doc.getElementsByTagName("code");
        NodeList cities = doc.getElementsByTagName("city");
        NodeList availability = doc.getElementsByTagName("availability");
        NodeList postoffices = doc.getElementsByTagName("postoffice");
        NodeList lats = doc.getElementsByTagName("lat");
        NodeList lngs = doc.getElementsByTagName("lng");
        
        
        System.out.println(address.getLength());
        for(int i = 0; i < address.getLength(); i++) {
            
            Node node_address = address.item(i);
            Node node_codes = codes.item(i);
            Node node_cities = cities.item(i);
            Node node_availability = availability.item(i);
            Node node_postoffices = postoffices.item(i);
            Node node_lats = lats.item(i);
            Node node_lngs = lngs.item(i);
            
            
            Element e_ad = (Element) node_address;
            Element e_co = (Element) node_codes;
            Element e_ci = (Element) node_cities;
            Element e_av = (Element) node_availability;
            Element e_po = (Element) node_postoffices;
            Element e_la = (Element) node_lats;
            Element e_ln = (Element) node_lngs;
            
            
            
            int temp = i + 1;
            System.out.println(e_ad.getTextContent());
            System.out.println(e_co.getTextContent());
            System.out.println(e_ci.getTextContent());
            System.out.println(e_av.getTextContent());
            System.out.println(e_po.getTextContent());
            System.out.println(e_la.getTextContent());
            System.out.println(e_ln.getTextContent());
            
//            Sqlite sql = Sqlite.getInstance();
            
//            statement = "INSERT INTO Postiautomaatti(automaattiID, kaupunki, nimi) "
//                    + "VALUES(" + temp + ", '" + e_ci.getTextContent() + "', '" + e_po.getTextContent() + "');";
//            System.out.println(statement);
//            sql.addData(statement);
            
            Sqlite sql2 = Sqlite.getInstance();
            
            statement = "UPDATE Osoite "
                    + "SET postinro = '" + e_co.getTextContent() + "' "
                    + "WHERE automaattiID = " + temp + ";";
            System.out.println(statement);
            sql2.addData(statement);
            
//            Sqlite sql3 = Sqlite.getInstance();
//            
//            statement = "INSERT INTO Koordinaatit(leveys, pituus, automaattiID) "
//                    + "VALUES(" + e_la.getTextContent() + ", " + e_ln.getTextContent() + ", "
//                    + temp + ");";
//            System.out.println(statement);
//            sql3.addData(statement);
//            
//            Sqlite sql4 = Sqlite.getInstance();
//            
//            statement = "INSERT INTO Aukioloaika(automaattiID, saatavilla) "
//                    + "VALUES(" + temp + ", '" + e_av.getTextContent() + "');";
//            System.out.println(statement);
//            sql4.addData(statement);
        
        }
        
    }
    
}
