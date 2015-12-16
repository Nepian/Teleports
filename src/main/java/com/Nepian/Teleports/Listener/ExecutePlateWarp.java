package com.Nepian.Teleports.Listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.Nepian.Teleports.EventManager;
import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Event.PlayerWarpEvent;
import com.Nepian.Teleports.Util.BlockUtil;

public class ExecutePlateWarp implements Listener {

	@EventHandler
	public static void onPlayerInteract(PlayerInteractEvent event) {
		
		if (event.getAction() != Action.PHYSICAL) {
			return;
		}
		
		if (!BlockUtil.isStonePlate(event.getClickedBlock())) {
			return;
		}
		
		Block block = BlockUtil.getBlockUnder(event.getClickedBlock());
		
		if (!TeleportManager.isStart(block)) {
			return;
		}
		
		Player player = event.getPlayer();
		
		if (!TeleportManager.hasWarp(block)) {
			String name = TeleportManager.getStartName(block);
			player.sendMessage("This warp is Closed! (" + name + ")");
			return;
		}
		
		Location loc = BlockUtil.getBlockAbove(TeleportManager.getWarp(block).getBlock()).getLocation();
		
		loc.setYaw(player.getLocation().getYaw());
		loc.setPitch(player.getLocation().getPitch());
		loc.add(0.5, 0, 0.5);
		
		Event eve = new PlayerWarpEvent(player, block, loc);
		
		EventManager.callEvent(eve);
	}
}
