package com.Nepian.Teleports.Configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Config {
	WARP_REGISTERING_ITEM("WATCH"),
	
	DUMMY(null);
	
	private static File file = Files.FILE_CONFIG;
	private Object value;

	/* Constructor ----------------------------------------------------------*/

	Config(Object value) {
		this.value = value;
	}

	/* Methods --------------------------------------------------------------*/

	public boolean getBoolean() {
		return (boolean) this.get();
	}

	public String getString() {
		return (String) this.get();
	}

	public int getInt() {
		return (int) this.get();
	}

	public static void load() {
		read(file);
		save();
	}

	public static void save() {
		write(file);
	}

	/* Private Methods ------------------------------------------------------*/

	private Object get() {
		return this.value;
	}

	private String toPath() {
		return this.toString().toLowerCase().replace("__", ".");
	}

	private static void read(File file) {
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);

		for (Config key : values()) {
			String path = key.toPath();

			if (conf.contains(path)) {
				key.value = conf.get(path);
			}
		}
	}

	private static void write(File file) {
		FileConfiguration conf = YamlConfiguration.loadConfiguration(file);

		for (Config key : values()) {
			conf.set(key.toPath(), key.value);
		}

		try {
			conf.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
