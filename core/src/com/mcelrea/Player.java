package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/16/2015.
 */
public class Player {

    private Body body;
    private boolean canJump;
    private int jumpCount;
    private int jumpPulse = 13000;
    private boolean fly = false;
    Sprite leftSprite;
    Sprite rightSprite;
    private int dir;
    public static final int LEFT = 1, RIGHT = 2;

    public Player(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);
        PolygonShape box = new PolygonShape();
        box.setAsBox(.5f,.9f);
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
        dir = RIGHT;

        Texture t = new Texture(Gdx.files.internal("tempPlayerLeft.png"));
        leftSprite = new Sprite(t);
        rightSprite = new Sprite(t);
        rightSprite.flip(true, false);
    }

    public void moveRight() {
        body.setLinearVelocity(5,body.getLinearVelocity().y);
        dir = RIGHT;
    }

    public void moveLeft() {
        body.setLinearVelocity(-5,body.getLinearVelocity().y);
        dir = LEFT;
    }

    public void stopMovementX() {
        body.setLinearVelocity(0,body.getLinearVelocity().y);
    }

    public void jump() {
        if(fly || canJump) {
            if(jumpCount == 1) { //if it's double jump don't compound the impulse applied
                body.setLinearVelocity(body.getLinearVelocity().x, 0);
                body.setAwake(true);
            }
            body.applyLinearImpulse(0, jumpPulse, body.getPosition().x,
                    body.getPosition().y, true);
            jumpCount++;
            if(jumpCount == 2) {
                canJump = false;
                jumpCount = 0;
            }
        }
    }
    
    public void update() {
//        if(Math.abs(body.getLinearVelocity().y) <= 0.00001) {
//            canJump = true;
//        }
        System.out.println(body.getLinearVelocity().y);
    }

    public void resetJump() {
        canJump = true;
        jumpCount = 0;
    }

    public Body getBody() {
        return body;
    }

    public boolean isFly() {
        return fly;
    }

    public void setFly(boolean fly) {
        this.fly = fly;
    }

    public void paint(SpriteBatch batch, OrthographicCamera camera) {

        Vector3 worldCoords = new Vector3(body.getPosition().x, body.getPosition().y, 0);

        Vector3 screenCoords = camera.project(worldCoords);

        leftSprite.setPosition(screenCoords.x- leftSprite.getWidth()/2, screenCoords.y- leftSprite.getHeight()/2);
        if(dir == LEFT)
            batch.draw(leftSprite, leftSprite.getX(), leftSprite.getY());
        else if(dir == RIGHT)
            batch.draw(rightSprite, leftSprite.getX(), leftSprite.getY());
    }

    public int getJumpCount() {
        return jumpCount;
    }

    public boolean canJump() {
        return canJump;
    }
}
