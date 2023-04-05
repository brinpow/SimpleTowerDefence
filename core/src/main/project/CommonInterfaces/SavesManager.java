package project.CommonInterfaces;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface SavesManager {
    void save(LevelManager levelManager, DataOutputStream outputData);
    LevelManager load(DataInputStream inputData, ScreenManager screenManager);
}
