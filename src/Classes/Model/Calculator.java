package Classes.Model;

import IFaces.CalculatorInterface;

import java.util.Arrays;
import java.util.TreeMap;

public class Calculator implements CalculatorInterface {

    @Override
    public int[] separateOctets(String ip) {
        String[] split = ip.split("\\.");

        if (split.length != 4) {
            System.err.println("Network hat falsche Laenge!");
            System.exit(20);
        }

        int[] octets = new int[4];
        for (int i = 0; i < octets.length; i++) {
            octets[i] = Integer.parseInt(split[i]);
        }

        if (checkIfValidIP(octets)) {
            return octets;
        }
        else {
            System.out.println("Keine valide Adresse!");
            return null;
        }
    }

    @Override
    public int[] toDecimal(String[] binaryIP) {
        int[] ar = new int[4];
        for (int i = 0; i < binaryIP.length; i++) {
            ar[i] = Integer.parseInt(binaryIP[i], 2);
        }
        return ar;
    }

    public String toString(int[] octets) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < octets.length; i++) {
            if (i < 3) {
                sb.append(octets[i]).append('.');
            }
            else {
                sb.append(octets[i]);
            }
        }
        return sb.toString();
    }

    // TODO: Spaeter BitMasking verwenden (wesentlich schneller)
    @Override
    public String[] invertBinaryIP(String[] subnetBinary) {
        String[] ar = new String[4];

        for (int i = 0; i < subnetBinary.length; i++) {
            StringBuilder sb = new StringBuilder(subnetBinary[i]);

            for (int z = 0; z < subnetBinary[i].length(); z++) {

                if (sb.charAt(z) == '1') {
                    sb.setCharAt(z, '0');
                }
                else {
                    sb.setCharAt(z, '1');
                }
            }
            ar[i] = sb.toString();
        }
        return ar;
    }

    @Override
    public long calculateAddressRangeDifference(long firstAddress, long secondAddress) {
        if (firstAddress > secondAddress) {
            return firstAddress - secondAddress;
        }
        else {
            return secondAddress - firstAddress;
        }
    }

    @Override
    public double possibleIPs(String cidr) {
        int bits = Integer.parseInt(cidr.replace("/", ""));
        return Math.pow(2, (32 - bits));
    }

    @Override
    public boolean checkIfInRange(String subnet, String ip) {
        return false;
    }

    @Override
    public boolean checkIfValidIP(int[] ip) {
        return (ip.length == 4) && (Arrays.stream(ip).allMatch(p -> p <= 255)) && (Arrays.stream(ip).allMatch(n -> n >= 0));
    }

    @Override
    public boolean validateSubnet(String binarySubnet) {
        char[] ar = binarySubnet.toCharArray();
        int counter = 0;

        while (counter < ar.length) {
            if (ar[counter] == '1') {
                return true;
            }
            counter++;
        }
        return false;
    }

    @Override
    public long actualAddress(int[] octets) {
        long address = 0;

        for (int i = 0; i < octets.length; i++) {

            switch (i) {
                case 0 -> address += octets[i] * 16777216L;
                case 1 -> address += octets[i] * 65536L;
                case 2 -> address += octets[i] * 256L;
                case 3 -> address += octets[i];
                default -> System.out.println("Addressbereich überstiegen!");
            }
        }
        return address;
    }

    @Override
    public char determineClassByIP(String binaryIP) {
        char[] bits = binaryIP.toCharArray();
        char[] classes = new char[]{'A','B','C','D','E'};
        boolean loop = true;
        int count = 0;

        // Old determination of NetClass
        while (loop && count <= 4) {
            if (bits[count] != '0') {
                count++;
            }
            else {
                loop = false;
            }
        }

        return classes[count];
    }

    public char determineClassBySubnet(int[] subnetOctets) {
        TreeMap<Integer, Character> classes = new TreeMap<>();
        classes.put(255, 'A');
        classes.put(510, 'B');
        classes.put(765, 'C');
        classes.put(1020, 'C');

        int sumOfSubnet = Arrays.stream(subnetOctets).sum();
        return classes.get(classes.ceilingKey(sumOfSubnet));
    }

    @Override
    public String nextNet(int[] ip, int[] subnet) {
        return null;
    }

    @Override
    public String[] calculateNetID(String binaryIP, String subnetBinary) {
        // TODO: Logical AND anstatt von langer if Abfrage implementieren
        StringBuilder sb = new StringBuilder();

        if (binaryIP.length() != subnetBinary.length()) {
            System.out.println("calcualteNetID Fehlgeschlagen wegen Length Mismatch!");
            System.exit(-1);
            return null;
        }

        else {
            for (int i = 0; i < binaryIP.length(); i++) {
                if (binaryIP.charAt(i) == '1' && subnetBinary.charAt(i) == '1') {
                    sb.append('1');
                }
                else {
                    sb.append('0');
                }
            }
            return sb.toString().split("(?<=\\G.{8})");
        }
    }

    // Alternative Implementierung: Schaue wichtiges Oktet an, anstatt Bits
    @Override
    public int[] calculateNetID(int cidr, int[] ipOctets) {

        TreeMap<Integer, Integer> getOctectNumber = new TreeMap<>();
        getOctectNumber.put(8, 0);
        getOctectNumber.put(16, 1);
        getOctectNumber.put(24, 2);
        getOctectNumber.put(32, 3);

        int[] netID = new int[4];
        int wichtigesOctetSchrittweite = (int) Math.pow(2, getOctectNumber.ceilingKey(cidr) - cidr);
        int octCounter = getOctectNumber.get((getOctectNumber.ceilingKey(cidr)));
        int wichtigesOctet = ipOctets[octCounter];

        for (int i = 0; i < ipOctets.length; i++) {
            if (i < octCounter) {
                netID[i] = ipOctets[i];
            }
            else if (i == octCounter) {
                int next = 0;

                while (next < wichtigesOctet) {
                    next += wichtigesOctetSchrittweite;
                } if (next == wichtigesOctet) {
                    netID[i] = next;
                } else {
                    netID[i] = next - wichtigesOctetSchrittweite;
                }
            }
            else {
                netID[i] = 0;
            }
        }
        return netID;
    }

    @Override
    public String[] determineBinaryBroadcastIP(String[] netID, String[] invertedSubnetMask) {
        StringBuilder sb = new StringBuilder();

        if (netID.length == 4 && invertedSubnetMask.length == 4) {
            for (int i = 0; i < netID.length; i++) {
                for (int j = 0; j < netID[i].length(); j++) {
                    if (invertedSubnetMask[i].charAt(j) == '0' && netID[i].charAt(j) == '0') {
                        sb.append('0');
                    }
                    else {
                        sb.append('1');
                    }
                }
            }
        }
        return sb.toString().split("(?<=\\G.{8})");
    }

    @Override
    public String[] binaryIP(int[] ip) {
        String[] ar = new String[]{"00000000", "00000000", "00000000", "00000000"};

        for (int i = 0; i < ip.length; i++) {

            String bin = Integer.toBinaryString(ip[i]);
            StringBuilder bin2String = new StringBuilder(ar[i]);

            int offset = (ar[i].length() - bin.length());
            int counter = 0;

            for (int z = offset; z < bin2String.length(); z++) {
                bin2String.setCharAt(z, bin.charAt(counter++));
            }

            ar[i] = bin2String.toString();
        }

        return ar;
    }

    @Override
    public String cidrNotation(String binaryMerged) {

        // Positives Lookahead mit einem Array Limit von 2 (separator char ist splitted, nicht der folgende char + nur 2 Arrays moeglich)
        String[] splitAfterZero = binaryMerged.split("(?=0)", 2);

        if (validateSubnet(splitAfterZero[1])) {
            System.out.println("Subnet " + binaryMerged + " ist nicht Korrekt!\n" +
                    "");
            System.exit(20);
            return null;
        }

        else {
            return "/" + splitAfterZero[0].length();
        }

    }

    @Override
    public String toMergedString(String[] binaryIp) {
        StringBuilder merged = new StringBuilder();

        for (String binOct : binaryIp) {
            merged.append(binOct);
        }

        return merged.toString();
    }
}