package com.Nepian.Teleports.Util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BlockUtil {

	public static Block getBlockUnder(Block block) {
		return block.getRelative(BlockFace.DOWN);
	}
	
	public static Block getBlockAbove(Block block) {
		return block.getRelative(BlockFace.UP);
	}
	
	public static Material getType(Block block) {
		return block.getType();
	}

	public static boolean isStonePlate(Block block) {
		return getType(block) == Material.STONE_PLATE;
	}
	
	public static boolean isWoodPlate(Block block) {
		return getType(block) == Material.WOOD_PLATE;
	}

	public static boolean isDiamondBlock(Block block) {
		return getType(block) == Material.DIAMOND_BLOCK;
	}

	public static boolean isEmeraldBlock(Block block) {
		return getType(block) == Material.EMERALD_BLOCK;
	}
	
	public static boolean isPlate(Block block) {
		return isStonePlate(block) || isWoodPlate(block);
	}
}
