package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mcelrea.com.mcelrea.maps.Map;
import com.mcelrea.com.mcelrea.maps.Map1;
import com.mcelrea.enemies.Enemy;

import java.util.ArrayList;

/**
 * Created by Tech on 3/16/2015.
 */
public class GameplayScreen implements Screen {

    World world;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    SpriteBatch batch;
    Player player;
    Map currentMap;
    public static ArrayList<Enemy> enemies;
    boolean debug = true;
    BitmapFont font;

    @Override
    public void show() {
        enemies = new ArrayList<Enemy>();
        world = new World(new Vector2(0,-9.81f), true);
        world.setContactFilter(new MyContactFilter());
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        debugRenderer = new Box2DDebugRenderer();
        player = new Player(world);
        player.setFly(false);
        currentMap = new Map1();
        currentMap.createMap(world);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        camera.position.set(player.getBody().getPosition().x,
                            0,
                            0);
        camera.update();

        world.step(1/60f, 8, 3);

        batch.begin();
        if(debug)
            drawDebugGUI();
        batch.end();


        debugRenderer.render(world, camera.combined);
    }

    private void drawDebugGUI() {
        font.draw(batch, "x: " + player.getBody().getPosition().x, 5, 400);
        font.draw(batch, "y: " + player.getBody().getPosition().y, 5, 380);
    }

    private void update(float delta) {

        updatePlayer(delta);
        updateEnemies(delta);
    }

    private void updateEnemies(float delta) {
        for(int i=0; i < enemies.size(); i++) {
            enemies.get(i).act(world, delta);
        }
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
