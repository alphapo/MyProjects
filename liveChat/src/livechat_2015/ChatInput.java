package livechat_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ChatInput {

	public static final int NAME = 0;
	public static final int MESSAGE = 1;
	public static final int ULIST = 2;
	public static final int LIST = 3;
	public static final int QUIT = 4;
	public static final int NAMEOK = 5;
	public static final int NAMEBAD = 6;
	public static final int GREETING = 7;
	public static final int PRIVATEMESSAGE = 8;
	BufferedReader is;
	String strMsg;
	String strName;
	ArrayList<String> userList;
	int lastCommand;
	
	
	
	public String getStrMsg() {
		return strMsg;
	}

	public String getStrName() {
		return strName;
	}

	public ArrayList<String> getUserList() {
		return userList;
	}

	public int getLastCommand() {
		return lastCommand;
	}

	public ChatInput(Socket s) throws IOException {
		is = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	public int getCommand() throws IOException{
		String line = is.readLine();
		
		if (line.equals("NAME")){
			strName = is.readLine();
			lastCommand = NAME; 
		}
		
		else if (line.equals("NAMEOK")){
			lastCommand = NAMEOK;
		}
		
		else if (line.equals("NAMEBAD")){
			lastCommand = NAMEBAD;
		}
		
		else if (line.equals("ULIST")){
			lastCommand = ULIST;
		}
		
		else if (line.equals("LIST")){
			while (!(line = is.readLine()).equals(".")){
				userList.add(line);
			}
			lastCommand = LIST;
		}
		
		else if (line.equals("MESSAGE")){
			strName = is.readLine();
			strMsg = is.readLine();
			lastCommand = MESSAGE;
		}
		
		else if (line.equals("QUIT")){
			lastCommand = QUIT;
		}
		
		return lastCommand;
	}
	

}
