package livechat_2015;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		int port;
		if (args.length == 0){
			port = 1234;
		}
		else port = Integer.parseInt(args[0]);
		
		
		try {
			new ServerCore(port);
		} catch (IOException e) {
			System.out.println("Error during initialisation: " + e.toString());
		}

	}

}
