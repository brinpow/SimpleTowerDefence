package project.DataBase;

public class SqlDbFactory {
    public static SqlDbImpl getDb()
    {
        SqlDbImpl db = new SqlDbImpl("jdbc:sqlite:DataBase/game.db");
        db.createTableIfNotExists();
        return db;
    }
}
