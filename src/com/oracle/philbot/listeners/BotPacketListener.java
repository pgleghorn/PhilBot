package com.oracle.philbot.listeners;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

public class BotPacketListener implements PacketListener {

	@Override
	public void processPacket(Packet arg0) {
		Message m = (Message) arg0;
		BotMessageListener bml = new BotMessageListener(true, "bml");
		bml.processMessage(null, m);
		System.out.println("MESSAGE (" + m.getFrom() + "):" + m.getBody());
	}

}
