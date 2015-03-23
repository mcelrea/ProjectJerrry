package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/23/2015.
 */
public class SmallPlatform {

    public SmallPlatform(World world, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();

        //platform 1-1
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        PolygonShape plat = new PolygonShape();
        plat.setAsBox(5, 2);
        fixtureDef.shape = plat;
        fixtureDef.friction = 0f;
        fixtureDef.restitution = 0f;
        fixtureDef.density = 800;
        Body temp = world.createBody(bodyDef);
        temp.createFixture(fixtureDef);
        temp.getFixtureList().first().setUserData("platform");
        plat.dispose();
    }
}
