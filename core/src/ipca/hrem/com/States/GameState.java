package ipca.hrem.com.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import ipca.hrem.com.BasicResources.GameViewport;
import ipca.hrem.com.InputManagers.GameInput;
import ipca.hrem.com.InputManagers.InputManager;
import ipca.hrem.com.BasicResources.Map;
import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GameObject;

//GameLogic goes here
public abstract class GameState extends State {
    //-------------------------Constants-------------------------//
    public final static float gameScaleWidth = 15.0f;
    public final static float gameScaleHeight = gameScaleWidth * ((float) Gdx.graphics.getBackBufferHeight() / (float) Gdx.graphics.getBackBufferWidth());

    //-------------------------Variables-------------------------//
    public static Map currentMap;
    public static OrthographicCamera gameCamera, menuCamera;
    public static Viewport currentViewport, currentMenuViewport;

    protected static ArrayList<GameObject> allGameObjects;
    protected static float timeSpeed;
    private static int currentMenuSize;

    //-------------------------GetSetters-------------------------//
    public static int getCurrentMenuSize() {
        return currentMenuSize;
    }

    public static void setCurrentMenuSize(int menuSize) {
        currentMenuSize = menuSize;
        currentViewport.setScreenPosition(currentMenuSize, 0);
        currentViewport.setScreenWidth(Gdx.graphics.getBackBufferWidth() - currentMenuSize);

        currentMenuViewport.setScreenWidth(currentMenuSize);
    }

    //-------------------------Constructor-------------------------//
    public GameState(int menuSize) {
        currentMap = new Map(20, 20);

        gameCamera = new OrthographicCamera();

        currentMenuSize = menuSize;
        currentViewport = new GameViewport(new Point(currentMenuSize, 0), new Vector2(Gdx.graphics.getBackBufferWidth() - currentMenuSize, Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, gameCamera);
        currentViewport.apply();
        gameCamera.position.set(gameCamera.viewportWidth / 2.0f, gameCamera.viewportHeight / 2f, 0);

        menuCamera = new OrthographicCamera();
        currentMenuViewport = new GameViewport(Point.Zero, new Vector2(currentMenuSize, Gdx.graphics.getBackBufferHeight()), gameScaleWidth, gameScaleHeight, menuCamera);
        currentMenuViewport.apply();
        menuCamera.position.set(menuCamera.viewportWidth / 2f, menuCamera.viewportHeight / 2f, 0);

        allGameObjects = new ArrayList<GameObject>();
        timeSpeed = 1.0f;
    }

    public GameState() {
    }

    //-------------------------Functions-------------------------//
    @Override
    public void render(SpriteBatch batch) {
        currentViewport.apply();
        batch.setProjectionMatrix(gameCamera.combined);
        batch.begin();
        currentMap.render(batch);
        renderGame(batch);
        batch.end();

        currentMenuViewport.apply();
        batch.setProjectionMatrix(menuCamera.combined);
        batch.begin();
        renderMenu(batch);
        batch.end();
    }

    protected abstract void renderMenu(SpriteBatch batch);

    protected abstract void renderGame(SpriteBatch batch);

    @Override
    public void dispose() {
        gameCamera = null;
        menuCamera = null;
        currentViewport = null;
        currentMenuViewport = null;
        currentMap.dispose();
        for (GameObject obj: allGameObjects) {
            obj.dispose();
        }
        allGameObjects.clear();
    }

    @Override
    public void resize(int width, int height) {
        currentViewport.update(width - currentMenuSize, height, false);
        currentMenuViewport.update(currentMenuSize, height, false);
    }
}