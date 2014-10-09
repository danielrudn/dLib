package com.DR.dLib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class dButton extends dObject {

	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/

	private boolean enabled = true, clicked = false;
	private dText buttonText;
	private float bWidth, bHeight;
	
	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/
	
	public dButton(float x, float y, Sprite sprite, String text) {
		super(x, y, sprite);
		buttonText = new dText(0,0,18f,text);
		buttonText.setPos(x + getWidth()/2f - buttonText.getWidth()/2f, y + getHeight()/2f - buttonText.getHeight()/2f);
		buttonText.setColor(Color.WHITE);
		bWidth = sprite.getWidth();
		bHeight = sprite.getHeight();
	}
	
	public dButton(float x, float y, Sprite sprite, dText text) {
		super(x, y, sprite);
		buttonText = text;
		bWidth = sprite.getWidth();
		bHeight = sprite.getHeight();
	}


	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	@Override
	public void update(float delta) {
		if(isEnabled())
		{
			checkClicked();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		getSprite().draw(batch);
		buttonText.render(batch);
	}
	
	private void checkClicked()
	{
		if(getBoundingRectangle().contains(dValues.camera.position.x-dValues.VW/2f + (Gdx.input.getX() / (Gdx.graphics.getWidth() / dValues.VW)),dValues.camera.position.y-dValues.VH/2f + Gdx.input.getY() / (Gdx.graphics.getHeight() / dValues.VH)) && Gdx.input.justTouched())
		{
			clicked = true;
			setAlpha(.6f);
		}
		else
		{
			clicked = false;
			setAlpha(1f);
		}
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	@Override
	public void setPosition(Vector2 pos)
	{
		position = pos;
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		position.set(x, y);
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	@Override
	public void setPos(Vector2 pos)
	{
		position = pos;
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	@Override
	public void setPos(float x, float y)
	{
		position.set(x,y);
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	@Override
	public void setX(float x)
	{
		position.set(x, position.y);
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	@Override
	public void setY(float y)
	{
		position.set(position.x, y);
		getSprite().setPosition(position.x, position.y);
		buttonText.setPos(position.x + getWidth()/2f - buttonText.getWidth()/2f, position.y + getHeight()/2f - buttonText.getHeight()/2f);
	}
	
	public void setText(String text)
	{
		buttonText.setText(text);
	}
	
	public void setTextSize(float size)
	{
		buttonText.setScale(size);
	}
	
	public void setTextColor(Color c)
	{
		buttonText.setColor(c);
	}
	
	public void setWidth(float w)
	{
		bWidth = w;
		this.setScaleX(w/getSprite().getRegionWidth());
	}
	
	public void setHeight(float h)
	{
		bHeight = h;
		setScaleY(h/getSprite().getRegionHeight());
	}
	
	public void setDimensions(float w, float h)
	{
		setWidth(w);
		setHeight(h);
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/
	
	public boolean isClicked()
	{
		return clicked;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	@Override
	public float getWidth()
	{
		return bWidth;
	}
	
	@Override
	public float getHeight()
	{
		return bHeight;
	}
	
	public String getText()
	{
		return buttonText.getText();
	}
	
}
