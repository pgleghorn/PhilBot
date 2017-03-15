package com.oracle.philbot.commands;

import java.util.List;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.RoomInfo;

import com.oracle.philbot.BadArgsException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandRoomInfo implements ICommand {

	String roomName;
	
	private void setupArgs(List<String> args) throws BadArgsException {
		roomName = args.get(0);
	}
	
	public String name() {
		return "roomInfo";
	}
	
	public void execute(String from, List<String> args) throws XMPPException,BadArgsException {
		StringBuffer sb = new StringBuffer();
		
		setupArgs(args);
		RoomInfo roominfo = MultiUserChat
				.getRoomInfo(Globals.conn, roomName);
		sb.append("room: " + roominfo.getRoom());
		sb.append("\n");
		sb.append("description: " + roominfo.getDescription());
		sb.append("\n");
		sb.append("occupants count: " + roominfo.getOccupantsCount());
		sb.append("\n");
		sb.append("subject: " + roominfo.getSubject());
		if (Globals.isConsole(from)) {
			System.out.println(sb.toString());
		} else {
			Globals.MultiSendRoomMessage(from, sb.toString());
		}
	}

	public String help() {
		return "Get room info. Usage: " + name() + " <roomname>";
	}
	

}
