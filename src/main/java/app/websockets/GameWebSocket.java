package app.websockets;

import app.helpers.GameHelper;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/game/{uuid}")
public class GameWebSocket {

    private static final GameHelper gameHelper = new GameHelper();

    @OnMessage
    public void onMessage(Session session, String message, @PathParam("uuid") String uuid) {
        switch (message.length()){
            case 4:
                gameHelper.SendMessageToAllOtherSessions(uuid, session, message);
                gameHelper.UpdateGame(uuid, message);
                break;
            case 7:
                char winner = message.charAt(1);
                if (winner != 'T')
                    gameHelper.AddScore(uuid, winner);
                break;
            case 11:{
                gameHelper.ResetGame(uuid);
                gameHelper.SendMessageToAllSessions(uuid,"resetGame");
                break;
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("uuid") String uuid) {
        if (!gameHelper.CheckIfGameExists(uuid)){
            gameHelper.AddGame(uuid);
        }
        gameHelper.AddClientToGame(uuid, session);

        if (gameHelper.GetSessionsInGameCount(uuid) == 2){
            gameHelper.SendStartGameMessageToFirstSession(uuid);
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("uuid") String uuid) {
        gameHelper.RemoveClientFromGame(uuid, session);
    }

    @OnError
    public void onError(Session session, @PathParam("uuid") String uuid, Throwable throwable) {
        gameHelper.RemoveClientFromGame(uuid, session);
//            sessions.get(uuid).remove(session);
//            if (sessions.get(uuid).size() == 0)
//                sessions.remove(uuid);
//            try {
//                session.close();
//            } catch (IOException ex) {
//            }
    }
}
