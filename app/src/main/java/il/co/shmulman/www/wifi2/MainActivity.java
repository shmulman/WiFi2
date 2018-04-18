package il.co.shmulman.www.wifi2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
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
    WifiInfo connection;
    String display;

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



        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                connection = wifiManager.getConnectionInfo();

                mCapsenseValue.append("Start WiFi\n");
                if (wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(true);
                    mCapsenseValue.append("WiFi enabled\n");
                }
                else if (!wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    mCapsenseValue.append("WiFi disabled\n");
                }
                display += "SSID : " + connection.getSSID() + "\n" +
                        "RSSi : " + connection.getRssi() + "\n" +
                        "Mac Address : " + connection.getMacAddress();
                mCapsenseValue.append(display + "\n");
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
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        // Register the broadcast receiver
        registerReceiver(myBroadCastReceiver,intentFilter);
    }

    public class MyBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            mCapsenseValue.append("BroadCast is initiated\n");
            StringBuffer stringBuffer = new StringBuffer();
            List<ScanResult> list = wifiManager.getScanResults();
            for (ScanResult scanResult : list){
                stringBuffer.append(scanResult);
            }
            mCapsenseValue.append(stringBuffer);
        }
    }
}
