package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandDisconnect implements ICommand {

	public String name() {
		return "disconnect";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		Globals.conn.disconnect();
		System.out.println("Disconnected");
	}

	public String help() {
		return "Disconnect from server. Usage: " + name();
	}

}
