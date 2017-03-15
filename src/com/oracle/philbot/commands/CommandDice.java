package com.oracle.philbot.commands;

import java.util.Date;
import java.util.List;
import java.util.Random;
import org.jivesoftware.smack.XMPPException;

import com.oracle.philbot.Globals;
import com.oracle.philbot.interfaces.ICommand;

public class CommandDice implements ICommand {

	public String name() {
		return "dice";
	}
	
	public void execute(String from, List<String> args) throws XMPPException {
		String dice = "";
		if (args.size() == 0) {
			dice = "1d6";
		} else {
			dice = args.get(0).toLowerCase();
		}
		Random random = new Random(new Date().getTime());
		int dIndex = dice.indexOf("d");
		int numberOfDice = 0;
		try {
			numberOfDice = Integer.parseInt(dice.substring(0, dIndex));
		} catch (Exception e) {
			numberOfDice = 1;
		}
		int symbolIndex = dice.indexOf("+");

		int diceType;
		int modifier = 0;
		if (symbolIndex > 0) {
			diceType = Integer
					.parseInt(dice.substring(dIndex + 1, symbolIndex));
			modifier = Integer.parseInt(dice.substring(symbolIndex + 1,
					dice.length()));
		} else {
			try {
				diceType = Integer.parseInt(dice.substring(dIndex + 1,
						dice.length()));
			} catch (Exception e) {
				diceType = 6;
			}
		}
		
		int result = 0;
		String rr = "";
		for (int i = 1; i <= numberOfDice; i++) {
			int currentRoll = random.nextInt(diceType - 1) + 1;
			result += currentRoll;
			rr += currentRoll + " ";
		}

		result += modifier;
		rr += "+ " + modifier;
		String r = "You rolled " + result + " (" + rr + ")"; 
		if (Globals.isConsole(from)) {
			System.out.println(r);
		} else {
			Globals.MultiSendRoomMessage(from, r);
		}
	}
	
	public String help() {
		return "Roll dice. Usage: " + name() + " <NdX+Y>";
	}

}
