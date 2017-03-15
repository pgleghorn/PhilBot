package com.oracle.philbot.commands;

import java.util.List;

import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;
import com.oracle.philbot.listeners.BotChatListener;

public class CommandConnect implements ICommand {

	public String name() {
		return "connect";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		ConnectionConfiguration cc = new ConnectionConfiguration(args.get(0),
				Integer.parseInt(args.get(1)), args.get(2));
		cc.setSecurityMode(ConnectionConfiguration.SecurityMode.legacy);
		cc.setCompressionEnabled(true);
		cc.setSASLAuthenticationEnabled(true);
		// cc.setDebuggerEnabled(true);
		Globals.conn = new XMPPConnection(cc);

		try {
			System.out.println("Trying to connect");
			Globals.conn.connect();
			System.out.println("Connected");
			Globals.conn.login("phil.gleghorn", "passwordgoeshere",
					"ParallelUniverse");
			ChatManager chatManager = Globals.conn.getChatManager();
			chatManager.addChatListener(new BotChatListener());
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

	public String help() {
		return "Connect to a server. Usage: " + name() + " <host> <port> <resource>";
	}

}
