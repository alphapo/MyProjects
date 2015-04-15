package livechat_2015;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCore extends Thread {

	private int port;
	ServerSocket ss;
	private boolean stop = false;
	private IChatLogger logger=null;
	
	public ServerCore(int port) throws IOException {
		this.port = port;
		ss = new ServerSocket(port);
		logger = new TextChatLogger();
		logger.systemMessage("Server started...");
		start();
	}

	public void run() {
		try {
			while (!stop)
				try {
					Socket s = ss.accept();
					logger.userConnected(s.toString());
					new HandleClient(s, logger).start();
				} catch (IOException ex) {
					if (!stop)
						ex.printStackTrace();
				}
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void finish(){
		/*
	}
		synchronized(ChatModel.class){
		String[] users = getUsersArray();
		for(int i=0; i<users.length; i++){
		ChatModel.unregisterUser(users[i]);
		}
		}
		stop = true;
		try {
		ss.close();
		} catch (IOException ex) {;}
*/
	}
}
