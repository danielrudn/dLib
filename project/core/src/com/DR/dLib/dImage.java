package com.DR.dLib;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class dImage extends dObject {
	
	// class for images in ui cards, etc..
	
	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/
	
	private float imageWidth, imageHeight;
	private boolean hasShadow = false;
	
	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/

	public dImage(float x, float y, Sprite sprite) {
		super(x, y, sprite);
		setUpdatable(false);
	}
	
	public dImage(Vector2 pos, Texture texture)
	{
		super(pos,texture);
		setUpdatable(false);
	}
	
	public dImage(Vector2 pos, Sprite sprite)
	{
		super(pos,sprite);
		setUpdatable(false);
	}
	
	public dImage(float x, float y, Texture texture)
	{
		super(x,y,texture);
		setUpdatable(false);
	}
	
	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	@Override
	public void update(float delta) {}

	@Override
	public void render(SpriteBatch batch) {
		if(hasShadow)
		{
			getSprite().setColor(0,0,0,.25f);
			getSprite().setPosition(getX()+1, getY()+2);
			getSprite().draw(batch);
			getSprite().setColor(getColor());
			getSprite().setPosition(getX(), getY());
			getSprite().draw(batch);
		}
		else
		{
			getSprite().draw(batch);
		}
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setWidth(float width)
	{
		imageWidth = width;
		setScaleX(width/getSprite().getRegionWidth());
	}
	
	public void setHeight(float height)
	{
		imageHeight = height;
		setScaleY(height/getSprite().getRegionHeight());
	}
	
	public void setDimensions(float width, float height)
	{
		setWidth(width);
		setHeight(height);
	}
	
	public void setHasShadow(boolean shadow)
	{
		hasShadow = shadow;
	}
	
	@Override
	public void setAlpha(float a)
	{
		getSprite().setAlpha(a);
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/
	
	@Override
	public float getWidth()
	{
		return imageWidth;
	}
	
	@Override
	public float getHeight()
	{
		return imageHeight;
	}
	
	public boolean hasShadow()
	{
		return hasShadow;
	}
}