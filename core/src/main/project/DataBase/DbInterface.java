package project.DataBase;

public interface DbInterface {
    void updateAchievements(String type, int value);
    int readAchievements(String type);
    String readSaveDate(int type);
    void updateSaveDate(int type, String date);
}
