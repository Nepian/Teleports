package com.Nepian.Teleports.Util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayerUtil {

	public static OfflinePlayer getOfflinePlayer(String name) {
		for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
	
	public static void playSound(Player player, Sound sound, float volume, float pitch) {
		player.getWorld().playSound(player.getLocation(), sound, volume, pitch);
	}
}
