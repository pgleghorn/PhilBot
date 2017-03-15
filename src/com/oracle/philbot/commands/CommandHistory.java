package com.oracle.philbot.commands;

import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;
import com.oracle.philbot.listeners.BotMessageListener;

public class CommandHistory implements ICommand {

	public String name() {
		return "history";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		System.out.println("from=" + from);
		String messageHistory = "History requested...\n"
				+ Globals.store.dumpHistoryAsString();
		if (Globals.isConsole(from)) {
			System.out.println(messageHistory);
		} else {
//			String[] recipientArr = args.get(0).split("/");
			String[] recipientArr = from.split("/");
			String recipient = recipientArr[1].concat("@oracle.com");
			Chat privchat = Globals.conn.getChatManager().createChat(recipient,
					new BotMessageListener(false, "BotMessageListener"));
			privchat.sendMessage(messageHistory);
		}
	}

	public String help() {
		return "receive message history in a private chat. Usage: " + name();
	}

}