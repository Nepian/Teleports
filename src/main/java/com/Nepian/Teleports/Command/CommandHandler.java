package com.Nepian.Teleports.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Nepian.Teleports.Command.Sub.HelpCommand;
import com.Nepian.Teleports.Command.Sub.ListCommand;

public class CommandHandler implements CommandExecutor {
	
	private List<SubCommand> subCommands;
	
	public CommandHandler() {
		subCommands = new ArrayList<SubCommand>();
		registerSubCommand(new ListCommand());
		registerSubCommand(new HelpCommand(this));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args.length == 0) {
			//TODO: 引数無しの場合の処理を記述
			return true;
		}
		
		for (SubCommand subCommand : subCommands) {
			if (subCommand.isValidTrigger(args[0])) {
				if (!subCommand.hasPermission(sender)) {
					sender.sendMessage("You don't have permission!");
					return true;
				}
				
				if (subCommand.getMinimumArguments() <= args.length - 1) {
					try {
						subCommand.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
					} catch (CommandException e) {
						sender.sendMessage(e.getMessage());
					}
				} else {
					sender.sendMessage("Usage: /" + label + " " + subCommand.getName() + " " + subCommand.getPossibleArguments());
				}
				
				return true;
			}
		}
		
		sender.sendMessage("Unknown sub-command. Type \"/" + label + " help\" for a list of commands.");
		
		return true;
	}
	
	public List<SubCommand> getSubCommand() {
		return this.subCommands;
	}
	
	private void registerSubCommand(SubCommand command) {
		subCommands.add(command);
	}
}
	