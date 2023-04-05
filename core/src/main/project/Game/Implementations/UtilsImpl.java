package project.Game.Implementations;

import com.badlogic.gdx.Gdx;
import project.Constants;
import project.CommonInterfaces.Utils;

import java.time.LocalDateTime;

public class UtilsImpl implements Utils {
    @Override
    public int getMouseX()
    {
        return Gdx.input.getX();
    }

    @Override
    public int getMouseY()
    {
        return Constants.screenHeight - Gdx.input.getY();
    }

    @Override
    public String getCurrentTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        String dataTimeStr = dateTime.toString();
        return dataTimeStr.substring(0,16);
    }

    @Override
    public boolean isButtonJustPressed(int button) {
        return Gdx.input.isButtonJustPressed(button);
    }

    @Override
    public boolean isKeyJustPressed(int key) {
        return Gdx.input.isKeyJustPressed(key);
    }
}
