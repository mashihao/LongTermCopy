package learningbase.msh.longtermcopy.app;

import android.app.Application;

import learningbase.msh.longtermcopy.util.StompClientUtil;


public class MyApplication extends Application {
    private static MyApplication myApplication;
    ua.naiksoftware.stomp.client.StompClient StompClient;

    public ua.naiksoftware.stomp.client.StompClient getStompClient() {
        return StompClient;
    }

    private static final String TAG = "MSH";

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        StompClientUtil.init();
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }
}
