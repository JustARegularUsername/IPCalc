package IFaces;

public interface IPInterface {
    char getNetClassIP();
    char getNetClassSubnet();
    long getRealAddress();
    int[] getOctets();

    String getIp();
    String getSubnet();
    String getCidr();
    String[] getBinaryIP();
}
