package project.Game.Implementations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import project.Game.Clickable;
import project.Game.Manager;
import project.CommonInterfaces.Utils;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClickableManager implements Manager.ObjectManager<Clickable>{
    Queue<Clickable> clickableList = new ConcurrentLinkedQueue<>();
    private final Utils utils;

    ClickableManager(Utils utils)
    {
        this.utils = utils;
    }

    @Override
    public void update() {
        int posX = utils.getMouseX();
        int posY = utils.getMouseY();
        for(Clickable clickable:clickableList) {
            if(clickable.contains(posX,posY) && utils.isButtonJustPressed(Input.Buttons.LEFT)) {
                for (Iterator<Clickable.ClickFunction> it = clickable.getClickFunctions(); it.hasNext(); ) {
                    Clickable.ClickFunction function = it.next();
                    function.click();
                }
            }
        }
    }

    public void addNewObject(Clickable clickable)
    {
        clickableList.add(clickable);
    }

    public void removeObject(Clickable clickable)
    {
        clickableList.remove(clickable);
    }

    public void clear()
    {
        clickableList.clear();
    }
}
