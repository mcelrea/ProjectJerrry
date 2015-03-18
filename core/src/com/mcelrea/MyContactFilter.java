package com.mcelrea;

import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;


/**
 * Created by Tech on 3/17/2015.
 */
public class MyContactFilter implements ContactFilter{
    @Override
    public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {

//        if(fixtureA.getUserData() instanceof Player &&
//                fixtureB.getUserData().equals("platform")) {
//            ((Player)(fixtureA.getUserData())).resetJump();
//            System.out.println("Contact");
//        }
//        else if(fixtureB.getUserData() instanceof Player &&
//                fixtureA.getUserData().equals("platform")) {
//            ((Player)(fixtureB.getUserData())).resetJump();
//            System.out.println("Contact");
//        }



        return true;
    }
}
