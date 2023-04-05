package project.Game;

public interface GameState {
    enum GameStatus
    {
        GAME_OVER, GAME_FINISHED, IN_GAME
    }

    ListInterface<Villain> getVillainList();
    ListInterface<Tower> getTowerList();
    ListInterface<Projectile> getProjectileList();
    ListInterface<LogicalShape> getMiscShapes();
    Player getPlayer();
    boolean isGamePaused();
    void setPause(boolean pause);
    GameStatus getStatus();
    void setStatus(GameStatus status);
}
