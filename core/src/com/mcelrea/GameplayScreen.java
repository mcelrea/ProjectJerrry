package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tech on 3/16/2015.
 */
public class GameplayScreen implements Screen {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    Player player;
    Map currentMap;

    @Override
    public void show() {
        world = new World(new Vector2(0,-9.81f), true);
        world.setContactFilter(new MyContactFilter());
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        player = new Player(world);
        currentMap = new Map1();
        currentMap.createMap(world);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        camera.update();

        world.step(1/60f, 8, 3);

        debugRenderer.render(world, camera.combined);
    }

    private void update(float delta) {

        updatePlayer(delta);
    }

    private void updatePlayer(float delta) {

        player.update();

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.moveRight();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.moveLeft();
        }
        else {
            player.stopMovementX();
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            player.jump();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height/25f;
        camera.viewportWidth = width/25f;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
