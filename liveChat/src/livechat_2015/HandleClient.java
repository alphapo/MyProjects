package livechat_2015;

import java.io.IOException;
import java.net.Socket;

public class HandleClient extends Thread {
	
	private Socket S;
	private ChatOutput cho;
	private ChatInput chi;
	private boolean stop = false;
	private String name = null;
	private IChatLogger logger=null;
	private int state = 0;
	
	public HandleClient(Socket S, IChatLogger logger) throws IOException {
		this.S = S;
		this.logger = logger;
		cho = new ChatOutput(S);
		chi = new ChatInput(S);
	}
	
	
	public void run() {
		try { while(!stop){
				switch(chi.getCommand()){
					case ChatInput.NAME:
						String newName = chi.getStrName();
						if (ChatModel.existUserName(newName)) {
							
						} else {
							if (state == 0) {
							ChatModel.registerUser(newName, this);
							state = 1;
							} else ChatModel.renameUser(name, newName);
							name = newName;
							cho.sendNameOk();
							logger.userGotName(S.toString(), name);
						}
					break;
					case ChatInput.ULIST:
						if (state == 0) break;
						cho.sendList(ChatModel.getUserNames());
					break;
					case ChatInput.MESSAGE:
						if (state == 0) break;
						ChatModel.chatMessage(name,chi.getStrMsg());
						logger.publicChat(name,chi.getStrMsg());
					break;
				}
			}
		} catch (IOException ex) { finish(); }
	}
	
	public void sendChatMessage(String from, String msg){
		if (from!=name){
			cho.sendMessage(from,msg);
		}
	}
	
	public synchronized void finish(){
		if (!stop) {
			stop = true;
			try {
				S.close();
			} catch (IOException ex) { ex.printStackTrace(); }
			
			if (name != null)
			ChatModel.unregisterUser(name);
			logger.userDisconnected(S.toString(), name);
		}
	}


	public void sendUList() {
		// TODO Auto-generated method stub
		cho.sendList(ChatModel.getUserNames());
	}


}
