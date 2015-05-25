/*
 * Copyright 2015 Corncob.
 *
 */
package listnetwork;
import java.net.*;
import java.util.*;
import static java.lang.System.out;
/**
 *
 * @author Corncob
 */
public class ListNetwork {

     public static void main(String args[]) throws SocketException {
        ArrayList<NetworkInterface> workingNetwork = getRealInterfaces();
        for(NetworkInterface net:workingNetwork)
        {
            out.println("Network Name: "+ net.getDisplayName());
            out.println("IP address: "+getInet4Address(net).getHostAddress());
        }
    }

    
    public static ArrayList<NetworkInterface> getRealInterfaces() throws SocketException
    {
        Enumeration<NetworkInterface> netEnums = NetworkInterface.getNetworkInterfaces();
        ArrayList<NetworkInterface> nets = Collections.list(netEnums);
        ArrayList<NetworkInterface> result = new ArrayList<NetworkInterface> ();
         for(NetworkInterface net : nets)
         {
             Enumeration<InetAddress> inetNum = net.getInetAddresses();
             ArrayList<InetAddress> inets = Collections.list(inetNum);
             if(inets.size()>0 && !net.isVirtual() && net.isUp() && !net.isLoopback() && getInet4Address(net)!=null )
             {
                 result.add(net);
             }
         }
         return result;
        
    }
    public static Inet4Address getInet4Address(NetworkInterface net)
    {
        ArrayList<Inet4Address> result = new ArrayList<Inet4Address>();
        Enumeration<InetAddress> inetAddresses = net.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            if(inetAddress instanceof Inet4Address)
                return (Inet4Address)inetAddress;
        }
        return null;
    }
}
