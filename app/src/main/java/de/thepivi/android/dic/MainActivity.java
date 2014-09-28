package de.thepivi.android.dic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String TAG = "DIC"; // Device Information Companion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceInformation = DeviceContextProvider.toString(getApplicationContext());

        TextView textView = (TextView) findViewById(R.id.main_text);
        textView.setText(deviceInformation);

        Log.v(TAG, deviceInformation);
    }

}
