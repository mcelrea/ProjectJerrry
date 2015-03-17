package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/17/2015.
 */
public class Map1 implements Map{

    @Override
    public void createMap(World world) {

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        //platform 1-1
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,-3);
        PolygonShape plat = new PolygonShape();
        plat.setAsBox(10, 2);
        fixtureDef.shape = plat;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 800;
        Body temp = world.createBody(bodyDef);
        temp.createFixture(fixtureDef);
        temp.getFixtureList().first().setUserData("platform");
        plat.dispose();

    }

    @Override
    public void destroyMap(World world) {

    }
}
