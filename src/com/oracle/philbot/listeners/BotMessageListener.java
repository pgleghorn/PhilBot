package com.oracle.philbot.listeners;

import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

import com.oracle.philbot.CommandExecutor;
import com.oracle.philbot.Globals;
import com.oracle.philbot.ThinMessage;

public class BotMessageListener implements MessageListener {

	private boolean capturing = false;
	private String id;
	
	public BotMessageListener(boolean capturing, String id) {
		this.capturing = capturing;
		this.id = id;
	}
	
	public void processMessage(Chat chat, Message message) {
		ThinMessage tm = new ThinMessage(message);
		if (capturing) Globals.store.storeMessage(tm);
		if (tm.body.startsWith("!")) {
			System.out.println(id + " heard " + tm.from + " say: " + tm.body);
			List<String> command = Arrays.asList(tm.body.substring(1,
					tm.body.length()).split(" "));
			CommandExecutor.executeCommandByClass(tm.from, command);
		}

	}
}