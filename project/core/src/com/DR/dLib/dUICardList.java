package com.DR.dLib;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class dUICardList extends dScreen {

	/*
	 * a dUICard container that allows user to scroll through lists, like a ListView from android.
	 */
	
	// list of cards that will appear in the list
	private ArrayList<dUICard> listItems = new ArrayList<dUICard>();
	// deltaY, put in a variable so it can be set to 0 if the user is at the top/bottom of a list
	private float deltaY = 0;
	// title card at the top
	private dUICard titleCard;
	
	public dUICardList(float x, float y, Texture texture, ArrayList<dUICard> list) {
		super(x, y, texture);
		
		titleCard = new dUICard(0,0,texture);
		titleCard.setDimensions(0, 0);
		titleCard.setAlpha(0);
		listItems = list;
		for(int i = 0; i < listItems.size(); i++)
		{
			if(i == 0)
			{
				addObject(listItems.get(i),dUICard.CENTER, dUICard.TOP);
				listItems.get(i).setY(listItems.get(i).getY() + titleCard.getHeight());
			}
			else
			{
				addObjectUnder(listItems.get(i), getIndexOf(listItems.get(i-1)));
			}
		}
		
		// add title card last so it's drawn in the front, and overlaps the list cards
		addObject(titleCard,dUICard.LEFT_NO_PADDING, dUICard.TOP_NO_PADDING);
	}
	
	public dUICardList(float x, float y, Texture texture, dUICard titleCard, ArrayList<dUICard> list) {
		super(x, y, texture);
		
		this.titleCard = titleCard;
		listItems = list;
		for(int i = 0; i < listItems.size(); i++)
		{
			if(i == 0)
			{
				addObject(listItems.get(i),dUICard.CENTER, dUICard.TOP);
				listItems.get(i).setY(listItems.get(i).getY() + titleCard.getHeight());
			}
			else
			{
				addObjectUnder(listItems.get(i), getIndexOf(listItems.get(i-1)));
			}
		}
		
		// add title card last so it's drawn in the front, and overlaps the list cards
		addObject(titleCard,dUICard.LEFT_NO_PADDING, dUICard.TOP_NO_PADDING);
	}
	
	@Override
	public void update(float delta)
	{
		super.update(delta);
		if(Gdx.input.isTouched())
		{
				deltaY = Gdx.input.getDeltaY();
		}
		for(int x = 0; x < listItems.size(); x++)
		{
				// lock at top
				if(listItems.get(0).getY() > titleCard.getHeight() + getPadding())
				{
					deltaY = 0;
					listItems.get(x).setY(titleCard.getHeight() + getPadding() + (x)*(listItems.get(x).getHeight() + getPadding() + 16f));
				}
				// lock at bottom
				else if(listItems.get(listItems.size() - 1).getY() + listItems.get(listItems.size() - 1).getHeight() < getY() + getHeight() - getPadding() && deltaY < 0)
				{
					deltaY = 0;
				}
				// make the scroll speed slower
				deltaY = dTweener.MoveToAndSlow(deltaY, 0, delta/listItems.size());
				listItems.get(x).setY(listItems.get(x).getY() + deltaY);
				if(listItems.get(x).getY() + listItems.get(x).getHeight() < 0 || listItems.get(x).getY() > dValues.VH + 8f)
				{
					listItems.get(x).setVisible(false);
				}
				else
				{
					listItems.get(x).setVisible(true);
				}
		}
	}
	
	public void setTitleCard(dUICard title)
	{
		titleCard = title;
		addObject(titleCard,dUICard.LEFT_NO_PADDING, dUICard.TOP_NO_PADDING);
	}
	
	public dUICard getListItem(int index)
	{
		return listItems.get(index);
	}
	
	public int getSize()
	{
		return listItems.size();
	}

}
