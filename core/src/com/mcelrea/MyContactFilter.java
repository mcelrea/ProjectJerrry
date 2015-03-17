package com.mcelrea;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;


/**
 * Created by Tech on 3/17/2015.
 */
public class MyContactFilter implements ContactFilter{
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
        return true;
    }
}
