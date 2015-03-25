package com.mcelrea.enemies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tech on 3/23/2015.
 */
public abstract class Enemy {

    protected Body body;
    protected Sprite sprite;
    protected boolean alive;

    public Enemy() {
        alive = true;
    }

    public abstract void act(World world, float delta);
    public abstract void paint(SpriteBatch batch, OrthographicCamera camera);
    public abstract void die(World world);
}
