package il.co.shmulman.www.wifi2;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    String display = "";

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
        mCapsenseValue.setMovementMethod(new ScrollingMovementMethod());

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                connection = wifiManager.getConnectionInfo();

                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+3:00"));
                Date currentLocalTime = cal.getTime();
                DateFormat date = new SimpleDateFormat("HH:mm:ss");
                date.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
                String localTime = date.format(currentLocalTime);
                mCapsenseValue.append("onClick is initiated: "+ localTime +"\n");


                List<ScanResult> list;
                // THE ERROR: wifiManager will return zero if inside the
                // Settings -> App -> My App -> Permission - Location is not allowed.
                list = wifiManager.getScanResults();
                for(int i = 0; i < list.size(); i++){
                    mCapsenseValue.append("SSID from list:" + list.get(i).SSID + "\n");
                }

                mCapsenseValue.append("wifiManager list size: "+ list.size() +"\n");

                if (wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(true);
                }
                else if (!wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                }

                display = "connection SSID : " + connection.getSSID() + "\n" +
                          "connection RSSi : " + connection.getRssi() + "\n";
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

    }
}
