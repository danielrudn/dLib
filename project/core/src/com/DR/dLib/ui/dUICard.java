package com.DR.dLib.ui;

import java.util.ArrayList;

import com.DR.dLib.dObject;
import com.DR.dLib.dTweener;
import com.DR.dLib.dValues;
import com.DR.dLib.animations.AnimationStatusListener;
import com.DR.dLib.animations.ExpandAnimation;
import com.DR.dLib.animations.dAnimation;
import com.DR.dLib.utils.dUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;

public class dUICard extends dObject implements AnimationStatusListener {
	
	/*===========================================================================
	*								VARIABLES								 	|
	*===========================================================================*/
	// Static identifiers for placing objects onto this card
	// HORIZONTAL PLACEMENT IDENTIFIERS
	public static final byte LEFT = 0, RIGHT = 2, LEFT_NO_PADDING = 5, RIGHT_NO_PADDING = 7;
	// BOTH 
	public static final byte CENTER = 1; 
	// VERTICAL  PLACEMENT IDENTIFIERS
	public static final byte TOP = 3, BOTTOM = 4, TOP_NO_PADDING = 6, BOTTOM_NO_PADDING = 8;
	// Shadow distance from X,Y position
	private static final float SHADOW_DISTANCE = 4f;
	// Dimensions of the card
	private float cardWidth, cardHeight;
	// padding between objects 
	private float paddingTop = 8, paddingLeft = 8;
	private boolean hoverEnabled = false, hasShadow = true, isVisible = true, clickable = false;
	// true when the card is clicked
	private boolean isClicked = false;
	public static Color SHADOW_COLOR = new Color(0,0,0,.25f);
	// whether to clip when drawing
	private boolean clip = true, pushedScissors = false;
	private Rectangle scissors = new Rectangle(), clipRect = new Rectangle();
	private float deltaX = 0, deltaY = 0;//distance moved from last position, used for changing pos of objects
	// circle cover for when this card is clickable
	private dImage circleCover = null;
	private dAnimation clickAnim = null;
	private final int CIRCLE_ANIM_ID = 98765;
	
	private ArrayList<dObject> objects = new ArrayList<dObject>();

	/*===========================================================================
	*							   CONSTRUCTORS								 	|
	*===========================================================================*/
	
	public dUICard(Vector2 pos, Texture texture)
	{
		super(pos, texture);
	}
	
	public dUICard(float x, float y, Texture texture)
	{
		super(x, y, texture);
	}
	
	public dUICard(Vector2 pos, Texture texture, float width, float height)
	{
		this(pos, texture);
		cardWidth = width;
		cardHeight = height;
		setDimensions(width,height);
	}
	
	public dUICard(float x, float y, Texture texture, float width, float height)
	{
		this(new Vector2(x, y), texture, width, height);
	}
	
	/*===========================================================================
	*									METHODS								 	|
	*===========================================================================*/
	
	public void addObject(dObject object, byte xAlign, byte yAlign)
	{
		alignObject(object, xAlign, yAlign);
		objects.add(object);
	}
	
	public void addObjectOnTopOf(dObject object, int index)// places object on top of indexes object and aligns x 
	{
		if(index >= 0)
		{
			object.setY(objects.get(index).getY() - objects.get(index).getHeight() - paddingTop*2);
			object.setX(objects.get(index).getX());
			objects.add(object);
		}
	}
	
