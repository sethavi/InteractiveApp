package btech.oneandall.interactivelearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.UUID;

public class Settings extends AppCompatActivity {

    TextView tv;
    private int seconds = 0;
    private boolean running= true;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.activity_settings);
        runTimer();
//        Toast.makeText(Settings.this,uniqueID,Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    private void runTimer()
    {
        TextView timeView = (TextView)findViewById(R.id.textView4);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override

            public void run()
            {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(time.equals("0:00:15"))
                {
                    AlertDialog.Builder builder= new AlertDialog.Builder(Settings.this);
                    builder.setMessage("TAKE SCREEN BREAK");
                    builder.setTitle("Hello !");
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog,
                                            int which)
                        { dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}