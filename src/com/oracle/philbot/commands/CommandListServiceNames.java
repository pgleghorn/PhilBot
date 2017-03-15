package com.oracle.philbot.commands;

import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandListServiceNames implements ICommand {

	public String name() {
		return "listServiceNames";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		Collection<String> serviceNames = MultiUserChat
				.getServiceNames(Globals.conn);
		String r = serviceNames.size()
				+ " service names found on this connection\n";
		for (String name : serviceNames) {
			r += "servicename : " + name + "\n";
		}

		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}

	public String help() {
		return "List servicenames on this connection. Usage: " + name();
	}

}
