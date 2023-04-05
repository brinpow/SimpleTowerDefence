package project.Game.Implementations;


import project.Game.LevelLoader;
import project.Game.ListInterface;
import project.Game.LogicalShape;
import project.Game.Path;
import project.CommonInterfaces.*;
import project.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LevelLoaderImpl implements LevelLoader {
    private final LevelManager manager;
    LevelLoaderImpl(LevelManager manager) {
        this.manager = manager;
    }

    @Override
    public void load(DataInput stream, int timePassed) throws IOException {
        // background
        String backgroundImgName = stream.readUTF();
        manager.setBackground(new DrawableShape(new Point(0, 0), backgroundImgName));

        // paths
        int nrOfPaths = stream.readInt();
        for (int j = 0; j < nrOfPaths; j++) {
            List<Point> points = new ArrayList<>();
            int nrOfPoints = stream.readInt();
            for (int i = 0; i < nrOfPoints; i++) {
                points.add(new Point(stream.readInt(), stream.readInt()));
            }
            Path path = new PathImpl(points, stream.readInt(), j);
            manager.addPath(path);
            manager.getGameState().getMiscShapes().add(path.getShape());
        }

        // misc shapes
        int nrOfShapes = stream.readInt();
        ListInterface<LogicalShape> miscShapes = manager.getGameState().getMiscShapes();
        for (int i = 0; i < nrOfShapes; i++) {
            miscShapes.add(LogicalShapeFactory.produceRectangle(stream.readInt(), stream.readInt(),
                    stream.readInt(), stream.readInt()));
        }

        // waves
        manager.setWaveManager(new WaveManagerImpl(stream, timePassed));
    }

    @Override
    public void load(DataInput stream) throws IOException {
        load(stream, 0);
    }
}
