package project.Game.Implementations;


import project.Game.*;

public class GameStateImpl implements GameState {
    private final ListInterface<Villain> villainList = new GdxList<>();
    private final ListInterface<Projectile> projectileList = new GdxList<>();
    private final ListInterface<Tower> towerList = new GdxList<>();
    private final ListInterface<LogicalShape> miscShapes = new GdxList<>();
    private final Player player;
    private boolean pause = false;
    private GameStatus status = GameStatus.IN_GAME;


    public GameStateImpl(Player player) {
        this.player = player;
    }

    @Override
    public ListInterface<Villain> getVillainList() {
        return villainList;
    }

    @Override
    public ListInterface<Tower> getTowerList() {
        return towerList;
    }

    @Override
    public ListInterface<Projectile> getProjectileList() {return projectileList;}

    @Override
    public ListInterface<LogicalShape> getMiscShapes() {
        return miscShapes;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override public boolean isGamePaused()
    {
        return pause;
    }

    @Override
    public void setPause(boolean pause)
    {
        this.pause = pause;
    }

    @Override
    public GameStatus getStatus()
    {
        return status;
    }

    @Override
    public void setStatus(GameStatus status)
    {
        this.status = status;
    }
}
