package com.mcelrea.enemies;

/**
 * Created by Tech on 3/25/2015.
 */
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Sean on 3/25/2015.
 */
public class KinematicEnemy extends Enemy{
    private long changeMovementTime;
    private long movementDuration;

    public KinematicEnemy(World world, float x, float y) {
        super();

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);
        PolygonShape plat = new PolygonShape();
        plat.setAsBox(.2f, 3f);
        fixtureDef.shape = plat;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 1000;
        fixtureDef.density = 90000;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.getFixtureList().first().setUserData(this);
        plat.dispose();

        movementDuration = 1000; //3 seconds
        changeMovementTime = System.currentTimeMillis() + movementDuration;
        body.setLinearVelocity(2,0); //start moving left
    }

    @Override
    public void act(World world, float delta) {

        if(changeMovementTime < System.currentTimeMillis()) {
            int ran = (int) (1 + Math.random() * 2);
            if(ran == 1)
                body.setLinearVelocity(2,0); //start moving left
            else
                body.setLinearVelocity(-2,0); //start moving right
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
