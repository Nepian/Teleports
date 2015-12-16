package com.Nepian.Teleports.Command;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Util.LocationUtil;

public class PrintWarpsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			if (!(sender instanceof ConsoleCommandSender)) {
				return true;
			}
		}
		
		Map<Location, String> starts = TeleportManager.getStarts();
		Map<Location, String> ends = TeleportManager.getEnds();
		
		StringBuilder msg = new StringBuilder("");
		
		msg.append("+-- Start locations ----------------------------------------------------------+\n");
		for (Location loc : starts.keySet()) {
			String s = LocationUtil.toStringSimpleLocation(loc);
			msg.append(starts.get(loc) + ": " + s + "\n");
		}
		msg.append("+-- End locations -----------------------------------------------------------+\n");
		for (Location loc : ends.keySet()) {
			String s = LocationUtil.toStringSimpleLocation(loc);
			msg.append(ends.get(loc) + ": " + s + "\n");
		}
		msg.append("+---------------------------------------------------------------------------+\n");
		
		((Player) sender).sendMessage(msg.toString());
		
		return true;
	}

}
