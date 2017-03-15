package com.oracle.philbot.storage;

import java.io.StringReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.oracle.philbot.ThinMessage;
import com.oracle.philbot.interfaces.IMessageStore;

public class H2MessageStore implements IMessageStore {
   
	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:h2:file:/tmp/philbotdb", "sa", "");
		return conn;
	}

	public void reinitialize() {
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn
					.prepareStatement("DROP TABLE IF EXISTS messages; "
							+ "CREATE TABLE messages"
							+ "(id INT PRIMARY KEY AUTO_INCREMENT, m_date TIMESTAMP, m_from VARCHAR(255), "
							+ "m_to VARCHAR(255), m_body CLOB, m_packetid VARCHAR(255), "
							+ "m_thread VARCHAR(255), m_subject VARCHAR(255))");
			ps.clearParameters();
			int count = ps.executeUpdate();
			System.out.println("\nRecreated " + count
					+ " tables successfully\n");
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void storeMessage(ThinMessage m) {
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO messages "
							+ "(m_date, m_from, m_to, m_body, m_packetid, m_thread, m_subject) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.clearParameters();
			java.util.Date now = new java.util.Date();
			Timestamp ts = new Timestamp(now.getTime());
			ps.setTimestamp(1, ts);
			ps.setString(2, m.from);
			ps.setString(3, m.to);
			ps.setCharacterStream(4, new StringReader(m.body));
			ps.setString(5, m.packetid);
			ps.setString(6, m.thread);
			ps.setString(7, m.subject);
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ThinMessage> recallMessagesByCriteria(String criteria) {
		return null;
	}

	public List<ThinMessage> dumpHistory() {
		List<ThinMessage> r = new ArrayList<ThinMessage>();

		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn
					.prepareStatement("SELECT "
							+ "m_date, m_from, m_to, m_body, m_packetid, m_thread, m_subject"
							+ " FROM messages ORDER BY id");
			ps.clearParameters();
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				ThinMessage tm = new ThinMessage();
				java.sql.Timestamp ts = result.getTimestamp(1);
				java.util.Date then = new java.util.Date(ts.getTime());
				tm.date = then;
				tm.from = result.getString(2);
				tm.to = result.getString(3);
				Clob clob = result.getClob(4);
				String clobStr = clob.getSubString(1, (int) clob.length());
				tm.body = clobStr;
				tm.packetid = result.getString(5);
				tm.thread = result.getString(6);
				tm.subject = result.getString(7);
				r.add(tm);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}

	public String dumpHistoryAsString() {
		StringBuffer sb = new StringBuffer();
		List<ThinMessage> messages = dumpHistory();
		for (ThinMessage m : messages) {
			sb.append(m.date).append(" ").append(m.from).append(": ")
					.append(m.body).append("\n");
		}
		return sb.toString();
	}
}