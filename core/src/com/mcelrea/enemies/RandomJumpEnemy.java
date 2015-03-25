package com.mcelrea.enemies;

/**
 * Created by Tech on 3/25/2015.
 */
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Sean on 3/25/2015.
 */
public class RandomJumpEnemy extends Enemy{

    private long changeJumpTime;
    private long jumpDuration;

    public RandomJumpEnemy(World world, float x, float y){
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

        jumpDuration = 3000; //3 seconds
        changeJumpTime = System.currentTimeMillis() + jumpDuration;
        body.setLinearVelocity(.01f, 0);
    }

    @Override
    public void act(World world, float delta) {

        if(changeJumpTime < System.currentTimeMillis()) {
            int ran = (int) (1 + Math.random() * 3);
            if(ran == 1) {
                //body.setLinearVelocity(.3f,0);
                body.applyLinearImpulse(0, 2000, body.getPosition().x,
                        body.getPosition().y, true);
            }
            if(ran == 2){
                //body.setLinearVelocity(-.3f,0);
                body.applyLinearImpulse(0, -1300, body.getPosition().x,
                        body.getPosition().y, true);
            }
            if(ran == 3)
            {
                //body.setLinearVelocity(-.1f,0);
                body.applyLinearImpulse(0, -1300, body.getPosition().x,
                        body.getPosition().y, true);
            }
        }
    }

    @Override
    public void paint(SpriteBatch batch, OrthographicCamera camera) {

    }

    @Override
    public void die(World world) {

    }
}