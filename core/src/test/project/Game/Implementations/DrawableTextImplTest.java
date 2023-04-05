package project.Game.Implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawableTextImplTest {

    @Test
    void testConstruction(){
        DrawableTextImpl drawableText = new DrawableTextImpl("xyz", 10, 20, 2.5f);
        assertEquals("xyz", drawableText.getText());
        assertEquals(10, drawableText.getX());
        assertEquals(20, drawableText.getY());
        assertEquals(2.5f, drawableText.getScale());
    }

    @Test
    void testSetText() {
        DrawableTextImpl drawableText = new DrawableTextImpl("xyz", 10, 20, 2.5f);
        drawableText.setText("qaz");
        assertEquals("qaz", drawableText.getText());
    }

}