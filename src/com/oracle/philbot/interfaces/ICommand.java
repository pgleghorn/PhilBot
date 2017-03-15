package com.oracle.philbot.interfaces;

import java.util.List;

import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.BadArgsException;

public interface ICommand {

	public String name();
	
	public void execute(String from, List<String> args) throws XMPPException,BadArgsException;

	public String help();

}
