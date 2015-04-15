package livechat_2015;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ChatOutput {
	PrintWriter os;

	public ChatOutput(Socket s) throws IOException {
		this.os = new PrintWriter(s.getOutputStream(), true);
	}
	
	

	public synchronized void sendName(String name ){
		os.println("NAME");
		os.println(name);
	}
	
	public synchronized void sendMessage(String name, String message){
		os.println("MESSAGE");
		os.println(name);
		os.println(message);
	}
	
	public synchronized void sendNameOk (){
		os.println("NAMEOK");
	}
	
	public synchronized void sendNameBad (){
		os.println("NAMEBAD");
	}
	
	public synchronized void sendList(ArrayList <String> users){
		os.println("LIST");
		for(String user: users){
			os.println(user);
		}
		os.println(".");
	}
	
	public synchronized void sendUList() {
		os.println("ULIST");
	}
	
	public synchronized void sendQuit (){
		os.println("QUIT");
	}
}
