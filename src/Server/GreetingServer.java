package Server;
// File Name GreetingServer.java
import java.net.*;
import java.io.*;
/**
 * @author M1030090,Mayank Upadhyaya
 *
 */
public class GreetingServer extends Thread {
   private ServerSocket serverSocket;
 public static  String name="";
   public GreetingServer(int port) throws IOException {
      serverSocket = new ServerSocket(port);
    //  serverSocket.setSoTimeout(10000);
   }

   public void run() {
      while(true) {
         try {
        	
            System.out.println("Waiting for client on port " + 
               serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            
            System.out.println("Just connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            String clinetResponse=in.readUTF();
            System.out.println(clinetResponse);
            if(clinetResponse.contains(",")==false){
            	 name=clinetResponse;      
            }else if(clinetResponse.contains(",")==true){
            	File f= new File("F:\\SPLUNK logs\\"+name+".csv");	
            	BufferedOutputStream bos= new BufferedOutputStream(new FileOutputStream(f));
            	bos.write(clinetResponse.getBytes());
            	bos.close();
            	//FileWriter fw= new FileWriter(f);
                //fw.write(clinetResponse);
                //fw.flush();	
            }
            //System.out.println(in.readUTF());
            
            
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
               + "\nGoodbye!");
            server.close();
            
         }catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         }
      }
   }
   
   public static void main(String [] args) {
      int port = 443;
      try {
         Thread t = new GreetingServer(port);
         t.start();
      }catch(IOException e) {
         e.printStackTrace();
      }
   }
}