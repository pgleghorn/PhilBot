package com.oracle.philbot.commands;

import java.util.List;

import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandGet implements ICommand {
	
	public String name() {
		return "get";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		String r = "Get it yourself";
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "You don't need any help";
	}
	
}
