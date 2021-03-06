package ipca.hrem.com.InputManagers;

import com.badlogic.gdx.math.Vector2;

import ipca.hrem.com.ObjectResources.TouchableObject;
import ipca.hrem.com.ObjectResources.UIResources.Button;
import ipca.hrem.com.States.ScoreState;

public class ScoreInput extends InputManager{
    //-------------------------Variables-------------------------//
    private ScoreState currentState;

    //-------------------------Constructor-------------------------//
    public ScoreInput(ScoreState currentState){
        this.currentState = currentState;
    }

    //-------------------------Functions-------------------------//
    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Vector2 position = currentState.getCurrentViewport().unproject(new Vector2(x, y));
        TouchableObject touchedUIObject = currentState.findTouchedObject(position);
        if(touchedUIObject == null)
            return false;
        else if(touchedUIObject instanceof Button) {
            ((Button) touchedUIObject).onClick();
            return true;
        }
        return false;
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
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
