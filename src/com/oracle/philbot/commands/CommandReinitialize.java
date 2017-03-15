package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandReinitialize implements ICommand {

	public String name() {
		return "reinitialize";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		Globals.store.reinitialize();
		String r = "OK";
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "Reinitializes (wipes) the message history database: " + name();
	}
	
}
