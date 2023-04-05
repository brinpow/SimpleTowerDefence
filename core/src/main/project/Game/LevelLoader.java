package project.Game;

import java.io.DataInput;
import java.io.IOException;

public interface LevelLoader {
    void load(DataInput stream) throws IOException;
    void load(DataInput stream, int timePassed) throws IOException;
}
