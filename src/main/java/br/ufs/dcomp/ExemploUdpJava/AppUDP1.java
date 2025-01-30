package br.ufs.dcomp.ExemploUdpJava;

import java.net.*;
import java.util.Scanner;

public class AppUDP1 {

    public static void main(String[] args) throws SocketException {
        try{
            System.out.print("[ Alocando porta UDP      ..................  ");
    	    DatagramSocket socket = new DatagramSocket(10000);
            System.out.println("[OK] ]");
            
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("Enviar: ");
                String msg = sc.nextLine();
                
                byte[] msg_buf = msg.getBytes();
                int msg_size = msg_buf.length;
                InetAddress destination_address = InetAddress.getLocalHost();
                int destination_port = 20000; 
    
                System.out.print("[ Montando datagrama UDP  ..................  ");
                DatagramPacket pack = new DatagramPacket(msg_buf, msg_size, destination_address, destination_port);
                System.out.println("[OK] ]");
                
                System.out.print("[ Enviando datagrama UDP  ..................  ");
                socket.send(pack);
                System.out.println("[OK] ]");
                
                byte[] buf = new byte[20];
                DatagramPacket pack2 = new DatagramPacket(buf, buf.length);
    
                System.out.print("[ Aguardando recebimento de mensagem  ..................  ");
                socket.receive(pack2);   // BLOQUEANTE
                System.out.println("[OK] ]");
                
                byte[] received_data = pack2.getData();
                String received_msg = new String(received_data); 
                InetAddress origin_address = pack2.getAddress();
                int origin_port = pack2.getPort();
                
                System.out.println("  Mensagem:             "+received_msg);
                System.out.println("  Endereço de origem:   "+origin_address.getHostAddress());
                System.out.println("  Porta de origem:      "+origin_port);
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }    
    }
}