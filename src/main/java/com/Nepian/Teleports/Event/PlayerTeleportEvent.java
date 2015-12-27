package com.Nepian.Teleports.Event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.Nepian.Teleports.Data.TeleporterData;

public class PlayerTeleportEvent extends CancellableEvent {
	private Player player;
	private Block block;
	private TeleporterData data;
	
	public PlayerTeleportEvent(Player player, Block block, TeleporterData data) {
		super();
		this.player = player;
		this.block = block;
		this.data = data;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public TeleporterData getTeleporterData() {
		return data;
	}
}
