import Classes.Control.Controller;
import Classes.View.GUI;

import javax.swing.text.View;

public class Main {

    public static void main(String[] args) {
        Controller con = new Controller(
                "/home/daniel/IdeaProjects/IPs/src/ipfile.xml",
                null);
        GUI gui = new GUI(con);
        con.lade();
    }
}