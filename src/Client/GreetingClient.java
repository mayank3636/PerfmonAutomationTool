package Client;

//File Name GreetingClient.java
import java.net.*;
import java.io.*;

public class GreetingClient implements Runnable {
	public static final int BUFFER_SIZE = 1024 * 4;
	private byte[] buffer;

	@Override
	public void run() {
		String serverName = "172.22.20.245";
		int port = 443;
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			File f = new File(GUI.path + "\\output.csv");
			System.out.println(GUI.path + "\\output.csv");
			long expect = f.length();
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()))) {

				byte[] buffer = new byte[client.getSendBufferSize()];
				dos.writeUTF(f.getName());
				dos.writeLong(expect);

				long left = expect;
				int inlen = 0;
				while (left > 0 && (inlen = bis.read(buffer, 0, (int) Math.min(left, buffer.length))) >= 0) {
					dos.write(buffer, 0, inlen);
					left -= inlen;
				}
				dos.flush();
				if (left > 0) {
					throw new IllegalStateException("We expected " + expect + " bytes but came up short by " + left);
				}
				if (bis.read() >= 0) {
					throw new IllegalStateException(
							"We expected only " + expect + " bytes, but additional data has been added to the file");
				}
			}

			// OutputStream outToServer = client.getOutputStream();
			// DataOutputStream out = new DataOutputStream(outToServer);
			//
			//
			// BufferedInputStream in = new BufferedInputStream(new
			// FileInputStream(f));
			// buffer = new byte[BUFFER_SIZE];
			// int len = 0;
			// while ((len = in.read(buffer)) > 0) {
			// out.write(buffer, 0, len);
			// //System.out.print("#");
			// }
			// InputStream inFromServer = client.getInputStream();
			// DataInputStream in1 = new DataInputStream(inFromServer);
			//
			// System.out.println("Server says " + in1.readUTF());
			client.close();
			// in.close();
		} catch (IOException e) {
			System.out.println("172.22.20.246 is Down currently or Server is not Running");
		}

	}
}