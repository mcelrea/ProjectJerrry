package com.mcelrea;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;


/**
 * Created by Tech on 3/17/2015.
 */
public class MyContactFilter implements ContactFilter{
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

        if(fixtureA.getUserData() instanceof Player &&
                fixtureB.getUserData().equals("platform")) {
            if(fixtureA.getBody().getPosition().y > fixtureB.getBody().getPosition().y)
                ((Player)(fixtureA.getUserData())).resetJump();

        }
        else if(fixtureB.getUserData() instanceof Player &&
                fixtureA.getUserData().equals("platform")) {
            if(fixtureB.getBody().getPosition().y > fixtureA.getBody().getPosition().y)
                ((Player)(fixtureB.getUserData())).resetJump();

        }



        return true;
    }
}
