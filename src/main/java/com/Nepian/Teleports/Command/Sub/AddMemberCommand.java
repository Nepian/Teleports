package com.Nepian.Teleports.Command.Sub;

import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.Command.SubCommand;
import com.Nepian.Teleports.Metadata.AddMemberMetadata;
import com.Nepian.Teleports.Util.PlayerUtil;

public class AddMemberCommand extends SubCommand {
	
	public AddMemberCommand() {
		super("addmember", "addplayer", "addowner");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		
		if (!(sender instanceof Player)) {
			return;
		}
		
		String name = args[0];
		OfflinePlayer member = PlayerUtil.getOfflinePlayer(name);
		
		if (member == null) {
			sender.sendMessage("Not found the player of such a name.");
			return;
		}
		
		Player player = (Player) sender;
		AddMemberMetadata data = new AddMemberMetadata(member);
		MetadataValue meta = new FixedMetadataValue(Main.getPlugin(), data);
		player.setMetadata("addmemberflag", meta);
		
		sender.sendMessage("Please click the block you want to add member.");
	}

	@Override
	public String getPossibleArguments() {
		return "<member>";
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