	public void addObjectUnder(dObject object, int index)// places object under indexes object 
	{
		if(index >= 0)
		{
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() + paddingTop*2);
			object.setX(objects.get(index).getX());
			objects.add(object);
		}
	}
	
	public void addObjectUnder(dObject object, byte xAlign, int index)// places object under indexes object and aligns x 
	{
		if(index >= 0)
		{
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() + paddingTop*2);
			alignObjectX(object, xAlign);
			objects.add(object);
		}
	}
	
	public void addObjectToRightOf(dObject object, int index)// places object to right of indexes object and aligns y
	{
		if(index >= 0)
		{
			object.setX(objects.get(index).getX() + paddingLeft + objects.get(index).getWidth());
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() - object.getHeight());
			objects.add(object);
		}
	}
	
	public void addObjectToLeftOf(dObject object, int index)// places object to right of indexes object and aligns y
	{
		if(index >= 0)
		{
			object.setX(objects.get(index).getX() - paddingLeft - objects.get(index).getWidth());
			object.setY(objects.get(index).getY() + objects.get(index).getHeight() - object.getHeight());
			objects.add(object);
		}
	}
	
	public void removeObject(int index) throws ArrayIndexOutOfBoundsException
	{
		objects.remove(index);
	}

	@Override
	public void update(float delta) {
		if(isVisible)
		{
			if(hoverEnabled)
			{
				checkHover();
			}
			if(clickable)
			{
				checkClicked();
			}
			if(clickAnim != null && clickAnim.isActive())
			{
				clickAnim.update(delta);
			}
			for(int x = 0; x < objects.size(); x++)
			{
				if(objects.get(x).isUpdatable())
				{
					objects.get(x).update(delta);
				}
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if(isVisible)
		{
			if(clip)
			{	
				if(hasShadow)
				{
					getSprite().setColor(SHADOW_COLOR);
					getSprite().setPosition(getX() + SHADOW_DISTANCE, getY() + SHADOW_DISTANCE);
					getSprite().draw(batch);
				}
				batch.flush();
				clipRect.set(getX(), getY(), getWidth(), getHeight());
				ScissorStack.calculateScissors(dValues.camera, batch.getTransformMatrix(), clipRect,  scissors);
				pushedScissors = ScissorStack.pushScissors(scissors);
				if(hasShadow)
				{
					setColor(getColor());
					getSprite().setPosition(getX(),getY());
					getSprite().draw(batch);
				}
				else
				{
					getSprite().draw(batch);
				}
				//render objects contained in card
				for(int x = 0; x < objects.size(); x++)
				{
					objects.get(x).render(batch);
				}
				if(circleCover != null)
				{
					circleCover.render(batch);
				}
				batch.flush();
				if(pushedScissors)
				{
					ScissorStack.popScissors();
				}
			}
			else
			{
				if(hasShadow)
				{
					getSprite().setColor(SHADOW_COLOR);
					getSprite().setPosition(getX() + SHADOW_DISTANCE, getY() + SHADOW_DISTANCE);
					getSprite().draw(batch);
				
					setColor(getColor());
					getSprite().setPosition(getX(),getY());
					getSprite().draw(batch);
				}
				else
				{
					getSprite().draw(batch);
				}
				//render objects contained in card
				for(int x = 0; x < objects.size(); x++)
				{
					objects.get(x).render(batch);
				}
			}
		}
	}
	
	public int getIndexOf(dObject object)//returns index of object in the object list of this card, -1 if not found
	{
		for(int x = 0; x < objects.size(); x++)
		{
			if(object.equals(objects.get(x)))
			{
				return x;
			}
		}
		return -1;
	}
	
	protected void checkHover()
	{
		if(getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY()))
		{
			setAlpha(.25f);
		}
		else
		{
			setAlpha(1f);
		}
	}
	
	private void checkClicked()
	{
		if(getBoundingRectangle().contains(dUtils.getVirtualMouseX(), dUtils.getVirtualMouseY()) && Gdx.input.justTouched())
		{
			isClicked = true;
			if(circleCover == null)
			{
				setAlpha(.6f);
			}
			else
			{
				clickAnim.start();
			}
		}
		else
		{
			isClicked = false;
			if(circleCover == null)
			{
				setAlpha(1f);
			}
		}
	}
	
	private void alignObjectX(dObject object, byte xAlign)
	{
		//align x
		if(xAlign == LEFT)
		{
			object.setX(getX() + paddingLeft);
		}
		else if(xAlign == LEFT_NO_PADDING)
		{
			object.setX(getX());
		}
		else if(xAlign == CENTER)
		{
			object.setX(getX() + getWidth()/2f - object.getWidth()/2f);
		}
		else if(xAlign == RIGHT)
		{
			object.setX(getX() + getWidth() - object.getWidth() - paddingLeft);
		}
		else if(xAlign == RIGHT_NO_PADDING)
		{
			object.setX(getX() + getWidth() - object.getWidth());
		}
	}
	
	private void alignObjectY(dObject object, byte yAlign)
	{
		//align y 
		if(yAlign == TOP)
		{
			object.setY(getY() + paddingTop);
		}
		else if(yAlign == TOP_NO_PADDING)
		{
			object.setY(getY());
		}
		else if(yAlign == CENTER)
		{
			object.setY(getY() + getHeight()/2f - object.getHeight()/2f);
		}
		else if(yAlign == BOTTOM)
		{
			object.setY(getY() + getHeight() - object.getHeight() - paddingTop);
		}
		else if(yAlign == BOTTOM_NO_PADDING)
		{
			object.setY(getY() + getHeight() - object.getHeight());
		}
	}
	
	private void alignObject(dObject object, byte xAlign, byte yAlign)//gets an objects position relative to the card depending on align attributes
	{
		alignObjectX(object, xAlign);
		alignObjectY(object, yAlign);
	}
	
	protected void updateObjectPosition()
	{
		deltaX = position.x - getSprite().getX();
		deltaY = position.y - getSprite().getY();
		getSprite().setPosition(position.x, position.y);
		for(int i = 0; i < objects.size(); i++)
		{
			objects.get(i).setPos(objects.get(i).getX() + deltaX, objects.get(i).getY() + deltaY);
		}
	}
	
	protected void updateObjectPositionForScale(float width, float height)
	{
		deltaX = width - cardWidth;
		deltaY = height - cardHeight;
		for(int i = 0; i < objects.size(); i++)
		{
			if(objects.get(i).getY() > getHeight()/2f)
			{
				objects.get(i).setPos(objects.get(i).getX() + deltaX, objects.get(i).getY() + deltaY);
			}
		}
	}
	
	public void hide()
	{
		isVisible = false;
	}
	
	public void show()
	{
		isVisible = true;
	}
	
	/*===========================================================================
	*									SETTERS								 	|
	*===========================================================================*/
	
	public void setRespondHover(boolean hover)
	{
		hoverEnabled = hover;
	}
	
	public void setHasShadow(boolean shadow)
	{
		hasShadow = shadow;
	}
	
	public void setClickable(boolean click)
	{
		clickable = click;
	}
	
	public void setClickable(boolean click, Texture circle)
	{
		clickable = click;
		if(clickable)
		{
			circleCover = new dImage(getX(), getY(), circle);
			circleCover.setColor(0.8f,0.8f,0.8f,0.25f);
			circleCover.setDimensions(0, 0);
			clickAnim = new ExpandAnimation(circleCover, 2.5f, this, CIRCLE_ANIM_ID, circleCover.getColor(), getWidth()*2f);
		}
	}
	
	public void setWidth(float width)
	{
		updateObjectPositionForScale(width, getHeight());
		cardWidth = width;
		setScaleX(width/getSprite().getRegionWidth());
	}
	
	public void setHeight(float height)
	{
		updateObjectPositionForScale(getWidth(), height);
		cardHeight = height;
		setScaleY(height/getSprite().getRegionHeight());
	}
	
	public void setDimensions(float width, float height)
	{
		cardWidth = width;
		cardHeight = height;
		setWidth(width);
		setHeight(height);
	}
	
	@Override
	public void setPosition(Vector2 pos)
	{
		position = pos;
		updateObjectPosition();
	}
	
	@Override
	public void setPosition(float x, float y)
	{
		position.set(x, y);
		updateObjectPosition();
	}
	
	@Override
	public void setPos(Vector2 pos)
	{
		position = pos;
		updateObjectPosition();
	}
	
	@Override
	public void setPos(float x, float y)
	{
		position.set(x,y);
		updateObjectPosition();
	}
	
	@Override
	public void setX(float x)
	{
		position.set(x, position.y);
		updateObjectPosition();
	}
	
	@Override
	public void setY(float y)
	{
		position.set(position.x, y);
		updateObjectPosition();
	}
	
	public void setPaddingTop(float pad)
	{
		paddingTop = pad;
		updateObjectPosition();
	}
	
	public void setPaddingLeft(float pad)
	{
		paddingLeft = pad;
		updateObjectPosition();
	}
	
	public void setPadding(float pad)
	{
		paddingLeft = pad;
		paddingTop = pad;
		updateObjectPosition();
	}
	
	public void setVisible(boolean v)
	{
		isVisible = v;
	}
	
	public void setClipping(boolean c)
	{
		clip = c;
	}
	
	/*===========================================================================
	*									GETTERS								 	|
	*===========================================================================*/

	public boolean isHoverEnabled()
	{
		return hoverEnabled;
	}
	
	public boolean isClickable()
	{
		return clickable;
	}
	
	public boolean isClicked()
	{
		return isClicked;
	}
	
	@Override
	public float getWidth()
	{
		return cardWidth;
	}
	
	@Override 
	public float getHeight()
	{
		return cardHeight;
	}
	
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public int getObjectCount()
	{
		return objects.size();
	}
	
	public dObject getObject(int index)
	{
		return objects.get(index);
	}
	
	public float getPadding()
	{
		return paddingLeft;
	}
	
	/*===========================================================================
	*							Animation Listener								 |
	*===========================================================================*/
	
	@Override
	public void onAnimationStart(int ID, float duration) {
		if(ID == this.CIRCLE_ANIM_ID)
		{
			circleCover.setPos(dUtils.getVirtualMouseX() - getWidth() / 8f, dUtils.getVirtualMouseY() - getHeight() / 2f);
		}
	}

	@Override
	public void whileAnimating(int ID, float time, float duration,float delta) {
		if(ID == this.CIRCLE_ANIM_ID)
		{
			if(time < duration/2f)
			{
				circleCover.setAlpha(dTweener.LinearEase(time, .25f, -.25f, duration/2f));
			}
		}
	}

	@Override
	public void onAnimationFinish(int ID) {
		
	}
	
}