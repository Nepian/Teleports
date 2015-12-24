package com.Nepian.Teleports.Util;

import org.bukkit.event.block.Action;

public class ActionUtil {

	public static boolean isRightClickedBlock(Action action) {
		return action == Action.RIGHT_CLICK_BLOCK;
	}
	
	public static boolean isClickedBlock(Action action) {
		return action == Action.LEFT_CLICK_BLOCK || action == Action.RIGHT_CLICK_BLOCK;
	}
}
