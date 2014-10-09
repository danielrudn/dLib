package com.DR.dLib;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class dObject {

	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/
	
	protected Vector2 position;
	private Texture objTexture;
	private Sprite objSprite;
	protected Color objColor = new Color(Color.WHITE);
	private boolean updatable = true;
	
	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/
	
	public dObject(Vector2 pos)
	{
		position = pos;
	}
	
	public dObject(Vector2 pos, Texture texture)
	{
		position = pos;
		objTexture = texture;
		objSprite = new Sprite(texture);
		objSprite.setPosition(position.x, position.y);
		objSprite.flip(false,true);
	}
	
	public dObject(Vector2 pos, Sprite sprite)
	{
		position = pos;
		objTexture = sprite.getTexture();
		objSprite = sprite;
		objSprite.setPosition(position.x, position.y);
		objSprite.flip(false,true);
	}
	
	public dObject(float x, float y)
	{
		position = new Vector2(x,y);
	}
	
	public dObject(float x, float y, Texture texture)
	{
		position = new Vector2(x,y);
		objTexture = texture;
		objSprite = new Sprite(texture);
		objSprite.setPosition(position.x, position.y);
		objSprite.flip(false,true);
	}
	
	public dObject(float x, float y, Sprite sprite)
	{
		position = new Vector2(x,y);
		objTexture = sprite.getTexture();
		objSprite = sprite;
		objSprite.setPosition(position.x, position.y);
		objSprite.flip(false,true);
	}
	
	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	public abstract void update(float delta);
	
	public abstract void render(SpriteBatch batch);
	
	private void applyColor()
	{
			objSprite.setColor(objColor);
	}

	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setPosition(Vector2 pos)
	{
		position = pos;
		objSprite.setPosition(position.x, position.y);
	}
	
	public void setPosition(float x, float y)
	{
		position.set(x, y);
		objSprite.setPosition(position.x, position.y);
	}
	
	public void setPos(Vector2 pos)
	{
		position = pos;
		objSprite.setPosition(position.x, position.y);
	}
	
	public void setPos(float x, float y)
	{
		position.set(x,y);
		objSprite.setPosition(position.x, position.y);
	}
	
	public void setX(float x)
	{
		position.set(x, position.y);
		objSprite.setPosition(position.x, position.y);
	}
	
	public void setY(float y)
	{
		position.set(position.x, y);
		objSprite.setPosition(position.x, position.y);
	}
	
	public void setColor(Color c)
	{
		objColor = c;
		applyColor();
	}
	
	public void setColor(float r, float g, float b, float a)
	{
		objColor = new Color(r,g,b,a);
		applyColor();
	}
	
	public void setAlpha(float a)
	{
		objColor.set(objColor.r, objColor.g, objColor.b, a);
		applyColor();
	}
	
	protected void setScale(float XYScale)
	{
		objSprite.setScale(XYScale);
		objSprite.setOrigin(0, 0);
	}
	
	protected void setScale(float xScale, float yScale)
	{
		objSprite.setScale(xScale, yScale);
		objSprite.setOrigin(0, 0);
	}
	
	protected void setScaleX(float x)
	{
		objSprite.setScale(x,objSprite.getScaleY());
		objSprite.setOrigin(0, 0);
	}
	
	protected void setScaleY(float y)
	{
		objSprite.setScale(objSprite.getScaleX(),y);
		objSprite.setOrigin(0, 0);
	}
	
	public void setUpdatable(boolean update)
	{
		updatable = update;
	}
	
	public void setOrigin(float oX, float oY)
	{
		objSprite.setOrigin(oX, oY);
	}
	
	public void setOriginCenter()
	{
		objSprite.setOriginCenter();
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public Vector2 getPos()
	{
		return position;
	}
	
	public float getX()
	{
		return position.x;
	}
	
	public float getY()
	{
		return position.y;
	}
	
	public Color getColor()
	{
		return objColor;
	}
	
	public Texture getTexture()
	{
		return objTexture;
	}
	
	public Sprite getSprite()
	{
		return objSprite;
	}
	
	public float getScaleX()
	{
		return objSprite.getScaleX();
	}
	
	public float getScaleY()
	{
		return objSprite.getScaleY();
	}

	public float getWidth()
	{
		return objSprite.getWidth();
	}
	
	public float getHeight()
	{
		return objSprite.getHeight();
	}
	
	public Rectangle getBoundingRectangle()
	{
		return objSprite.getBoundingRectangle();
	}
	
	public boolean isUpdatable()
	{
		return updatable;
	}
	
	public float getOriginX()
	{
		return objSprite.getOriginX();
	}
	
	public float getOriginY()
	{
		return objSprite.getOriginY();
	}
}