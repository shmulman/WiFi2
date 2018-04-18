package il.co.shmulman.www.wifi2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // TAG is used for informational messages
    private final static String TAG = "MainActivity 18.4.2018";

    private TextView mCapsenseValue;
    private Button start_button;
    private Button search_button;
    private Button connect_button;
    private Button discover_button;
    private Button disconnect_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start_button = findViewById(R.id.start_button);
        search_button = findViewById(R.id.search_button);
        connect_button = findViewById(R.id.connect_button);
        discover_button = findViewById(R.id.discoverSvc_button);
        disconnect_button = findViewById(R.id.disconnect_button);
        mCapsenseValue = findViewById(R.id.capsense_value);


        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCapsenseValue.append("start_button\n");
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
