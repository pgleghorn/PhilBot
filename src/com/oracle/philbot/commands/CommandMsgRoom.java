package com.oracle.philbot.commands;

import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.oracle.philbot.BadArgsException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandMsgRoom implements ICommand {

	String mucName;
	List<String> message;

	private void setupArgs(List<String> args) throws BadArgsException {
		try {
			mucName = args.get(0);
			message = args.subList(1, args.size());
		} catch (Exception e) {
			throw new BadArgsException();
		}
	}

	public String name() {
		return "roomInfo";
	}

	public void execute(String from, List<String> args) throws XMPPException,
			BadArgsException {
		setupArgs(args);
		MultiUserChat muc = Globals.MultiGetMuc(mucName);
		muc.sendMessage(Globals.joinWithSpaces(message));
	}

	public String help() {
		return "Send message to a room. Usage: " + name() + " <room> <message>";
	}

}
