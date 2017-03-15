package com.oracle.philbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oracle.philbot.storage.H2MessageStore;

public class App {

	public void cmd_exit(List<String> a) {
		System.exit(0);
	}

	public static void main(String[] args) {
		// Globals.store = new SimpleMessageStore();
		Globals.store = new H2MessageStore();
		// Globals.store.reinitialize();

		try {
			CommandExecutor.executeCommandByClass(
					Globals.CONSOLE,
					new ArrayList<String>(Arrays.asList("connect",
							"stbeehive.oracle.com", "5223", "oracle.com")));
			CommandExecutor
					.executeCommandByClass(
							Globals.CONSOLE,
							new ArrayList<String>(Arrays.asList(
									"join",
									"bagend@conference.oracle.com", "Philbot",
									"12345")));
			
//			Thread.sleep(1000);
			CommandExecutor.executeCommandByClass(
					Globals.CONSOLE,
					new ArrayList<String>(Arrays
							.asList("join",
									"wc_sites_general@conference.oracle.com", "Philbot",
									"12345")));
									
		} catch (Exception e) {
			e.printStackTrace();
		}
		CommandExecutor.shellCommandLoop();
	}

}