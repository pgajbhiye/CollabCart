package com.droid.floatboat.collabcart.collbcartsdk;

import android.util.Log;

import com.droid.floatboat.collabcart.config.Config;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;


/**
 * Created by naveen on 9/27/2015.
 */
public class CollabCart {

    private static final String LOG_TAG = CollabCart.class.getName();

    public static enum Actions {PRODUCT_VIEW, PRODUCT_PURCHASE, CHAT_MESSAGE_POST, CHAT_WINDOW_LEFT}

    public static enum Events {CONNECTED, USER_VIEWING_PRODUCT, USER_PURCHASED_PRODUCT, CONNECT_ERROR ,DISCONNECTED, CHAT_MESSAGE_NEW};

    private Connector socketConnector;

    //Event Listeners
    private Emitter.Listener onRegisteredEvents = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            JSONArray arryOfEvents = (JSONArray) args[0];
            try {
                Log.d(LOG_TAG, "Registered events Received are:"+ arryOfEvents.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    //Product viewed
    private Emitter.Listener onProductViewedByOther = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject details = (JSONObject) args[0];
            try {
                Log.d(LOG_TAG, "Someone Viewed Product:"+ details.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    //Product purchased
    private Emitter.Listener onProductPurchasedByOther = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject details = (JSONObject) args[0];
            try {
                Log.d(LOG_TAG, "Someone purchased Product:"+ details.toString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    //Connected
    private Emitter.Listener onConnected = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(LOG_TAG, "Socket connection established with server");
        }
    };

    //Connection Error
    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d(LOG_TAG, "Socket connection with server is broke");
        }
    };

    public CollabCart(){
        try {
            socketConnector = new Connector();
        } catch (URISyntaxException e) {
            Log.e(LOG_TAG, "Failed to create socket connection", e);
        }
    }

    public CollabCart on(CollabCart.Events evt, Emitter.Listener listener){
        if(socketConnector==null){
            return this;
        }
        Socket mSocket = socketConnector.getConnection();

        switch (evt){
            case CONNECTED:
                mSocket.on(Socket.EVENT_CONNECT, listener);
                break;
            case DISCONNECTED:
                mSocket.on(Socket.EVENT_DISCONNECT, listener);
                break;
            case CONNECT_ERROR:
                mSocket.on(Socket.EVENT_CONNECT_ERROR, listener);
                break;
            case USER_VIEWING_PRODUCT:
                mSocket.on("userViewingProduct", listener);
                break;
            case USER_PURCHASED_PRODUCT:
                mSocket.on("userPurchasedProduct", listener);
                break;
            case CHAT_MESSAGE_NEW:
                mSocket.on("newChatMessage", listener);
                break;
            default:
                Log.d(LOG_TAG, "Not a valid event type to bind listener");
                break;
        }
        return this;
    }

    public CollabCart off(CollabCart.Events evt, Emitter.Listener listener){
        if(socketConnector==null){
            return this;
        }
        Socket mSocket = socketConnector.getConnection();

        switch (evt){
            case CONNECTED:
                mSocket.off(Socket.EVENT_CONNECT, listener);
                break;
            case DISCONNECTED:
                mSocket.off(Socket.EVENT_DISCONNECT, listener);
                break;
            case CONNECT_ERROR:
                mSocket.on(Socket.EVENT_CONNECT_ERROR, listener);
                break;
            case USER_VIEWING_PRODUCT:
                mSocket.off("userViewingProduct", listener);
                break;
            case USER_PURCHASED_PRODUCT:
                mSocket.off("userPurchasedProduct", listener);
                break;
            case CHAT_MESSAGE_NEW:
                mSocket.on("newChatMessage", listener);
                break;
            default:
                Log.d(LOG_TAG, "Not a valid event type to unbind listener");
                break;
        }
        return this;
    }

    public CollabCart notify(CollabCart.Actions action, JSONObject obj){

        if(socketConnector==null){
            return this;
        }
        String actionStr=null;

        switch (action){
            case PRODUCT_VIEW:
                actionStr = "productView";
                break;
            case PRODUCT_PURCHASE:
                actionStr = "productPurchase";
                break;
            case CHAT_MESSAGE_POST:
                actionStr = "postChatMessage";
                break;
            case CHAT_WINDOW_LEFT:
                actionStr = "userLeftChat";
                break;
            default:
                Log.d(LOG_TAG, "Invalid action to notify");
                break;
        }

        if(actionStr==null){
            return this;
        }

        socketConnector.getConnection().emit(actionStr, obj);

        return this;
    }

    public CollabCart connect(){
        if(socketConnector==null){
            return this;
        }
        bindEvents();
        socketConnector.connect();
        return this;
    }

    public void disconnect(){
        if(socketConnector==null){
            return;
        }
        unBindEvents();
        socketConnector.disconnect();
    }

    private void bindEvents(){
        socketConnector.getConnection().on("registeredEvents", onRegisteredEvents);
        this.on(Events.USER_VIEWING_PRODUCT, onProductViewedByOther)
        .on(Events.USER_PURCHASED_PRODUCT, onProductPurchasedByOther)
        .on(Events.CONNECTED, onConnected).on(Events.CONNECT_ERROR, onConnectError);
    }

    private void unBindEvents(){
        socketConnector.getConnection().off();
    }

}

class Connector{
    private Socket mSocket;
    public Connector() throws URISyntaxException{
        mSocket = IO.socket(Config.SERVER_URL);
    }

    public void connect(){
        mSocket.connect();
    }

    public void disconnect(){
        mSocket.disconnect();
    }

    public Socket getConnection(){
        return mSocket;
    }
}