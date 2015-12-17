package com.Nepian.Teleports.Event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.Nepian.Teleports.Data.TeleportLocationData;

public class RemoveTeleportEvent extends CancellableEvent {
	private Player player;
	private Block block;
	private TeleportLocationData data;
	
	public RemoveTeleportEvent(Player player, Block block, TeleportLocationData data) {
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
	
	public TeleportLocationData getTeleportLocationData() {
		return data;
	}
}
