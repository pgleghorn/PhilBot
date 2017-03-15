package com.oracle.philbot.commands;

import java.lang.reflect.Method;
import java.util.List;
import org.jivesoftware.smack.XMPPException;
import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandHelp implements ICommand {

	public String name() {
		return "help";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void execute(String from, List<String> args) throws XMPPException {

		try {
			String r;
			if (args.size() > 0) {
				String commandClass = args.get(0).substring(0, 1).toUpperCase()
						+ args.get(0).substring(1);
				Class cls = Class.forName("com.oracle.philbot.commands.Command"
						+ commandClass);
				Object obj = cls.newInstance();
				Method method = cls.getDeclaredMethod("help");
				r = (String) method.invoke(obj);
			} else {
				r = help();
			}
			if (Globals.isConsole(from)) {
				System.out.println(r);
			} else {
				Globals.MultiSendRoomMessage(from, r);
			}
		} catch (NoSuchMethodException e) {
			System.out.println("No such command");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String help() {
		return "Get help on a command. Usage: "
				+ name()
				+ " <command>\n"
				+ "Available commands:\n"
				+ "about, date, dice, echo, help, history";
	}

	/*
	 * @SuppressWarnings("unused") private void execute(List<String> args) { for
	 * (String a : args) { System.out.println("command arg: " + a); } if (conn
	 * != null) { System.out.println("connection id: " +
	 * conn.getConnectionID()); System.out.println("port: " + conn.getPort());
	 * System.out.println("host: " + conn.getHost());
	 * System.out.println("service name: " + conn.getServiceName());
	 * System.out.println("user: " + conn.getUser());
	 * 
	 * Roster roster = conn.getRoster(); Collection<RosterEntry> entries =
	 * roster.getEntries(); for (RosterEntry entry : entries) {
	 * System.out.println("roster: " + entry); }
	 * 
	 * System.out.println("account instructions: " +
	 * conn.getAccountManager().getAccountInstructions());
	 * 
	 * // Collection<String> attribs = conn.getAccountManager() //
	 * .getAccountAttributes(); // for (String attrib : attribs) { //
	 * System.out.println("account attribute: " + attrib); // }
	 * 
	 * Collection<ChatManagerListener> listeners = conn.getChatManager()
	 * .getChatListeners(); for (ChatManagerListener listener : listeners) {
	 * System.out.println("listener: " + listener); } } else {
	 * System.out.println("no connection"); } }
	 */
}
