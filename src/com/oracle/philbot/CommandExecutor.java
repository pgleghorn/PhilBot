package com.oracle.philbot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.jivesoftware.smack.XMPPException;

@SuppressWarnings("rawtypes")
public class CommandExecutor {

	@SuppressWarnings({ "unchecked" })
	public static void executeCommandByClass(String from, List<String> args) {
		Class[] params = new Class[2];
		params[0] = String.class;
		params[1] = List.class;

		try {
			String commandClass = args.get(0).substring(0, 1).toUpperCase()
					+ args.get(0).substring(1);
			Class cls = Class.forName("com.oracle.philbot.commands.Command"
					+ commandClass);
			Object obj = cls.newInstance();
			Method method = cls.getDeclaredMethod("execute", params);
			System.out.println("Executing " + commandClass + " from " + from
					+ " with " + args);
			method.invoke(obj, from, args.subList(1, args.size()));
		} catch (ClassNotFoundException e) {
			try {
				Globals.MultiSendRoomMessage(from, "No such command");
			} catch (XMPPException e1) {
				e1.printStackTrace();
			}
		} catch (IndexOutOfBoundsException e) {
			try {
				Globals.MultiSendRoomMessage(from,
						"That didn't work, check your args with !help <command>");
			} catch (XMPPException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void executeCommandByClass(String from, String commandString) {
		List<String> commandList = Arrays.asList(commandString.split(" "));
		executeCommandByClass(from, commandList);
	}

	public static void shellCommandLoop() {
		while (true) {
			System.out.print("cmd> ");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				String s = br.readLine();
				if (s.length() > 1) {
					List<String> commandList = Arrays.asList(s.split(" "));
					CommandExecutor.executeCommandByClass(Globals.CONSOLE,
							commandList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
