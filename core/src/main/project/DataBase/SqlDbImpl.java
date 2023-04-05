package project.DataBase;

import project.CommonInterfaces.Counter;

import java.sql.*;

public class SqlDbImpl implements DbInterface{
    private final String path;

    SqlDbImpl(String path)
    {
        this.path = path;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(path);
    }

    void createTableIfNotExists() { /* create raw achievements db */
        try (Connection connection = getConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS achievements " +
                             "(type TEXT PRIMARY KEY, value INTEGER NOT NULL)"))
             {
            sql.executeUpdate();
        } catch (SQLException ex) {
            throw new SqlDbException(ex);
        }

        try (Connection connection = getConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS saves " +
                             "(type INTEGER PRIMARY KEY, value TEXT NOT NULL)"))
        {
            sql.executeUpdate();
        } catch (SQLException ex) {
            throw new SqlDbException(ex);
        }

        for(Counter.AchievementType type: Counter.AchievementType.values())
        {
            try (Connection connection = getConnection();
                 PreparedStatement sql = connection.prepareStatement(
                         "INSERT OR IGNORE INTO achievements (type, value) VALUES (?, ?)")) {
                sql.setString(1, type.toString());
                sql.setInt(2, 0);
                sql.executeUpdate();
            }
            catch (SQLException ex) { throw new SqlDbException(ex); }
        }

        for(int i=0;i<3; i++)
        {
            try (Connection connection = getConnection();
                 PreparedStatement sql = connection.prepareStatement(
                         "INSERT OR IGNORE INTO saves (type, value) VALUES (?, ?)")) {
                sql.setInt(1, i);
                sql.setString(2, "");
                sql.executeUpdate();
            }
            catch (SQLException ex) { throw new SqlDbException(ex); }
        }
    }

    @Override
    public void updateAchievements(String type, int value) {
        try (Connection connection = getConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "UPDATE achievements SET (type, value) = (?, ?) WHERE type = ?")) {
            sql.setString(1, type);
            sql.setInt(2, value);
            sql.setString(3, type);
            sql.executeUpdate();
        }
        catch (SQLException ex) { throw new SqlDbException(ex); }
    }

    @Override
    public int readAchievements(String type) {
        try (Connection connection = getConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "SELECT * FROM achievements WHERE type = ?"))
        {
            sql.setString(1, type);
            ResultSet resultSet = sql.executeQuery();
            resultSet.next();
            return resultSet.getInt("value");
        } catch (SQLException ex) {
            throw new SqlDbException(ex);
        }
    }

    @Override
    public String readSaveDate(int type) {
        try (Connection connection = getConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "SELECT * FROM saves WHERE type = ?"))
        {
            sql.setInt(1, type);
            ResultSet resultSet = sql.executeQuery();
            resultSet.next();
            return resultSet.getString("value");
        } catch (SQLException ex) {
            throw new SqlDbException(ex);
        }
    }

    @Override
    public void updateSaveDate(int type, String date) {
        try (Connection connection = getConnection();
             PreparedStatement sql = connection.prepareStatement(
                     "UPDATE saves SET (type, value) = (?, ?) WHERE type = ?")) {
            sql.setInt(1, type);
            sql.setString(2, date);
            sql.setInt(3, type);
            sql.executeUpdate();
        }
        catch (SQLException ex) { throw new SqlDbException(ex); }
    }
}
