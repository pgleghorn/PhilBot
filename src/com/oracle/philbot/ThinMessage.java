package com.oracle.philbot;

import java.util.Date;

import org.jivesoftware.smack.packet.Message;

public class ThinMessage {

	public Date date;
	public String body;
	public String from;
	public String to;
	public String packetid;
	public String thread;
	public String subject;

	public ThinMessage(Message m) {
		this.date = new Date();
		this.body = m.getBody();
		this.from = m.getFrom();
		this.to = m.getTo();
		this.packetid = m.getPacketID();
		this.thread = m.getThread();
		this.subject = m.getSubject();
	}
	
	public ThinMessage() {}

}
