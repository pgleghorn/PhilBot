package com.oracle.philbot;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.oracle.philbot.interfaces.IMessageStore;
import com.oracle.philbot.listeners.BotMessageListener;
import com.oracle.philbot.listeners.BotPacketListener;

public class Globals {

	public static String assumeService = "conference.oracle.com";

	public static String assumeUserDomain = "oracle.com";
	
	public static XMPPConnection conn;

	public static IMessageStore store;

	private static Map<String, MultiUserChat> mucs = new LinkedHashMap<String, MultiUserChat>();

	public static String CONSOLE = "console";

	public static String joinWithSpaces(List<String> args) {
		StringBuffer sb = new StringBuffer();
		for (String a : args) {
			sb.append(a).append(" ");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/*
	 * ------------------- THE NEW MULTI-MUC METHODS ------------------- TODO
	 * maybe extend MultiUserChat eg MultiUserChatPool extends MultiUserChat,
	 * and wrap join() leave() to pool and keep track of mucs
	 */

	public static MultiUserChat MultiAddMuc(String mucName, String nickName,
			String password) throws XMPPException {
		mucName = assumeDefaultService(mucName);
		if (!mucs.containsKey(mucName)) {
			MultiUserChat muc = new MultiUserChat(Globals.conn, mucName);
			muc.join(nickName, password);
			muc.addMessageListener(new BotPacketListener());
			mucs.put(mucName, muc);
		}
		return mucs.get(mucName);
	}

	public static boolean MultiRemoveMuc(String mucName) throws XMPPException {
		mucName = assumeDefaultService(mucName);
		if (mucs.containsKey(mucName)) {
			MultiUserChat muc = mucs.get(mucName);
			muc.leave();
			mucs.remove(mucName);
			return true;
		}
		return false;
	}
	
	public static MultiUserChat MultiGetMuc(String mucName) throws XMPPException {
		mucName = assumeDefaultService(mucName);
		if (mucs.containsKey(mucName)) {
			MultiUserChat muc = mucs.get(mucName);
			return muc;
		}
		return null;
	}

	public static boolean MultiSendRoomMessage(String mucName, String message)
			throws XMPPException {
		mucName = assumeDefaultService(mucName);
		String[] mucNameArray = mucName.split("/");
		String mucNameTrunc = mucNameArray[0];
		if (mucs.containsKey(mucNameTrunc)) {
			MultiUserChat muc = mucs.get(mucNameTrunc);
			muc.sendMessage(message);
			return true;
		}
		return false;
	}
	
	public static void MultiSendUserMessage(String user, String message) throws XMPPException {
		String username = StringUtils.parseName(user);
		String bareAddress = StringUtils.parseBareAddress(user);
		String resource = StringUtils.parseResource(user);
		String server = StringUtils.parseServer(user);
		
		System.out.println("username=" + username);
		System.out.println("bareAddress=" + bareAddress);
		System.out.println("resource=" + resource);
		System.out.println("server=" + server);
		
//		if (username.indexOf("@"))
		System.out.println("sending message to " + username);
		Chat privchat = Globals.conn.getChatManager().createChat(username,
				new BotMessageListener(false, "BotMessageListener"));
		privchat.sendMessage(message);
	}

	public static String MultiListJoinedRooms() {
		StringBuffer sb = new StringBuffer();
		Set<String> keyset = mucs.keySet();
		for (String k : keyset) {
			sb.append(k + "\n");
		}
		return sb.toString();
	}

	public static boolean isConsole(String from) {
		return (Globals.CONSOLE.compareTo(from) == 0);
	}

	private static String assumeDefaultService(String servicename) {
		if (servicename.indexOf("@") == -1) {
			return servicename.concat("@").concat(Globals.assumeService);
		} else {
			return servicename;
		}
	}
	
	private static String assumeDefaultUserDomain(String user) {
		if (user.indexOf("@") == -1) {
			return user.concat("@").concat(Globals.assumeUserDomain);
		} else {
			return user;
		}
	}
}