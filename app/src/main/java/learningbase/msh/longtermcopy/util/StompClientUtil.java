package learningbase.msh.longtermcopy.util;

import android.util.Log;

import org.java_websocket.WebSocket;

import rx.Subscriber;
import rx.functions.Action1;
import ua.naiksoftware.stomp.LifecycleEvent;
import ua.naiksoftware.stomp.Stomp;

/**
 * TODO
 * author : 马世豪 29350
 * time   : 2018/04/12
 * version: 1.0
 */
public class StompClientUtil {


    private static final String TAG = "MSH";
    private static ua.naiksoftware.stomp.client.StompClient StompClient;


    public static ua.naiksoftware.stomp.client.StompClient getStompClient() {
        return StompClient;
    }

    public static void init() {
        StompClient = Stomp.over(WebSocket.class, "ws://192.168.1.14:8080/hello/websocket");
        StompClient.connect();
        StompClient.lifecycle().subscribe(new Action1<LifecycleEvent>() {
            @Override
            public void call(LifecycleEvent lifecycleEvent) {
                switch (lifecycleEvent.getType()) {
                    case OPENED:
                        Log.e(TAG, "Stomp connection opened  连接已开启");
                        break;

                    case ERROR:
                        Log.e(TAG, "Stomp Error  连接出错", lifecycleEvent.getException());
                        break;
                    case CLOSED:
                        Log.e(TAG, "Stomp connection closed  连接关闭");
                        break;
                }
            }
        });
    }


    public static void send(String text) {

        getStompClient().send("/app/welcome", text)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }


}
