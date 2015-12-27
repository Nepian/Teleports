package com.Nepian.Teleports.Command.Sub;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.Command.CommandStrings;
import com.Nepian.Teleports.Command.SubCommand;
import com.Nepian.Teleports.Metadata.MetadataKeys;

public class ChangePrivateCommand extends SubCommand {
	
	public ChangePrivateCommand() {
		super("private");
		setPermission(CommandStrings.BASE_PERM + "changeprivate");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		
		if (!(sender instanceof Player)) {
			return;
		}
		
		Player player = (Player) sender;
		String flagString = args[0];
		boolean isPrivate = false;

		if (flagString.equalsIgnoreCase("true") || flagString.equalsIgnoreCase("false")) {
			isPrivate = Boolean.valueOf(flagString); 
		} else {
			player.sendMessage("Invalid args");
			return;
		}
		
		MetadataValue meta = new FixedMetadataValue(Main.getPlugin(), isPrivate);
		
		player.setMetadata(MetadataKeys.CHANGE_PRIVATE, meta);
		player.sendMessage("Please click the teleporter you want to change private.");
	}

	@Override
	public String getPossibleArguments() {
		return "<true/false>";
	}

	@Override
	public int getMinimumArguments() {
		return 1;
	}

	@Override
	public List<String> getTutorial() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.GENERIC;
	}

}
