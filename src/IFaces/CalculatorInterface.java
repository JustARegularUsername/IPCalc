package IFaces;

public interface CalculatorInterface {

    int[] separateOctets(String ip);
    int[] toDecimal(String[] binaryIP);
    String[] invertBinaryIP(String[] subnetBinary);
    long calculateAddressRangeDifference(long firstAddress, long secondAddress);
    double possibleIPs(String subnet);

    boolean checkIfInRange(String subnet, String ip);
    boolean checkIfValidIP(int[] ip);
    boolean validateSubnet(String binarySubnet);

    long actualAddress(int[] octets);
    char determineClassByIP(String binaryIP);
    char determineClassBySubnet(int[] subnetOctets);

    String nextNet(int[] ip, int[] subnet);
    String[] calculateNetID(String binaryIP, String subnetBinary);
    int[] calculateNetID(int cidr, int[] ipOctets);

    String[] determineBinaryBroadcastIP(String[] netID, String[] invertedSubnetMask);
    String[] binaryIP(int[] ip);
    String cidrNotation(String binarySubnet);
    String toMergedString(String[] binaryIp);
    String toString(int[] netIDOctets);
}
