package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;
import com.oracle.philbot.listeners.BotMessageListener;

public class CommandLeave implements ICommand {

	public String name() {
		return "leave";
	}

	public void execute(String from, List<String> args) throws XMPPException {
		String r;
		if (Globals.MultiRemoveMuc(args.get(0))) {
			r = "Left chatroom " + args.get(0);
		} else {
			r = "Chatroom not found";
		}
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Chat privchat = Globals.conn.getChatManager().createChat(from,
					new BotMessageListener(false, "BotMessageListener"));
			privchat.sendMessage(r);
		}
	}

	public String help() {
		return "Leave a chat room. Usage: " + name() + " <roomname>";
	}

}
