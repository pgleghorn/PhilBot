package com.oracle.philbot.commands;

import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandStatus implements ICommand {

	public String name() {
		return "status";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		StringBuffer sb = new StringBuffer();
		if (Globals.conn != null) {
			sb.append("connection id: " + Globals.conn.getConnectionID());
			sb.append("\n");
			sb.append("port: " + Globals.conn.getPort());
			sb.append("\n");
			sb.append("host: " + Globals.conn.getHost());
			sb.append("\n");
			sb.append("service name: " + Globals.conn.getServiceName());
			sb.append("\n");
			sb.append("user: " + Globals.conn.getUser());
			sb.append("\n");

			Roster roster = Globals.conn.getRoster();
			Collection<RosterEntry> entries = roster.getEntries();
			for (RosterEntry entry : entries) {
				sb.append("roster: " + entry);
				sb.append("\n");
			}

			sb.append("account instructions: "
					+ Globals.conn.getAccountManager().getAccountInstructions());

			Collection<ChatManagerListener> listeners = Globals.conn
					.getChatManager().getChatListeners();
			for (ChatManagerListener listener : listeners) {
				sb.append("listener: " + listener);
				sb.append("\n");
			}
		} else {
			sb.append("no connection");
		}

		if (Globals.isConsole(from)) {
			System.out.println(sb.toString());
		} else {
			Globals.MultiSendRoomMessage(from, sb.toString());
		}
	}

	public String help() {
		return "Show various connection status. Usage: " + name();
	}

}
