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

public class Learn_AR extends AppCompatActivity {

    ArFragment arFragment;
    ModelRenderable modelRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn__a_r);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        setupModel();
        setupPlane();


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




}