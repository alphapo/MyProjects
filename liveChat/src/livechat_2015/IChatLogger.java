package livechat_2015;

public interface IChatLogger {

	public void userConnected(String ip);
	public void userDisconnected(String ip, String name);
	public void userGotName(String ip, String name);
	public void userGotCommand(String name, int command);
	public void publicChat(String from, String msg);
	public void privateChat(String from, String to, String msg);
	public void systemMessage(String msg);

	
}
