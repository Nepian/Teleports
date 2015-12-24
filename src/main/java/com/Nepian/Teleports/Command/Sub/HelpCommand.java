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
		sender.sendMessage("");
		sender.sendMessage("Teleports Commands");
		
		for (SubCommand cmd : mainCommandHandler.getSubCommand()) {
			if (cmd.getType() != SubCommandType.GENERIC) {
				continue;
			}
			
			StringBuilder usage = new StringBuilder("/" + label + " ");
			usage.append(cmd.getName());
			
			if (cmd.getPossibleArguments().length() > 0) {
				usage.append(" ").append(cmd.getPossibleArguments());
			}
			
			sender.sendMessage(usage.toString());
		}
	}

	@Override
	public String getPossibleArguments() {
		return "";
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

	@Override
	public List<String> getTutorial() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.HIDDEN;
	}

}
