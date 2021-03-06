package com.Nepian.Teleports.Command.Sub;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.Nepian.Teleports.TeleporterManager;
import com.Nepian.Teleports.Command.CommandStrings;
import com.Nepian.Teleports.Command.SubCommand;
import com.Nepian.Teleports.Data.LocationStringable;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Data.TeleporterType;

public class ListCommand extends SubCommand {
	
	public ListCommand() {
		super("list");
		setPermission(CommandStrings.BASE_PERM + "list");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		
		if (args.length == 0) {
			StringBuffer message = new StringBuffer("");
			message.append("\nTeleports End Location List\n");
			
			for (TeleporterData data : TeleporterManager.getEndLocationDatas()) {
				String location = LocationStringable.toString(data.getBlockLocation());
				
				message.append(" # ");
				message.append("Name: ").append(data.getName()).append(", ");
				message.append("Location: ").append(location).append("\n");
			}
			
			sender.sendMessage(message.toString());
			
			return;
		}
		
		if (args.length == 1) {
			
			if (!TeleporterManager.hasName(args[0])) {
				sender.sendMessage("Teleport Location doesn't exist.");
				return;
			}
			
			TeleporterData end = TeleporterManager.getEndLocationData(args[0]);
			String name = end.getName();
			
			StringBuilder message = new StringBuilder("");
			message.append("\nTeleports Start Location List of [" + name + "]\n");
			
			if (TeleporterManager.getTeleportLocationDatas().size() <= 0) {
				message.append("No starting location.");
			} else {
				for (TeleporterData data :TeleporterManager.getTeleportLocationDatas()) {
				
					if (data.getType() != TeleporterType.START) {
						continue;
					}
				
					if (!data.getName().equalsIgnoreCase(name)) {
						continue;
					}
				
					String owner = data.getOwner().getName();
					String location = LocationStringable.toString(data.getBlockLocation());
				
					message.append(" # ");
					message.append("Owner: ").append(owner).append(", ");
					message.append("Location: ").append(location).append("\n");
				}
			}
			
			sender.sendMessage(message.toString());
			
			return;
		}
	}

	@Override
	public String getPossibleArguments() {
		return "<End Location Name>";
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

	@Override
	public List<String> getTutorial() {
		return Arrays.asList("Show the list of teleports.");
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.GENERIC;
	}

}
