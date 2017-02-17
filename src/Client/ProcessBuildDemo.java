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
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.util.Arrays;


/**
 * @author M1030090,Mayank Upadhyaya
 *
 */
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
f= new File(filePath+"\\"+"logstash.conf");

fw= new FileWriter(f);
bw= new BufferedWriter (fw);
String x="input {\r\n  file {\r\n    path => \""+filePath+"\\output.csv"+"\"\r\n    start_position => \"beginning\"\r\n   sincedb_path => \"/dev/null\"\r\n  }\r\n}\r\nfilter {\r\n  csv {\r\n      separator => \",\"  \r\n\t columns => [\"dateTime\",\"ILWSBClient % Processor Time\",\"ILPlatform % Processor Time\",\"ILPlatform # Bytes in all Heaps\",\"ILWSBClient # Bytes in all Heaps\",\"ILPlatform # Total committed Bytes\",\"ILWSBClient # Total committed Bytes\",\"ILPlatform % Time in GC\",\"ILWSBClient % Time in GC\"]\r\n  }\r\n   date {\r\nmatch => [ \"dateTime\", \"MM/dd/yyyy HH:mm:ss.SSS\", \"ISO8601\" ]\r\n  }\r\n  mutate {\r\nconvert => { \"ILWSBClient % Processor Time\" => \"integer\" }\r\nconvert => { \"ILPlatform % Processor Time\" => \"integer\" }\r\nconvert => { \"Total% Processor Time\" => \"integer\" }\r\nconvert => { \"ILWSBClient # Bytes in all Heaps\" => \"integer\" }\r\nconvert => { \"ILPlatform # Bytes in all Heaps\" => \"integer\" }\r\nconvert => { \"ILWSBClient % Time in GC\" => \"integer\" }\r\nconvert => { \"ILPlatform % Time in GC\" => \"integer\" }\r\nconvert => { \"ILWSBClient # Total committed Bytes\" => \"integer\" }\r\nconvert => { \"ILPlatform # Total committed Bytes\" => \"integer\" }\r\n}\r\n  }\r\noutput {\r\n   elasticsearch {\r\n     hosts => \"http://localhost:9200\"\r\n  }\r\nstdout {}\r\n}\r\n\r\n";	
bw.write(x);
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
       
        
	      
	      f = new File(filePath+"\\"+"logStash.bat");
	      fw=new FileWriter(f);
	      bw= new BufferedWriter(fw);
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
	      bw.write("cd %Logstash%\\bin");
	      bw.write('\n');
	      bw.write("logstash.bat -f "+filePath+"\\"+"logstash.conf");
	      bw.flush();
	      bw.close();
	      String[] command1 = {"CMD","/c",filePath+"\\"+"logStash.bat"};	      
	      ProcessBuilder probuilder1 = new ProcessBuilder( command1 );
	        //You can set up your work directory
	        probuilder1.directory(new File("c:\\demo"));

	        Process process1 = probuilder1.start();

	        //Read out dir output
	        InputStream is1 = process1.getInputStream();
	        InputStreamReader isr1 = new InputStreamReader(is1);
	        BufferedReader br1 = new BufferedReader(isr1);
	        String line1;
	        System.out.printf("Output of running %s is:\n",
	                Arrays.toString(command1));
	        while ((line1 = br.readLine()) != null) {
	            System.out.println(line1);
	        }
	      
	      
        while(true){
        try{
        	t1.run();
        }catch (IllegalStateException e){
        	System.out.println("More Data will be added in Next Cycle");
        }
        	if(g.flag==true){
        		OutputStream out=process.getOutputStream();
        		OutputStreamWriter osr= new OutputStreamWriter(out);
        		//String[] command1={"CMD","/c","taskkill /F /IM cmd.exe"};
        		//ProcessBuilder probuilder1= new ProcessBuilder(command1);
        		//probuilder1.start();
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