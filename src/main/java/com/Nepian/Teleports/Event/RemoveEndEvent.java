package com.Nepian.Teleports.Event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class RemoveEndEvent extends CancellableEvent {
	private Player player;
	private Block block;
	
	public RemoveEndEvent(Player player, Block block) {
		super();
		this.player = player;
		this.block = block;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getBlock() {
		return block;
	}
}
