package com.DR.dLib;

import com.DR.dLib.ui.dButton;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestClass extends ApplicationAdapter{
	SpriteBatch batch;
	static Camera camera;
	
	dButton testButton;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		((OrthographicCamera) camera).setToOrtho(true, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		dValues.VW = 480;
		dValues.VH = 640;
		dValues.camera = camera;
		
		testButton = new dButton(50,50,new Sprite(new Texture("card.png")), "text", new Texture("circle.png"),2.5f);
		testButton.setDimensions(256, 128);
		testButton.setColor(Color.TEAL);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(41f/256f, 128f/256f, 185f/256f,1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		testButton.update(Gdx.graphics.getDeltaTime());
		batch.begin();
		testButton.render(batch);
		batch.end();
	}

}
