package com.Nepian.Teleports;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.Nepian.Teleports.Configuration.Files;
import com.Nepian.Teleports.Data.TeleportLocationData;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Event.CreateTeleportEvent;
import com.Nepian.Teleports.Event.RemoveTeleportEvent;

public class TeleportManager {
	private static File teleportLocationDataFolder = Files.FOLDER_TELEPORTS;
	private static Map<Location, TeleportLocationData> teleports;
	private static Map<String, TeleportLocationData> names;
	
	static {
		teleports = new HashMap<Location, TeleportLocationData>();
		names = new HashMap<String, TeleportLocationData>();
	}
	
	/* Methods ----------------------------------------------------------------------------------*/
	
	/**
	 * テレポートデータを登録する
	 * @param event
	 */
	public static void put(CreateTeleportEvent event) {
		put(new TeleportLocationData(event));
	}
	
	/**
	 * テレポートデータを削除する
	 * @param event
	 */
	public static void remove(RemoveTeleportEvent event) {
		remove(event.getTeleportLocationData().getBlockLocation());
	}
	
	/**
	 * 指定した名前のテレポート先を取得する
	 * @param name (追加済)-> TeleportManager
	 * @return (存在)-> Location (非存在)-> null
	 */
	public static Location getTeleportLocation(String name) {
		if (!hasName(name)) return null;
		return names.get(name).getTeleportLocation();
	}
	
	/**
	 * 指定したブロックのテレポート名を取得する
	 * @param block = (追加済)-> TeleportManager
	 * @return (存在)-> String (非存在)-> null
	 */
	public static String getTeleportName(Block block) {
		return getTeleportName(block.getLocation());
	}
	
	/**
	 * 指定したブロックのテレポートタイプを取得する
	 * @param block = (追加済)-> TeleportManager
	 * @return (存在)-> TeleportType (非存在)-> TeleportType.OTHER
	 */
	public static TeleportType getType(Block block) {
		return getType(block.getLocation());
	}
	
	/**
	 * 指定したブロックがテレポートデータを所持しているかを判定する
	 * @param block = (確認対象)
	 * @return (所持)-> true (非所持)-> false
	 */
	public static boolean hasTeleportLocationData(Block block) {
		return hasTeleportLocationData(block.getLocation());
	}
	
	/**
	 * 指定した名前が存在するか確認する
	 * @param name = (確認対象)
	 * @return (存在)-> true (非存在)-> false
	 */
	public static boolean hasName(String name) {
		return names.containsKey(name);
	}
	
	/**
	 * 登録されているテレポートデータのコレクションを取得する
	 * @return Collection(TeleportLocationData)
	 */
	public static Collection<TeleportLocationData> getTeleportLocationDatas() {
		return teleports.values();
	}
	
	/**
	 * 指定したブロックのテレポート先が存在するか確認する
	 * @param block = (確認対象)
	 * @return (存在)-> true (非存在)-> false
	 */
	public static boolean hasTeleportLocation(Block block) {
		return hasTeleportLocation(getTeleportName(block.getLocation()));
	}
	
	/**
	 * 指定した名前のテレポート先が存在するか確認する
	 * @param name = (確認対象)
	 * @return (存在)-> true (非存在)-> false
	 */
	public static boolean hasTeleportLocation(String name) {
		return names.containsKey(name);
	}
	
	/**
	 * 指定したブロックのテレポートデータを取得する
	 * @param block = (追加済)-> TeleportManagers
	 * @return (存在)-> TeleportLocationData (非存在)-> null
	 */
	public static TeleportLocationData getTeleportLocationData(Block block) {
		return getTeleportLocationData(block.getLocation());
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static boolean isEnd(TeleportType type) {
		return type == TeleportType.END;
	}
	
	private static void put(TeleportLocationData data) {
		if (isEnd(data.getType())) {
			names.put(data.getName(), data);
		}
		teleports.put(data.getBlockLocation(), data);
	}
	
	private static TeleportLocationData getTeleportLocationData(Location location) {
		return teleports.get(location);
	}
	
	/**
	 * 指定した場所のテレポートデータを所持しているか判定する
	 * @param location = (確認対象)
	 * @return (所持)-> true (非所持)-> false
	 */
	private static boolean hasTeleportLocationData(Location location) {
		return teleports.containsKey(location);
	}
	
	/**
	 * 指定した場所のテレポートタイプを取得する
	 * @param location = (追加済)-> TeleportManager
	 * @return (存在)-> TeleportType (非存在)-> TeleportType.OTHER
	 */
	private static TeleportType getType(Location location) {
		return teleports.get(location).getType();
	}
	
	/**
	 * 指定した場所のテレポート名を取得する
	 * @param location = (追加済)-> TeleportManager
	 * @return (存在)-> String (非存在)-> null
	 */
	private static String getTeleportName(Location location) {
		if (!hasTeleportLocationData(location)) return null;
		return teleports.get(location).getName();
	}
	
	/**
	 * 指定した場所をテレポートローケーションから削除する
	 * @param location
	 */
	private static void remove(Location location) {
		TeleportLocationData tld = teleports.get(location);
		if (isEnd(tld.getType())) {
			names.remove(tld.getName());
		}
		teleports.remove(location);
		tld.deleteFile();
	}
	
	/* Load and Save Method ---------------------------------------------------------------------*/
	
	public static void save() {
		for (TeleportLocationData tld : teleports.values()) {
			tld.write();
		}
	}
	
	public static void load() {
		for (File file : teleportLocationDataFolder.listFiles()) {
			put(new TeleportLocationData(file));
		}
	}
}
