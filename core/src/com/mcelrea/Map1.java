package com.mcelrea;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tech on 3/17/2015.
 */
public class Map1 implements Map{

    @Override
    public void createMap(World world) {

        new MediumPlatform(world, 0, -3);
        new MediumPlatform(world, 10, 4);
        new LargePlatform(world, 30, -5);
        new SmallPlatform(world, -10, 0);
        new BoxPlatform(world, 35, 10);

        GameplayScreen.enemies.add(new RandomMovementEnemy(world, 10, 10));
        GameplayScreen.enemies.add(new RandomMovementEnemy(world, 15, 10));
        GameplayScreen.enemies.add(new RandomMovementEnemy(world, 18, 10));
        GameplayScreen.enemies.add(new RandomMovementEnemy(world, 1, 10));

    }

    @Override
    public void destroyMap(World world) {

    }
}
