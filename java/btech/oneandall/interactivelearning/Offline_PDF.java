package btech.oneandall.interactivelearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.pdfview.PDFView;

public class Offline_PDF extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline__p_d_f);
        pdfView =findViewById(R.id.pdf1);

        pdfView.fromAsset("AM1_18.pdf").show();

    }
}