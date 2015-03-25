package com.mcelrea.com.mcelrea.maps;

import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tech on 3/16/2015.
 */
public interface Map {

    public void createMap(World world);
    public void destroyMap(World world);
}
