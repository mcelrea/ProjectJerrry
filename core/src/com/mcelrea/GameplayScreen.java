package com.mcelrea;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
    boolean debug = false;
    BitmapFont font;
    private int topScreen;
    private int bottomScreen;
    private final int SCREEN_SIZE = 24;

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
        player.setFly(true);
        currentMap = new Map1();
        currentMap.createMap(world);
        topScreen = 12;
        bottomScreen = -12;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        if(player.getBody().getPosition().y >= topScreen){
            bottomScreen = topScreen;
            topScreen = topScreen + SCREEN_SIZE;
            System.out.println("true test");
        }
        if(player.getBody().getPosition().y <= bottomScreen){
            topScreen = bottomScreen;
            bottomScreen = bottomScreen - SCREEN_SIZE;
        }

        camera.position.set(player.getBody().getPosition().x, topScreen-SCREEN_SIZE/2,0);


//        camera.position.set(player.getBody().getPosition().x,
//                0,
//                0);
        camera.update();

        world.step(1/60f, 8, 3);

        batch.begin();
        if(debug)
            drawDebugGUI();
        player.paint(batch, camera);
        batch.end();


        debugRenderer.render(world, camera.combined);
    }

    private void drawDebugGUI() {

        font.draw(batch, "x: " + player.getBody().getPosition().x, 5, 600);
        font.draw(batch, "y: " + player.getBody().getPosition().y, 5, 580);

        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        font.draw(batch, "mouse x: " + x, 5, 560);
        font.draw(batch, "mouse y: " + y, 5, 540);
        Vector3 screenCoords = new Vector3(x, y, 0);
        Vector3 worldCoords = new Vector3(camera.unproject(screenCoords));
        font.draw(batch, "mouse x (world): " + worldCoords.x, 5, 520);
        font.draw(batch, "mouse y (world): " + worldCoords.y, 5, 500);
        font.draw(batch, "small plat x (world): " + (worldCoords.x + 5), 5, 480);
        font.draw(batch, "small plat y (world): " + (worldCoords.y - 2), 5, 460);
        font.draw(batch, "medium plat x (world): " + (worldCoords.x + 10), 5, 440);
        font.draw(batch, "medium plat y (world): " + (worldCoords.y - 2), 5, 420);
        font.draw(batch, "large plat x (world): " + (worldCoords.x + 24), 5, 400);
        font.draw(batch, "large plat y (world): " + (worldCoords.y - 2), 5, 380);
        font.draw(batch, "box plat x (world): " + (worldCoords.x + 2), 5, 360);
        font.draw(batch, "box plat y (world): " + (worldCoords.y - 2), 5, 340);
        font.draw(batch, "canJump: " + player.canJump(), 400, 600);
        font.draw(batch, "jumpCount: " + player.getJumpCount(), 400, 580);
        font.draw(batch, "velocity: " + player.getBody().getLinearVelocity(), 400, 560);

    }

    private void update(float delta) {

        updatePlayer(delta);
        updateEnemies(delta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.F10)) {
            debug = !debug;
        }
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
        font.dispose();
        batch.dispose();
        debugRenderer.dispose();
        world.dispose();
    }
}
