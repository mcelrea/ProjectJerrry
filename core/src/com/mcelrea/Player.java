package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/16/2015.
 */
public class Player {

    private Body body;
    private boolean canJump;
    private int jumpCount;

    public Player(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);
        PolygonShape box = new PolygonShape();
        box.setAsBox(.5f,1f);
        fixtureDef.shape = box;
        fixtureDef.density = 800;
        fixtureDef.restitution = 0f;
        fixtureDef.friction = 0f;
        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);
        body.setFixedRotation(true);
        body.getFixtureList().first().setUserData(this);
        box.dispose();

        canJump = true;
    }

    public void moveRight() {
        body.setLinearVelocity(5,body.getLinearVelocity().y);
    }

    public void moveLeft() {
        body.setLinearVelocity(-5,body.getLinearVelocity().y);
    }

    public void stopMovementX() {
        body.setLinearVelocity(0,body.getLinearVelocity().y);
    }

    public void jump() {
        if(canJump) {
            body.applyLinearImpulse(0, 13000, body.getPosition().x,
                    body.getPosition().y, true);
            jumpCount++;
            if(jumpCount == 2) {
                canJump = false;
                jumpCount = 0;
            }
        }
    }
    
    public void update() {
        if(Math.abs(body.getLinearVelocity().y) <= 0.00001) {
            canJump = true;
        }
        System.out.println(body.getLinearVelocity().y);
    }

    public void resetJump() {
        canJump = true;
    }

}
