package com.Nepian.Teleports.Command.Sub;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.Nepian.Teleports.Command.CommandHandler;
import com.Nepian.Teleports.Command.CommandStrings;
import com.Nepian.Teleports.Command.SubCommand;

public class HelpCommand extends SubCommand {
	
	private CommandHandler mainCommandHandler;
	
	public HelpCommand(CommandHandler mainCommandHandler) {
		super("help");
		setPermission(CommandStrings.BASE_PERM + "help");
		this.mainCommandHandler = mainCommandHandler;
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPossibleArguments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinimumArguments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getTutorial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubCommandType getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
