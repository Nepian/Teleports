package com.Nepian.Teleports.Metadata;

import org.bukkit.OfflinePlayer;

public class AddMemberMetadata {
	private OfflinePlayer player;
	
	public AddMemberMetadata(OfflinePlayer player) {
		this.player = player;
	}
	
	public OfflinePlayer getPlayer() {
		return player;
	}
}
