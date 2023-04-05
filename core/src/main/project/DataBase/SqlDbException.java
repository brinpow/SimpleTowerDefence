package project.DataBase;

import java.sql.SQLException;

public class SqlDbException extends RuntimeException{
    SqlDbException(SQLException cause) { super(cause); }
}
