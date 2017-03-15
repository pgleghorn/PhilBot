package com.oracle.philbot.listeners;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManagerListener;

public class BotChatListener implements ChatManagerListener {

	public void chatCreated(Chat chat, boolean local) {
		chat.addMessageListener(new BotMessageListener(false, "BotMessageListener"));
	}

}