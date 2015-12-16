package com.Nepian.Teleports.Event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RemoveStartEvent extends CancellableEvent {
	private Player player;
	private Block block;
	
	public RemoveStartEvent(Player player, Block block) {
		super();
		this.player = player;
		this.block = block;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Block getBlock() {
		return this.block;
	}
}
