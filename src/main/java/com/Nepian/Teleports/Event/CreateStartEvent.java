package com.Nepian.Teleports.Event;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CreateStartEvent extends CancellableEvent {
	private Player player;
	private Block block;
	private String name;
	private ItemStack item;
	
	public CreateStartEvent(Player player, Block block, String name, ItemStack item) {
		super();
		this.player = player;
		this.block = block;
		this.name = name;
		this.item = item;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ItemStack getItemStack() {
		return this.item;
	}
}
