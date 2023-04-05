package project.Game.Implementations;

import org.junit.jupiter.api.Test;
import project.CommonInterfaces.Button;
import project.Point;

import static org.junit.jupiter.api.Assertions.*;

class ButtonImplTest {
    private ButtonImpl createButton(){
        return new ButtonImpl(new Point(0,0), "") {
            @Override
            public void click() {
            }
        };
    }

    @Test
    void testConstruction() {
        ButtonImpl button = createButton();
        assertTrue(button.isActive());
        assertFalse(button.isPressed());
    }

    @Test
    void testSetActiveFalse() {
        ButtonImpl button = createButton();
        button.setActive(false);
        assertFalse(button.isActive());
    }

    @Test
    void testSetActiveTrue() {
        ButtonImpl button = createButton();
        button.setActive(false);
        button.setActive(true);
        assertTrue(button.isActive());
    }

    @Test
    void testChangeStateUnpressed() {
        ButtonImpl button = createButton();
        button.changeState(Button.State.HOVERED);
        assertFalse(button.isPressed());
        button.changeState(Button.State.UNPRESSED);
        assertFalse(button.isPressed());
    }

    @Test
    void testChangeStatePressed() {
        ButtonImpl button = createButton();
        button.changeState(Button.State.PRESSED);
        assertTrue(button.isPressed());
        button.changeState(Button.State.HOVERED);
        assertFalse(button.isPressed());
    }

    @Test
    void testHover(){
        ButtonImpl button = createButton();
        button.hover();
    }
}