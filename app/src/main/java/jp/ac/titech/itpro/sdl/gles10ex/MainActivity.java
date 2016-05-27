package jp.ac.titech.itpro.sdl.gles10ex;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnTouchListener{
    private final static String TAG = "MainActivity";

    private GLSurfaceView glView;
    private SimpleRenderer renderer;
    private SeekBar rotationBarX, rotationBarY, rotationBarZ;
    private float startx, starty;
    private float rotatex, rotatey;
    private float startrx, startry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        glView = (GLSurfaceView) findViewById(R.id.glview);

        rotationBarX = (SeekBar) findViewById(R.id.rotation_bar_x);
        rotationBarY = (SeekBar) findViewById(R.id.rotation_bar_y);
        rotationBarZ = (SeekBar) findViewById(R.id.rotation_bar_z);
        rotationBarX.setOnSeekBarChangeListener(this);
        rotationBarY.setOnSeekBarChangeListener(this);
        rotationBarZ.setOnSeekBarChangeListener(this);

        renderer = new SimpleRenderer();
        renderer.addObj(new Figure1(1.0f, 0, 0, 0));
        glView.setRenderer(renderer);
        glView.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        glView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        glView.onPause();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == rotationBarX) {
            renderer.setRotationX(progress);
            rotatex = progress;
        }
        else if (seekBar == rotationBarY){
            renderer.setRotationY(progress);
            rotatey = progress;
        }
        else if (seekBar == rotationBarZ)
            renderer.setRotationZ(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //Log.d(TAG, "onTouch");
        float touchingX = event.getX();
        float touchingY = event.getY();
        String touchingAction = "";

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                touchingAction = "MOVE";
                rotatex = (touchingY - starty + startrx) % 360;
                rotatey = (touchingX - startx + startry) % 360;
                renderer.setRotationX(rotatex);
                renderer.setRotationY(rotatey);
                rotationBarX.setProgress((int)rotatex);
                rotationBarY.setProgress((int)rotatey);
                break;

            case MotionEvent.ACTION_DOWN:
                touchingAction = "DOWN";
                startx = touchingX;
                starty = touchingY;
                startrx = rotatex;
                startry = rotatey;
                rotationBarX.setEnabled(false);
                rotationBarY.setEnabled(false);
                break;

            case MotionEvent.ACTION_UP:
                touchingAction = "UP";
                rotationBarX.setEnabled(true);
                rotationBarY.setEnabled(true);
                break;
            default:
                touchingAction = "";
                break;
        }
        //Log.d(TAG, touchingAction);
        return true;
    }

}
