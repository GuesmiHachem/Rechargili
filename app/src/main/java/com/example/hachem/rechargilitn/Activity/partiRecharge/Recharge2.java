package com.example.hachem.rechargilitn.Activity.partiRecharge;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import com.commonsware.cwac.camera.CameraHost;
import com.commonsware.cwac.camera.CameraHostProvider;
import com.commonsware.cwac.camera.SimpleCameraHost;
import com.example.hachem.rechargilitn.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import org.opencv.android.BaseLoaderCallback;
//import org.opencv.android.LoaderCallbackInterface;
//import org.opencv.android.OpenCVLoader;
//import org.opencv.core.Mat;


public class Recharge2 extends Activity implements CameraHostProvider,OnGestureListener {
    public static final String lang = "eng";






    protected static final String PHOTO_TAKEN = "photo_taken";
    private camreFragment current=null;
    final String TAG3 = "QuickRecharge";
    public int width = 0;
    public int height = 0;
    private GestureDetector gDetector;


    //  Mat imToprocess;
    //   Mat temp;
    //   Mat finalImage;
    //  Mat finalImagetaille;

    public static final String    TAG2                 = "OCVSample::Activity";
    /*  public BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
          @Override
          public void onManagerConnected(int status) {
              switch (status) {
                  case LoaderCallbackInterface.SUCCESS:
                  {
                      Log.i(TAG2, "OpenCV loaded successfully");
                      // imToprocess=new Mat();
                      // temp=new Mat();
                      // finalImage= new Mat();
                      //  finalImagetaille= new Mat();
                  } break;
                  default:
                  {
                      super.onManagerConnected(status);
                  } break;
              }
          }
      };*/
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();
    }
    @Override
    public void onResume() {
        super.onResume();
        //OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_5,this, mLoaderCallback);
    }
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        String lang = "eng";
        String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/QuickRecharge/";

        setContentView(R.layout.cameraactivity);
        gDetector = new GestureDetector(this);

        File dir = new File(Environment.getExternalStorageDirectory().toString() + "/QuickRecharge/");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.v(TAG3, "ERROR: Creation of directory  QuickRecharge on sdcard failed");
                return;
            } else {
                Log.v(TAG3, "Created directory QuickRecharge on sdcard");
            }
        }


        dir = new File(Environment.getExternalStorageDirectory().toString() + "/QuickRecharge/tessdata/");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.v(TAG3, "ERROR: Creation of directory  QuickRecharge on sdcard failed");
                return;
            } else {
                Log.v(TAG3, "Created directory QuickRecharge on sdcard");
            }
        }


        if (!(new File(DATA_PATH + "tessdata/" + lang + ".traineddata")).exists()) {
            try {

                AssetManager assetManager = getAssets();
                InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
                //GZIPInputStream gin = new GZIPInputStream(in);
                OutputStream out = new FileOutputStream(DATA_PATH + "tessdata/" + lang + ".traineddata");

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                //while ((lenf = gin.read(buff)) > 0) {
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                //gin.close();
                out.close();

                Log.v(TAG3, "Copied " + lang + " traineddata");
            } catch (IOException e) {
                Log.e(TAG3, "Was unable to copy " + lang + " traineddata " + e.toString());
            }
        }


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;


        current=new camreFragment();

        getFragmentManager().beginTransaction().replace(R.id.container, current).commit();


    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub

        super.onStart();

    }


    @Override
    public boolean onTouchEvent(MotionEvent me) {
        return gDetector.onTouchEvent(me);
    }
    @Override
    public CameraHost getCameraHost() {
        return(new SimpleCameraHost(this));
    }
    @Override
    public boolean onDown(MotionEvent e) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub


    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {

        current.makeshot();

        return false;

    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent e) {

        current.takefocus();

    }
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        return false;
    }
}

