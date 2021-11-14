package Classes.Control;

import Classes.Model.Calculator;
import Classes.Model.Console;
import Classes.Model.Network;
import IFaces.CalculatorInterface;

import java.util.List;

public class Selector {
    private static final CalculatorInterface calc = new Calculator();
    private static final Console con = new Console();


    public static void fillNetworkObjects(List<Network> networks) {

        if (!(networks.isEmpty())) {
            for (Network network : networks) {

                network.setOctets(calc.separateOctets(network.getIp()));
                network.setSubnetOctets(calc.separateOctets(network.getSubnet()));
                network.setRealAddress(calc.actualAddress(network.getOctets()));
                network.setBinaryIP(calc.binaryIP(network.getOctets()));
                network.setBinarySubnet(calc.binaryIP(network.getSubnetOctets()));
                network.setCidr(calc.cidrNotation(calc.toMergedString(network.getBinarySubnet())));
                network.setNetClassIP(calc.determineClassByIP(calc.toMergedString(network.getBinaryIP())));
                network.setNetClassSubnet(calc.determineClassBySubnet(network.getSubnetOctets()));
                network.setRange((long)calc.possibleIPs(network.getCidr()));
                network.setBinaryNetID(
                        calc.calculateNetID(
                                calc.toMergedString(network.getBinaryIP()),
                                calc.toMergedString(network.getBinarySubnet())
                        )
                );
                network.setNetIDOctets(calc.toDecimal(network.getBinaryNetID()));
                network.setNetID(calc.toString(network.getNetIDOctets()));

                // TODO: Herausfinden wieso binarySubnet sich auch nach Methode invertiert
                //network.setInvertedSubnet(calc.invertBinaryIP(network.getBinarySubnet()));
            }
        }
    }

    public static String printBinary(String[] binaryIP) {
        StringBuilder joinedString = new StringBuilder();
        int i = 0;

        while (i < binaryIP.length) {
            if (i == 3) {
                joinedString.append(binaryIP[i]);
            }
            else {
                joinedString.append(binaryIP[i]).append(" ");
            }
            i++;
        }

        return joinedString.toString();
    }

    public static void printAll(List<Network> networks) {
        for (Network network : networks) {
            System.out.printf("""
                            _________________________________________________________
                            %35s
                            %-20s %s
                            %-20s %s
                            %-20s %d
                            
                            %35s
                            %-20s %s
                            %-20s %s
                            %-20s %s
                            
                            %35s
                            %-20s %s
                            %-20s %s
                            %-20s %d
                            %-20s %c
                            %-20s %c
                            %-20s %b
                            
                            
                            """,
                    "++++IP Properties++++",
                    "IP:", network.getIp(),
                    "Binary IP view:", Selector.printBinary(network.getBinaryIP()),
                    "Real Address:", network.getRealAddress(),

                    "++++Subnet Properties++++",
                    "CIDR:", network.getCidr(),
                    "Subnet:", network.getSubnet(),
                    "Binary Subnet view:", Selector.printBinary(network.getBinarySubnet()),

                    "++++Network Properties++++",
                    "Net ID:", network.getNetID(),
                    "Binary Net ID", Selector.printBinary(network.getBinaryNetID()),
                    "Range:", network.getRange(),
                    "Net-Class by IP:", network.getNetClassIP(),
                    "Net-Class by Subnet:", network.getNetClassSubnet(),
                    "Validity:", calc.checkIfValidIP(network.getOctets()));
        }
    }
}