package Classes.Model.File;

import Classes.Model.Network;
import IFaces.XMLInterface;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser implements XMLInterface {

    private final List<Network> networks = new ArrayList<>();

    public List<Network> parseIPs(String file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList nl = doc.getElementsByTagName("network");

            for (int i = 0; i < nl.getLength(); i++) {
                Element ipElement = (Element) nl.item(i);
                networks.add(new Network(ipElement.getTextContent(), ipElement.getAttribute("ip")));
            }
        }

        catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("Error: " + ex);
        }
        return networks;
    }
}
