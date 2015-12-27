package com.Nepian.Teleports;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.Nepian.Teleports.Configuration.Files;
import com.Nepian.Teleports.Data.TeleporterData;
import com.Nepian.Teleports.Data.TeleporterType;
import com.Nepian.Teleports.Event.RemoveTeleportEvent;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;

public class TeleporterManager {
	private static File teleportLocationDataFolder = Files.FOLDER_TELEPORTS;
	private static Map<Location, TeleporterData> teleports;
	private static Map<String, TeleporterData> names;
	
	static {
		teleports = new HashMap<Location, TeleporterData>();
		names = new HashMap<String, TeleporterData>();
	}
	
	/* Methods ----------------------------------------------------------------------------------*/
	
	/**
	 * テレポートデータを登録する
	 * @param event
	 */
	public static void put(TeleporterCreatingEvent event) {
		put(new TeleporterData(event));
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
	public static TeleporterType getType(Block block) {
		return getType(block.getLocation());
	}
	
	/**
	 * 指定したブロックがテレポータデータを所持しているかを判定する
	 * @param block = (確認対象)
	 * @return (所持)-> true (非所持)-> false
	 */
	public static boolean hasTeleporterData(Block block) {
		return hasTeleporterData(block.getLocation());
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
	public static Collection<TeleporterData> getTeleportLocationDatas() {
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
	 * @param block = (追加済)-> TeleportManager
	 * @return (存在)-> TeleportLocationData (非存在)-> null
	 */
	public static TeleporterData getTeleporterData(Block block) {
		return getTeleporterData(block.getLocation());
	}
	
	/**
	 * 登録されている終点のテレポートデータをコレクションで取得する
	 * @return Collection(TeleportLocationData)
	 */
	public static Collection<TeleporterData> getEndLocationDatas() {
		return names.values();
	}
	
	/**
	 * 指定して名前の終点テレポートデータを取得する
	 * @param name = (追加済)-> TeleportManager
	 * @return (存在)-> String (非存在)-> null
	 */
	public static TeleporterData getEndLocationData(String name) {
		return names.get(name);
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static boolean isEnd(TeleporterType type) {
		return type == TeleporterType.END;
	}
	
	private static void put(TeleporterData data) {
		if (isEnd(data.getType())) {
			names.put(data.getName(), data);
		}
		teleports.put(data.getBlockLocation(), data);
	}
	
	private static TeleporterData getTeleporterData(Location location) {
		return teleports.get(location);
	}
	
	/**
	 * 指定した場所のテレポータデータを所持しているか判定する
	 * @param location = (確認対象)
	 * @return (所持)-> true (非所持)-> false
	 */
	private static boolean hasTeleporterData(Location location) {
		return teleports.containsKey(location);
	}
	
	/**
	 * 指定した場所のテレポートタイプを取得する
	 * @param location = (追加済)-> TeleportManager
	 * @return (存在)-> TeleportType (非存在)-> TeleportType.OTHER
	 */
	private static TeleporterType getType(Location location) {
		return teleports.get(location).getType();
	}
	
	/**
	 * 指定した場所のテレポート名を取得する
	 * @param location = (追加済)-> TeleportManager
	 * @return (存在)-> String (非存在)-> null
	 */
	private static String getTeleportName(Location location) {
		if (!hasTeleporterData(location)) return null;
		return teleports.get(location).getName();
	}
	
	/**
	 * 指定した場所をテレポートローケーションから削除する
	 * @param location
	 */
	private static void remove(Location location) {
		TeleporterData tld = teleports.get(location);
		if (isEnd(tld.getType())) {
			names.remove(tld.getName());
		}
		teleports.remove(location);
		tld.deleteFile();
	}
	
	/* Load and Save Method ---------------------------------------------------------------------*/
	
	public static void save() {
		for (TeleporterData tld : teleports.values()) {
			tld.write();
		}
	}
	
	public static void load() {
		for (File file : teleportLocationDataFolder.listFiles()) {
			put(new TeleporterData(file));
		}
	}
}
