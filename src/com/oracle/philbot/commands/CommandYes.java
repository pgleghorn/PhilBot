package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandYes implements ICommand {

	public String name() {
		return "yes";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		String r = "Are you sure?";
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "Yes. Usage: " + name();
	}
	
}
