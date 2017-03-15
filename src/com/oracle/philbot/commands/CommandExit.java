package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.interfaces.ICommand;

public class CommandExit implements ICommand {

	public String name() {
		return "exit";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		System.exit(0);
	}

	public String help() {
		return "Shutdown bot. Usage: " + name();
	}

}
