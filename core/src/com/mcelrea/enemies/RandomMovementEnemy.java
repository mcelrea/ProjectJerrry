package com.mcelrea.enemies;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/23/2015.
 */
public class RandomMovementEnemy extends Enemy {

    private long changeMovementTime;
    private long movementDuration;

    public RandomMovementEnemy(World world, float x, float y) {
        super();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        PolygonShape plat = new PolygonShape();
        plat.setAsBox(.5f, .5f);
        fixtureDef.shape = plat;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 800;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.getFixtureList().first().setUserData(this);
        plat.dispose();

        movementDuration = 3000; //3 seconds
        changeMovementTime = System.currentTimeMillis() + movementDuration;
        body.setLinearVelocity(4,0); //start moving left
    }

    @Override
    public void act(World world, float delta) {

        if(changeMovementTime < System.currentTimeMillis()) {
            int ran = (int) (1 + Math.random() * 2);
            if(ran == 1)
                body.setLinearVelocity(4,0); //start moving left
            else
                body.setLinearVelocity(-4,0); //start moving right
            changeMovementTime = System.currentTimeMillis() + movementDuration;
        }
    }

    @Override
    public void paint(SpriteBatch batch, OrthographicCamera camera) {

    }

    @Override
    public void die(World world) {

    }
}
