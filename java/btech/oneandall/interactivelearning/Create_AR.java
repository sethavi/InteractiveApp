package btech.oneandall.interactivelearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class Create_AR extends AppCompatActivity {

    ArFragment arFragment;
    ModelRenderable modelRenderable;
    VideoRecorder videoRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__a_r);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment2);
        setupModel();
        setupPlane();

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(videoRecorder==null)
                {
                    videoRecorder= new VideoRecorder();
                    videoRecorder.setSceneView(arFragment.getArSceneView());
                    int orientation = getResources().getConfiguration().orientation;
                    videoRecorder.setVideoQuality(CamcorderProfile.QUALITY_HIGH,orientation);

                }

                boolean isRec=videoRecorder.onToggleRecord();

                if(isRec)
                    Toast.makeText(getApplicationContext(),"Started Recording",Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(getApplicationContext(),"Stopped Recording",Toast.LENGTH_LONG).show();



            }

        });
    }

    private void setupPlane() {
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor= hitResult.createAnchor();
                AnchorNode anchorNode= new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode);
            }


        });
    }

    private void createModel(AnchorNode anchorNode) {
        TransformableNode node =new TransformableNode(arFragment.getTransformationSystem());
        node.getScaleController().setMaxScale(0.1f);
        node.getScaleController().setMinScale(0.02f);
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }

    private void setupModel() {
        ModelRenderable.builder()
                .setSource(this,R.raw.wolf_01)
                .build()
                .thenAccept(renderable ->modelRenderable=renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();
                    return null;
                });
    }



    @Override
    protected void onResume() {
        super.onResume();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }
}