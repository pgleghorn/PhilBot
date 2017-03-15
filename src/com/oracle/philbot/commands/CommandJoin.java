package com.oracle.philbot.commands;

import java.util.List;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;
import com.oracle.philbot.listeners.BotMessageListener;

public class CommandJoin implements ICommand {

	public String name() {
		return "join";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		Globals.MultiAddMuc(args.get(0), args.get(1), args.get(2));
		String r = "Joined chat " + args.get(0);
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {			
			Chat privchat = Globals.conn.getChatManager().createChat(from,
					new BotMessageListener(false, "BotMessageListener"));
			privchat.sendMessage(r);
		}
	}

	public String help() {
		return "Join a chat room. Usage: " + name() + " <roomname> <alias> <password>";
	}

}
