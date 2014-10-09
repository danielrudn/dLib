package com.DR.dLib;


import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	dUICard testCard, titleCard, footerCard;
	dUICard ballInfo1, ballInfo2, ballInfo3, ballInfo4;
	dText title, title2, price, title3, price2, title4, price3, title5, price4;
	dButton nextButton;
	static Camera camera;
	boolean moveCard = false, moveBack = false;
	float currentTime = 0;
	Texture ball, card;
	boolean drawCircle = false;
	Sprite circleCover;
	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera( Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		((OrthographicCamera) camera).setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		/*
		ball = new Texture("ball.png");
		card = new Texture("card.png");
		
		circleCover = new Sprite(ball);
		
		title = new dText(0,0,64f,"shop");
		title.setColor(Color.WHITE);
		title2 = new dText(0,0,22f,"Teal ball");
		price = new dText(0,0,20f,"99 .");
		title3 = new dText(0,0,22f,"Red ball");
		price2 = new dText(0,0,20f,"94 .");
		title4 = new dText(0,0,22f,"Cyan ball");
		price3 = new dText(0,0,20f,"89 .");
		title5 = new dText(0,0,22f,"Green ball");
		price4 = new dText(0,0,20f,"199 .");
		
		nextButton = new dButton(0,0,new Sprite(new Texture("button.png")),"next");
		nextButton.setColor(Color.NAVY);		
		
		testCard = new dUICard(10,10,card,Gdx.graphics.getWidth() - 120f,Gdx.graphics.getHeight() - 200f);
		testCard.setColor(Color.WHITE);
		testCard.setPaddingLeft(16f);
		
		titleCard = new dUICard(0,0,card,testCard.getWidth(),75f);
		titleCard.setColor(Color.TEAL);
		titleCard.setHasShadow(false);
		titleCard.addObject(title, dUICard.CENTER, dUICard.CENTER);
		
		footerCard = new dUICard(0,0,card,testCard.getWidth(),48f);
		footerCard.setColor(Color.TEAL);
		footerCard.setHasShadow(false);
		footerCard.addObject(nextButton, dUICard.RIGHT, dUICard.CENTER);
		
		testCard.addObject(titleCard,dUICard.LEFT_NO_PADDING,dUICard.TOP_NO_PADDING);
		testCard.addObject(footerCard, dUICard.LEFT_NO_PADDING, dUICard.BOTTOM_NO_PADDING);
		
		ballInfo1 = new dUICard(0,0,card,150,64);
		ballInfo1.setHasShadow(true);
		ballInfo1.setPaddingLeft(12f);
		dImage ball1 = new dImage(0,0,ball);
		ball1.setColor(Color.TEAL);
		ball1.setDimensions(32, 32);
		ball1.setHasShadow(true);
		ballInfo1.addObject(ball1, dUICard.LEFT, dUICard.CENTER);
		ballInfo1.addObject(title2, dUICard.RIGHT,dUICard.TOP);
		ballInfo1.addObject(price, dUICard.RIGHT,dUICard.BOTTOM);
		
		ballInfo2 = new dUICard(0,0,card,150,64);
		ballInfo2.setHasShadow(true);
		ballInfo2.setPaddingLeft(12f);
		dImage ball2 = new dImage(0,0,ball);
		ball2.setColor(Color.RED);
		ball2.setDimensions(32, 32);
		ball2.setHasShadow(true);
		ballInfo2.addObject(ball2, dUICard.LEFT, dUICard.CENTER);
		ballInfo2.addObject(title3, dUICard.RIGHT,dUICard.TOP);
		ballInfo2.addObject(price2, dUICard.RIGHT,dUICard.BOTTOM);
		
		ballInfo3 = new dUICard(0,0,card,150,64);
		ballInfo3.setHasShadow(true);
		ballInfo3.setPaddingLeft(12f);
		dImage ball3 = new dImage(0,0,ball);
		ball3.setColor(Color.CYAN);
		ball3.setDimensions(32, 32);
		ball3.setHasShadow(true);
		ballInfo3.addObject(ball3, dUICard.LEFT, dUICard.CENTER);
		ballInfo3.addObject(title4, dUICard.RIGHT,dUICard.TOP);
		ballInfo3.addObject(price3, dUICard.RIGHT,dUICard.BOTTOM);
		
		ballInfo4 = new dUICard(0,0,card,150,64);
		ballInfo4.setHasShadow(true);
		ballInfo4.setPaddingLeft(12f);
		dImage ball4 = new dImage(0,0,ball);
		ball4.setColor(Color.GREEN);
		ball4.setDimensions(32, 32);
		ball4.setHasShadow(true);
		ballInfo4.addObject(ball4, dUICard.LEFT, dUICard.CENTER);
		ballInfo4.addObject(title5, dUICard.RIGHT,dUICard.TOP);
		ballInfo4.addObject(price4, dUICard.RIGHT,dUICard.BOTTOM);
		
		testCard.addObjectUnder(ballInfo1, dUICard.LEFT, testCard.getIndexOf(titleCard));
		testCard.addObjectUnder(ballInfo2, dUICard.RIGHT, testCard.getIndexOf(titleCard));
		testCard.addObjectUnder(ballInfo3, dUICard.LEFT, testCard.getIndexOf(ballInfo1));
		testCard.addObjectUnder(ballInfo4, dUICard.RIGHT, testCard.getIndexOf(ballInfo2));	
		*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(236f/256f, 240f/256f, 241f/256f,1f);
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
	/*testCard.update(Gdx.graphics.getDeltaTime());

		if(Gdx.input.justTouched())
		{
			this.drawCircle = true;
			currentTime = 0;
			circleCover.setOriginCenter();
			circleCover.setScale(0.01f);
			circleCover.setPosition(Gdx.input.getX(), Gdx.input.getY());
		}
		*/
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//testCard.render(batch);
		batch.end();
	}
}
