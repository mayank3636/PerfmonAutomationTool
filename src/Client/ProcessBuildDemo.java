package Client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

public class ProcessBuildDemo { 
    public static void main(String [] args) throws IOException {
    	String filePath="";
    	GUI g= new GUI("Perfomance TEst");
    	g.GUIInvoke();
    	//SwingControlDemo swd= new SwingControlDemo("Start");
    	while(true){
    		if(g.path==""&&g.testName==""){
        		try {
    				Thread.sleep(1000);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
        	}else{
        		filePath=g.path;	
        		break;
        	}	
    	}
    	
    	
    	
    	
File f1= new File(filePath+"\\"+"ILP.cfg");
FileWriter fw1= new FileWriter(f1);
BufferedWriter bw1= new BufferedWriter(fw1);
bw1.write("\\process(ILWSBClient)\\% processor time");
bw1.write('\n');
bw1.write("\\process(ILPlatform)\\% processor time");
bw1.write('\n');
bw1.write("\\.NET CLR Memory(ILPlatform)\\# Bytes in all Heaps");
bw1.write('\n');
bw1.write("\\.NET CLR Memory(ILWSBClient)\\# Bytes in all Heaps");
bw1.write('\n');
bw1.write("\\.NET CLR Memory(ILPlatform)\\# Total committed Bytes");
bw1.write('\n');
bw1.write("\\.NET CLR Memory(ILWSBClient)\\# Total committed Bytes");
bw1.write('\n');
bw1.write("\\.NET CLR Memory(ILPlatform)\\% Time in GC");
bw1.write('\n');
bw1.write("\\.NET CLR Memory(ILWSBClient)\\% Time in GC");
bw1.flush();
bw1.close();
File f= new File(filePath+"\\"+"ILP.bat");

FileWriter fw= new FileWriter(f);
BufferedWriter bw= new BufferedWriter (fw);
bw.write("@echo off");
bw.write('\n');
bw.write(":: BatchGotAdmin (Run as Admin code starts)");
bw.write('\n');
bw.write("REM --> Check for permissions");
bw.write('\n');
bw.write(">nul 2>&1"+" "+'"'+"%SYSTEMROOT%\\system32\\cacls.exe"+'"'+" "+'"'+"%SYSTEMROOT%\\system32\\config\\system"+'"'+'\n');
bw.write('\n');
bw.write("if"+" "+"'"+"%errorlevel%"+"'"+" "+ "NEQ"+" "+"'"+0+"'"+" "+"(");	
bw.write('\n');
bw.write("echo Requesting administrative privileges...");		
bw.write('\n');
bw.write("goto UACPrompt");		
bw.write('\n');
bw.write(") else ( goto gotAdmin )");		
bw.write('\n');
bw.write(":UACPrompt");	
bw.write('\n');
bw.write("echo Set UAC = CreateObject^("+'"'+"Shell.Application"+'"'+"^) >"+" "+'"'+"%temp%\\getadmin.vbs"+'"'+'\n');
bw.write('\n');		
bw.write("echo UAC.ShellExecute"+" "+'"'+"%~s0"+'"'+","+ '"'+'"'+","+ '"'+'"'+","+'"'+"runas"+'"'+","+ "1 >>"+" "+'"'+"%temp%\\getadmin.vbs"+'"'+" ");		
bw.write('\n');
bw.write('"'+"%temp%\\getadmin.vbs"+'"'+" ");
bw.write('\n');
bw.write("exit /B");		
bw.write('\n');
bw.write(":gotAdmin");
bw.write('\n');
bw.write("if exist "+" "+'"'+"%temp%\\getadmin.vbs"+'"'+" "+ "( del "+'"'+"%temp%\\getadmin.vbs"+'"'+" )");
bw.write('\n');
bw.write("pushd "+'"'+"%CD%"+'"');
bw.write('\n');
bw.write("CD /D"+'"'+ "%~dp0"+'"');		
bw.write('\n');
bw.write(":: BatchGotAdmin (Run as Admin code ends)");		
bw.write('\n');
bw.write(":: Your codes should start from the following line");		
bw.write('\n');
bw.write("cd "+" "+filePath);
bw.write('\n');
bw.write("typeperf -cf"+" "+'"'+"ILP.cfg"+'"'+" "+"-f csv");
bw.flush();
bw.close();
    	
String[] command = {"CMD","/c",filePath+"\\"+"ILP.bat"};

        ProcessBuilder probuilder = new ProcessBuilder( command );
        //You can set up your work directory
        probuilder.directory(new File("c:\\demo"));

        Process process = probuilder.start();

        //Read out dir output
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        System.out.printf("Output of running %s is:\n",
                Arrays.toString(command));
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        //Wait to get exit value
        GreetingClient t1= new GreetingClient();
        Socket client1 = new Socket("172.22.20.246", 443);
        OutputStream outTo=client1.getOutputStream();
        DataOutputStream outtoServer = new DataOutputStream(outTo);
        outtoServer.writeUTF(g.testName);
        InputStream inFromServer = client1.getInputStream();
	      DataInputStream in = new DataInputStream(inFromServer);  
	      System.out.println("Server says " + in.readUTF());
	      client1.close();
	      try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        while(true){
        	t1.run();
        	if(g.flag==true){
        		OutputStream out=process.getOutputStream();
        		OutputStreamWriter osr= new OutputStreamWriter(out);
        		String[] command1={"CMD","/c","taskkill /F /IM cmd.exe"};
        		ProcessBuilder probuilder1= new ProcessBuilder(command1);
        		probuilder1.start();
        		osr.write('2');
        		process.destroyForcibly();
        		System.exit(1);
        		System.exit(0);
        		break;
        	}
       try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        }
    
    }
}