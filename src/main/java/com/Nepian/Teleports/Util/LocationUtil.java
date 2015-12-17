package com.Nepian.Teleports.Util;

public class LocationUtil {
	
	public static enum Yaw {
		SOUTH(0),
		EAST(-90),
		NORTH(-180),
		WEST(-270),
		
		OTHER(0);
		
		public float yaw;
		
		Yaw(float yaw) {
			this.yaw = yaw;
		}
		
		public static Yaw getDirection(float yaw) {
			float value = Math.abs(yaw) % 360;
			
			if (0 <= value && value < 45) {
				return SOUTH;
			}
			
			if (45 <= value && value < 135) {
				return EAST;
			}
			
			if (135 <= value && value < 225) {
				return NORTH;
			}
			
			if (225 <= value && value < 315) {
				return WEST;
			}
			
			if (315 <= value && value < 360) {
				return SOUTH;
			}
			
			return OTHER;
		}
		
		public static float getYawNextDirection(float yaw) {
			switch (getDirection(yaw)) {
			case SOUTH:
				return EAST.yaw;
			case EAST:
				return NORTH.yaw;
			case NORTH:
				return WEST.yaw;
			case WEST:
				return SOUTH.yaw;
			default:
				return SOUTH.yaw;
			}
		}
	}
}
