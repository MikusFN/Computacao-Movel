package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.BasicResources.Point;
import ipca.hrem.com.MainGame;
import ipca.hrem.com.ObjectResources.GridCell;
import ipca.hrem.com.ObjectResources.UIResources.Button;
import ipca.hrem.com.ObjectResources.UIResources.UIObject;
import ipca.hrem.com.States.BuildState;
import ipca.hrem.com.States.GameState;

public class BuildInput extends InputManager {
    //-------------------------Variables-------------------------//
    BuildState currentState;
    GridCell.CellType currentSelectedCellType;
    boolean isBuildingWalls;
    Vector2 firstPosition;

    //-------------------------GetSetters-------------------------//

    public void setCurrentSelectedCellType(GridCell.CellType currentSelectedCellType) {
        this.currentSelectedCellType = currentSelectedCellType;
    }

    public void setBuildingWalls(boolean buildingWalls) {
        isBuildingWalls = buildingWalls;
    }

    //-------------------------Constructor-------------------------//
    public BuildInput(BuildState state){
        currentState = state;
        isBuildingWalls = false;
        firstPosition = null;
    }
    //-------------------------Functions-------------------------//

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        if(isBuildingWalls && x > GameState.getCurrentMenuSizeScreen()){
            firstPosition = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
        }
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (x > GameState.getCurrentMenuSizeScreen()) {            //SE ESTIVER NO VIWEPORT.
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
            if(currentState.getSelectedObject() != null){
                MainGame.currentPlayer.currentMap.setTile(Point.fromVector2(touchedPositionOnWorld), currentSelectedCellType);
            }

        } else {                                                //SE ESTIVER NO MENU.
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentMenuViewport.unproject(new Vector2(x, y)));
            UIObject touchableObjectSelectedThisFrame = currentState.findTouchedObject(touchedPositionOnWorld);
            if (touchableObjectSelectedThisFrame instanceof Button)
                ((Button)touchableObjectSelectedThisFrame).onClick();
        }
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        if(isBuildingWalls){
            Vector2 touchedPositionOnWorld = new Vector2(GameState.currentViewport.unproject(new Vector2(x, y)));
        }
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        MainGame.currentPlayer.gameCamera.zoom += (initialDistance - distance) * MOVEMENT_SPEED * 0.1 * Gdx.graphics.getDeltaTime();
        if (MainGame.currentPlayer.gameCamera.zoom < MIN_ZOOM)
            MainGame.currentPlayer.gameCamera.zoom = MIN_ZOOM;
        else if (MainGame.currentPlayer.gameCamera.zoom > MAX_ZOOM)
            MainGame.currentPlayer.gameCamera.zoom = MAX_ZOOM;

        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}