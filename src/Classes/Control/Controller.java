package Classes.Control;

import Classes.Model.File.JSONParser;
import Classes.Model.File.XMLParser;
import Classes.Model.Console;
import Classes.Model.Network;

import java.util.List;

public class Controller {
    private String pfadXML;
    private String pfadJSON;
    private XMLParser xmlParser;
    private JSONParser jsonParser;
    private List<Network> networks;
    private Console console;

    public Controller(String pfadXML, String pfadJSON) {
        this.pfadXML = pfadXML;
        this.pfadJSON = pfadJSON;
        this.xmlParser = new XMLParser();
        this.jsonParser = new JSONParser();
    }

    // TODO: JSON implementieren
    public void lade() {
        this.networks = xmlParser.parseIPs(this.pfadXML);
        Selector.fillNetworkObjects(networks);
        Selector.printAll(networks);
    }

    // TODO: Kreiere speicher methode fuer XML & JSON
    public void speicher() {

    }

    public void setPfadXML(String pfadXML) {
        this.pfadXML = pfadXML;
    }

    public void setPfadJSON(String pfadJSON) {
        this.pfadJSON = pfadJSON;
    }

    public void setXmlParser(XMLParser xmlParser) {
        this.xmlParser = xmlParser;
    }

    public void setJsonParser(JSONParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public void setNetworks(List<Network> networks) {
        this.networks = networks;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public String getPfadXML() {
        return pfadXML;
    }

    public String getPfadJSON() {
        return pfadJSON;
    }

    public XMLParser getXmlParser() {
        return xmlParser;
    }

    public JSONParser getJsonParser() {
        return jsonParser;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public Console getConsole() {
        return console;
    }
}
