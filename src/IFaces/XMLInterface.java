package IFaces;

import Classes.Model.Network;

import java.util.List;

public interface XMLInterface {
    List<Network> parseIPs(String file);
}
