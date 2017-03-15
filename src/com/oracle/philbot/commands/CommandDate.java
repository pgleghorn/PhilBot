package com.oracle.philbot.commands;

import java.util.Date;
import java.util.List;

import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandDate implements ICommand {

	public String name() {
		return "date";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		Date now = new Date();
		String r = "Current date is " + now.toString();
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "Get server date/time. Usage: " + name();
	}
	
}
