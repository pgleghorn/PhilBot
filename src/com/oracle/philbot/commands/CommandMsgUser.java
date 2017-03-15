package com.oracle.philbot.commands;

import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;
import com.oracle.philbot.listeners.BotMessageListener;

public class CommandMsgUser implements ICommand {

	public String name() {
		return "msgUser";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		Globals.MultiSendUserMessage(args.get(0), Globals.joinWithSpaces(args.subList(1, args.size())));
//		Chat privchat = Globals.conn.getChatManager().createChat(args.get(0),
//				new BotMessageListener());
//		privchat.sendMessage(Globals.joinWithSpaces(args.subList(1, args.size())));
	}

	public String help() {
		return "Send private message to user. Usage: " + name() + " <user> <message>";
	}

}
