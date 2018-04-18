package il.co.shmulman.www.wifi2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TAG is used for informational messages
    private final static String TAG = "MainActivity 18.4.2018";

    TextView mCapsenseValue;
    Button start_button;
    Button search_button;
    Button connect_button;
    Button discover_button;
    Button disconnect_button;
    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_button = findViewById(R.id.start_button); // Start WiFi
        search_button = findViewById(R.id.search_button);
        connect_button = findViewById(R.id.connect_button);
        discover_button = findViewById(R.id.discoverSvc_button);
        disconnect_button = findViewById(R.id.disconnect_button);
        mCapsenseValue = findViewById(R.id.capsense_value);


        wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("Start WiFi\n");
                if (wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(true);
                }
                else if (!wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                }
            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("search_button\n");
            }
        });

        connect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("connect_button\n");
            }
        });

        discover_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("discover_button\n");
            }
        });

        disconnect_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("disconnect_button\n");
            }
        });

        MyBroadCastReceiver myBroadCastReceiver = new MyBroadCastReceiver();
        // Register the broadcast receiver
        registerReceiver(myBroadCastReceiver,new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            StringBuffer stringBuffer = new StringBuffer();
            List<ScanResult> list = wifiManager.getScanResults();
            for (ScanResult scanResult : list){
                stringBuffer.append(scanResult);
            }
            mCapsenseValue.append(stringBuffer);
        }
    }
}
