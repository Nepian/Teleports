package com.Nepian.Teleports.Event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class CancellableEvent extends Event {
	private static HandlerList handlers = new HandlerList();
	
	private Outcome outcome;
	
	public CancellableEvent() {
		this.outcome = Outcome.SUCCESS;
	}
	
	public boolean isCancelled() {
		return this.outcome != Outcome.SUCCESS;
	}
	
	public void setCancelled(boolean isCancel) {
		if (isCancel) {
			this.outcome = Outcome.FALLED;
		} else {
			this.outcome = Outcome.SUCCESS;
		}
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public enum Outcome {
		SUCCESS,
		FALLED;
	}
}
