package com.Nepian.Test;

import org.bukkit.Location;

import com.Nepian.Teleports.Data.LocationStringable;

public class PlayGround {

	public static void main(String[] args) {
		String str = "world_old__-195.0__69.0__-1349.0__0.0__0.0";
		Location location = LocationStringable.toLocation(str);
		System.out.println(location);
	}
}
