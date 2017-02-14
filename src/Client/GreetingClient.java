package Client;

//File Name GreetingClient.java
import java.net.*;
import java.io.*;

public class GreetingClient implements Runnable {

@Override
public void run() {
	String serverName = "172.22.20.246";
	   int port = 443;
	   try {
	      System.out.println("Connecting to " + serverName + " on port " + port);
	      Socket client = new Socket(serverName, port);
	      
	      System.out.println("Just connected to " + client.getRemoteSocketAddress());
	      OutputStream outToServer = client.getOutputStream();
	      DataOutputStream out = new DataOutputStream(outToServer);
	      
	      File f= new File(GUI.path+"\\output.csv");
	      System.out.println(GUI.path+"\\output.csv");
	      FileReader fr= new FileReader(f);
	      BufferedReader br= new BufferedReader(fr);
	      int output=0;
	      String finalOutput="";
	      while((output=br.read())!=-1){
	    	finalOutput+=(char)output;
	      }
	      System.out.println(finalOutput);
	      out.writeUTF(finalOutput);
	      InputStream inFromServer = client.getInputStream();
	      DataInputStream in = new DataInputStream(inFromServer);
	      
	      System.out.println("Server says " + in.readUTF());
	      client.close();
	   }catch(IOException e) {
	      e.printStackTrace();
	   }
	
}
}