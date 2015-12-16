package com.Nepian.Teleports.Command;

import java.util.Collection;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportLocationData;

public class PrintAllTeleportLocationDataCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			if (!(sender instanceof ConsoleCommandSender)) {
				return true;
			}
		}
		
		Collection<TeleportLocationData> data = TeleportManager.getTeleportLocationDatas();
		
		StringBuilder msg = new StringBuilder("");
		for (TeleportLocationData tld : data) {
			msg.append(tld.toString()).append("\n");
		}
		
		((Player) sender).sendMessage(msg.toString());
		
		return true;
	}

}
