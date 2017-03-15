package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandNo implements ICommand {

	public String name() {
		return "no";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		String r = "Don't be so negative";
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "No. Usage: " + name();
	}
	
}
