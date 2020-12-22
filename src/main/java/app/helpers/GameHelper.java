package app.helpers;

import app.models.Game;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GameHelper {
    private static Map<String, List<Session>> sessions;
    private static Map<String, Game> games;
    private static DataBaseHelper dbHelper;

    public GameHelper(){
        sessions = new ConcurrentHashMap<>();
        games = new ConcurrentHashMap<>();
        dbHelper = new DataBaseHelper();
    }

    public void AddGame(String gameUuid){
        Game newGame = new Game(gameUuid);
        games.put(gameUuid, newGame);
        sessions.put(gameUuid, new ArrayList<>());
        dbHelper.AddGame(newGame);
    }

    public void UpdateGame(String gameUuid, String updateMessage) {
        String gameResult = games.get(gameUuid).SetSymbol(Character.toString(updateMessage.charAt(1)), Character.toString(updateMessage.charAt(2)));
        if (gameResult != "")
            SendMessageToAllSessions(gameUuid, gameResult + "win");
    }

    public void RemoveGame(String gameUuid){
        games.remove(gameUuid);
        sessions.remove(gameUuid);
        dbHelper.DeleteGame(gameUuid);
    }

    public void ResetGame(String gameUuid){
        games.get(gameUuid).ResetGame();
    }

    public void AddClientToGame(String gameUuid, Session clientSession){
        sessions.get(gameUuid).add(clientSession);
    }

    public void RemoveClientFromGame(String gameUuid, Session clientSession){
        sessions.get(gameUuid).remove(clientSession);
        if (sessions.get(gameUuid).size() == 0)
            RemoveGame(gameUuid);
    }

    public void AddScore(String gameUuid, char winner){
        if (winner == 'X')
            games.get(gameUuid).countXWins++;
        else
            games.get(gameUuid).countOWins++;
    }

    public boolean CheckIfGameExists(String gameUuid){
        if (games.containsKey(gameUuid))
            return true;
        return false;
    }

    public int GetGamesCount(){
        return games.size();
    }

    public int GetSessionsInGameCount(String gameUuid){
        return sessions.get(gameUuid).size();
    }

    public void SendStartGameMessageToFirstSession(String gameUuid){
        sessions.get(gameUuid).get(0).getAsyncRemote().sendText("StartGame");
    }

    public void SendMessageToAllSessions(String gameUuid, String message){
        for (Session s : sessions.get(gameUuid))
            s.getAsyncRemote().sendText(message);
    }

    public void SendMessageToAllOtherSessions(String gameUuid, Session exceptionSession, String message){
        for (Session s : sessions.get(gameUuid))
            if (s != exceptionSession)
                s.getAsyncRemote().sendText(message);
    }

    public void SendWatchGameMessageToLastSession(String gameUuid){
        int lastIndex = sessions.get(gameUuid).size() - 1;
        sessions.get(gameUuid).get(lastIndex).getAsyncRemote().sendText("WatchPlayer");
    }

    public static Game GetGameByUuid(String uuid){
        return games.get(uuid);
    }
}
