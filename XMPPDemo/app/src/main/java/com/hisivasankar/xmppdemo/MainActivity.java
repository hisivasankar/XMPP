package com.hisivasankar.xmppdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText mInputText;
    private Button mButton;
    private final static XMPPDemo xmpp = new XMPPDemo();

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_send_message) {
            String msg = mInputText.getText().toString();
            new AsyncTask<String, String, Void>() {
                @Override
                protected Void doInBackground(String... params) {
                    Log.d(LOG_TAG, "Message : " + params[0]);
                    xmpp.chatWithUser("b@windows-PC", params[0]);
                    return null;
                }
            }.execute(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.btn_send_message);
        mInputText = (EditText) findViewById(R.id.text_input);

        mButton.setOnClickListener(this);
        xmpp.start();
    }
}
