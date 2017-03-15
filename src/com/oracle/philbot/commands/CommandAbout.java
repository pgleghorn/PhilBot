package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandAbout implements ICommand {

	public String name() {
		return "about";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		String r = "Philbot v1.0. phil.gleghorn@oracle.com\n" + "This bot can record and replay chat history.";
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "Information about this bot. Usage: " + name();
	}
	
}
