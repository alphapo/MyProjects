package livechat_2015;

public class TextChatLogger implements IChatLogger {

	
	public void userConnected(String ip) {
		System.out.println("L'utilisateur "+ip+" s'est connecté");
		
	}

	
	public void userDisconnected(String ip, String name) {
		System.out.println("L'utilisateur "+ ip + name +" s'est deconnecté");
		
	}

	
	public void userGotName(String ip, String name) {
		System.out.println("Le nom de l'ip "+ip+" est "+name);
		
	}

	
	public void userGotCommand(String name, int command) {
		System.out.println("La commande de l'utilisateur "+name+"est "+command);
		
	}

	
	public void publicChat(String from, String msg) {
		System.out.println("Le message venat de "+from+"est "+msg);
		
	}

	
	public void privateChat(String from, String to, String msg) {
		System.out.println("Message de "+from+"vers "+to+"est "+msg);
		
	}

	
	public void systemMessage(String msg) {
		System.out.println("Message du systeme "+msg);
		
	}
	

	
}
