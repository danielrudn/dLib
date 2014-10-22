package com.DR.dLib;

import com.DR.dLib.dUICard;
import com.badlogic.gdx.graphics.Texture;

public abstract class dScreen extends dUICard {

	private boolean isPaused = false;
	
	public dScreen(float x, float y, Texture texture) {
		super(x, y, texture);
		this.setDimensions(dValues.VW, dValues.VH);
		setHasShadow(false);
	}
	
	/**
	 * User clicked back button
	 */
	public abstract void goBack();
	
	/**
	 * Switch screens to provided screen, transitions, etc up to the current screen
	 * @param newScreen screen that will be switched to 
	 */
	public abstract void switchScreen(dScreen newScreen);
	
	public void pause()
	{
		isPaused = true;
	}
	
	public void resume()
	{
		isPaused = false;
	}
	
	public boolean isPaused()
	{
		return isPaused;
	}
	
	/*
	 * WARNING: WHEN OVERRIDING UPDATE AND RENDER METHODS MAKE SURE TO CALL super(...) TO UPDATE AND RENDER THE REGULAR DUICARD STUFF
	 */

}

