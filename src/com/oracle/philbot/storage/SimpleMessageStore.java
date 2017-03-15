package com.oracle.philbot.storage;

import java.util.ArrayList;
import java.util.List;

import com.oracle.philbot.ThinMessage;
import com.oracle.philbot.interfaces.IMessageStore;

public class SimpleMessageStore implements IMessageStore {
	private static List<ThinMessage> messageHistory = new ArrayList<ThinMessage>();

	public void storeMessage(ThinMessage m) {
		System.out.println("msg captured: " + m.body);
		messageHistory.add(m);
		System.out.println("messageHistory now has " + messageHistory.size()
				+ " items");
	}

	public List<ThinMessage> recallMessagesByCriteria(String criteria) {
		return null;
	}

	public List<ThinMessage> dumpHistory() {
		return messageHistory;
	}

	public String dumpHistoryAsString() {
		StringBuffer sb = new StringBuffer();
		for (ThinMessage m : messageHistory) {
//			String fromName = m.from.split("@")[0];
			String fromName = m.from;

			sb.append(m.date).append(": ").append(fromName).append(" said \'")
					.append(m.body).append("\'").append("\n");
		}
		return sb.toString();
	}

	public void reinitialize() {
		messageHistory = new ArrayList<ThinMessage>();
	}

}