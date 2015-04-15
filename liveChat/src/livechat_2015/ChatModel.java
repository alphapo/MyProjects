package livechat_2015;

import java.util.ArrayList;
import java.util.TreeMap;

public class ChatModel {

	private static TreeMap<String, HandleClient> clientList = new TreeMap<String, HandleClient>();

	public static synchronized boolean registerUser(String name, HandleClient client) {
		if (!existUserName(name) && !name.equals("")) {
			clientList.put(name, client);
			newName();
			return true;
		}
		return false;
	}

	public static synchronized boolean existUserName(String name) {
		return clientList.containsKey(name);
	}

	


	public static synchronized void unregisterUser(String name) {
		if (existUserName(name)) {
			HandleClient client = clientList.get(name);
			clientList.remove(name);
			client.finish();
			newName();
		}
	}
	
	
	private static void newName() {
		for (HandleClient client : clientList.values()) {
			client.sendUList();
		}
	}
	
	public static void chatMessage(String from, String msg) {
		for (HandleClient client : clientList.values()) {
			client.sendChatMessage(from, msg);
		}
	}

	public static ArrayList<String> getUserNames() {
		// TODO Auto-generated method stub
		ArrayList<String> l =  new ArrayList<>();
		l.addAll(clientList.keySet());
		return l;
	}

	public static void renameUser(String oldname, String newName) {
		// TODO Auto-generated method stub
		clientList.put(newName, clientList.get(oldname));
		clientList.remove(oldname);
	}
	
}
