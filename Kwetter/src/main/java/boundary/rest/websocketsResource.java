/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Jeroen
 */
@ServerEndpoint("/sockets/{profilename}")
public class websocketsResource {

    private static HashMap<String, List<Session>> sessions = new HashMap();

    @OnOpen
    public void onOpen(Session session, @PathParam("profilename") String profilename) {
        System.out.println("Session opened, id: %s%n" + session.getId());
        try {
            session.getBasicRemote().sendText("Subscribed on " + profilename);
            List<Session> sessionList = sessions.get(profilename);
            if (sessionList == null) {
                sessionList = new ArrayList();
            }
            sessionList.add(session);
            sessions.put(profilename, sessionList);
            System.out.println(sessions.get(profilename));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, @PathParam("profilename") String profilename) {
        System.out.println(message + " ontvangen van " + profilename);
        try {
            for (Session session : sessions.get(profilename)) {
                session.getBasicRemote().sendText(profilename);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    @OnClose
    public void onClose(Session session, @PathParam("profilename") String profilename) {
        List<Session> sessionList = sessions.get(profilename);
        sessionList.remove(session);
        sessions.put(profilename, sessionList);
        System.out.printf("Session closed with id: %s%n", session.getId());
    }
}
