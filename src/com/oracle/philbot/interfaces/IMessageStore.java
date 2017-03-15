package com.oracle.philbot.interfaces;

import java.util.List;
import com.oracle.philbot.ThinMessage;

public interface IMessageStore {

	public void reinitialize();

	public void storeMessage(ThinMessage m);

	public List<ThinMessage> recallMessagesByCriteria(String criteria);

	public List<ThinMessage> dumpHistory();

	public String dumpHistoryAsString();

}