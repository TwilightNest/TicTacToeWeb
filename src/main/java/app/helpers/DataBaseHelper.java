package app.helpers;

import app.models.Game;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseHelper {

    private final String dbUrl = "jdbc:postgresql://185.152.139.127:55450/TicTacToeDb";
    private final String user = "postgres";
    private final String password = "Yara25565j!";

    public Connection connection;

    public DataBaseHelper() {
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void AddGame(Game newGame){
        try {
            String tmp = newGame.grid.toString();
            String query = "insert into games values ('" + newGame.uuid.toString() + "')";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void DeleteGame(String gameUuid){
        try {
            String query = "delete from games where uuid = '" + gameUuid + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<String> GetGamesList(){
        ArrayList<String> result = new ArrayList<>();
        try {
            String query = "select * from games";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                result.add(rs.getString("uuid"));
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}