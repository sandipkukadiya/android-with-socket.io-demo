package com.example.sandip.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.net.URISyntaxException;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.0.4:80");

        } catch (URISyntaxException e) {}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSocket.connect();
        Log.i("sdfsdfsd",""+mSocket);
        mSocket.emit("join", "sandip123456789");
        mSocket.on("userjoinedthechat", onuserjoinedthechat);
    }

    private Emitter.Listener onuserjoinedthechat = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            String data = (String) args[0];
            Log.i("Receive", ""+ data);

        }
    };
    protected void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
    }
}
