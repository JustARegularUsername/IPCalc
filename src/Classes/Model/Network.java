package Classes.Model;

import IFaces.IPInterface;

public class Network implements IPInterface {

    private int[] octets = new int[4];
    private int[] subnetOctets = new int[4];
    private int[] netIDOctets;
    private int[] broadCastOctets;
    private long range;
    private long realAddress;

    private char netClassIP;
    private char netClassSubnet;
    private final String ip;
    private final String subnet;
    private String cidr;
    private String netID;
    private String broadCast;
    private String wildcardSubnet;
    private String[] binaryIP;
    private String[] binaryNetID;
    private String[] binarybroadCast;
    private String[] binarySubnet;
    private String[] invertedSubnet;


    public Network(String subnet, String ip) {
        this.subnet = subnet;
        this.ip = ip;
    }


    @Override
    public int[] getOctets() {
        return this.octets;
    }

    @Override
    public long getRealAddress() {
        return this.realAddress;
    }

    @Override
    public char getNetClassIP() {
        return this.netClassIP;
    }

    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public String getSubnet() {
        return this.subnet;
    }

    @Override
    public String getCidr() {
        return this.cidr;
    }

    @Override
    public String[] getBinaryIP() {
        return this.binaryIP;
    }

    public void setOctets(int[] octets) {
        this.octets = octets;
    }

    public void setRealAddress(long realAddress) {
        this.realAddress = realAddress;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public void setNetClassIP(char netClassIP) {
        this.netClassIP = netClassIP;
    }

    public void setBinaryIP(String[] binaryIP) {
        this.binaryIP = binaryIP;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    public String[] getBinaryNetID() {
        return binaryNetID;
    }

    public void setBinaryNetID(String[] binaryNetID) {
        this.binaryNetID = binaryNetID;
    }

    public String[] getBinarybroadCast() {
        return binarybroadCast;
    }

    public void setBinarybroadCast(String[] binarybroadCast) {
        this.binarybroadCast = binarybroadCast;
    }

    public String[] getBinarySubnet() {
        return this.binarySubnet;
    }

    public void setBinarySubnet(String[] binarySubnet) {
        this.binarySubnet = binarySubnet;
    }

    public int[] getSubnetOctets() {
        return subnetOctets;
    }

    public void setSubnetOctets(int[] subnetOctets) {
        this.subnetOctets = subnetOctets;
    }

    public char getNetClassSubnet() {
        return this.netClassSubnet;
    }

    public void setNetClassSubnet(char netClassSubnet) {
        this.netClassSubnet = netClassSubnet;
    }

    public String[] getInvertedSubnet() {
        return this.invertedSubnet;
    }

    public void setInvertedSubnet(String[] invertedSubnet) {
        this.invertedSubnet = invertedSubnet;
    }

    public int[] getNetIDOctets() {
        return netIDOctets;
    }

    public void setNetIDOctets(int[] netIDOctets) {
        this.netIDOctets = netIDOctets;
    }

    public int[] getBroadCastOctets() {
        return broadCastOctets;
    }

    public void setBroadCastOctets(int[] broadCastOctets) {
        this.broadCastOctets = broadCastOctets;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public String getBroadCast() {
        return this.broadCast;
    }

    public void setBroadCast(String broadCast) {
        this.broadCast = broadCast;
    }

    public String getWildcardSubnet() {
        return wildcardSubnet;
    }

    public void setWildcardSubnet(String wildcardSubnet) {
        this.wildcardSubnet = wildcardSubnet;
    }
}