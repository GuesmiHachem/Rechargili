package com.example.hachem.rechargilitn.Activity.partiRecharge;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hachem.rechargilitn.R;
import com.googlecode.tesseract.android.TessBaseAPI;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.File;
//SimpleAndroidOCR
import static org.opencv.android.Utils.matToBitmap;

/**
 * Created by d.m.a on 4/24/2016.
 */
public class Recharge1 extends Activity implements View.OnClickListener{
    public static   boolean xxxx = false;
    public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/QuickRecharge/";
    public static final String lang = "eng";
    public static final String TAG = "SimpleAndroidOCR.java";
    public String s;
    public Mat imToprocess;
    public Mat temp;
    public Mat finalImage;
    public Mat finalImagetaille;
    public static final String TAG2="OCVSample::Activity";
    public Button boutonCamera;
    public Button boutonOrange;
    public Button boutonTelecom;
    public Button boutonOoredoo;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this)
    {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {

                    Log.i(TAG2, "OpenCV loaded successfully");
                    imToprocess=new Mat();
                    temp=new Mat();
                    finalImage= new Mat();
                    finalImagetaille= new Mat();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    public void onResume()
    {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_5, this, mLoaderCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        boutonCamera = (Button) findViewById(R.id.buttonCamera);
        boutonOrange = (Button) findViewById(R.id.buttonOrange);
        boutonTelecom = (Button) findViewById(R.id.buttonTelecom);
        boutonOoredoo = (Button) findViewById(R.id.buttonOoredoo);


        boutonCamera.setOnClickListener(this);
        boutonOrange.setOnClickListener(this);
        boutonTelecom.setOnClickListener(this);
        boutonOoredoo.setOnClickListener(this);

    }
    public void recharger(String codeUssd,int x)
    {
        try
        {
            String photoPath = Environment.getExternalStorageDirectory().toString() + "/QuickRecharge/test.jpg";
            File fdelete = new File(photoPath);

            if (fdelete.exists())
            {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);

                ///////////////////////////

                int width, height;
                height = bitmap.getHeight();
                width = bitmap.getWidth();

                Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                Canvas c = new Canvas(bmpGrayscale);
                Paint paint = new Paint();
                ColorMatrix cm = new ColorMatrix();
                cm.setSaturation(0);
                ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
                paint.setColorFilter(f);
                c.drawBitmap(bitmap, 0, 0, paint);
                Utils.bitmapToMat(bmpGrayscale, imToprocess);
                //  imToprocess.imread(bmpGrayscale);
                Utils.bitmapToMat(bmpGrayscale, temp);
                Imgproc.threshold(imToprocess, finalImage, x, 255, Imgproc.THRESH_BINARY);
                Imgproc.resize(finalImage, finalImagetaille, finalImage.size(), 5, 5, Imgproc.INTER_LINEAR);
                // resize(finalImage, finalImagetaille,Size(finalImage.width() * 5,finalImage.height() * 5));
                Bitmap bmpOut = Bitmap.createBitmap(finalImagetaille.cols(), finalImagetaille.rows(), Bitmap.Config.ARGB_8888);
                matToBitmap(finalImagetaille, bmpOut);

                TessBaseAPI baseApi = new TessBaseAPI();
                baseApi.setDebug(true);//getFilesDir().getPath()+"/tessdata/
                baseApi.init(DATA_PATH, lang);
                baseApi.setImage(bmpOut);
                String recognizedText = baseApi.getUTF8Text();
                baseApi.end();
                Log.v(TAG, "OCRED TEXT: " + recognizedText);


                if (lang.equalsIgnoreCase("eng"))
                {
                    // recognizedText = recognizedText.replaceAll("[^a-zA-Z0-9]+", " ");
                    recognizedText = recognizedText.replaceAll("[^0-9]+", " ");
                    // recognizedText = recognizedText.replaceAll("[^0-9]+", " ");
                }


                recognizedText = recognizedText.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String uri = "tel:"+codeUssd+ recognizedText;
                intent.setData(Uri.parse(Uri.parse(uri) + Uri.encode("#")));
                try
                {
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                }

                if (fdelete.exists())
                {
                    if (!fdelete.delete())
                    {
                      Toast.makeText(this,"file not deleted",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                Toast.makeText(this,"image introuvable",Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception m)
        {
            Toast.makeText(this,m.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.buttonCamera)
        {
            Intent intent = new Intent(Recharge1.this, Recharge2.class);
            startActivity(intent);
            intent.putExtra(s, xxxx = true);




        }
        else if(v.getId()==R.id.buttonOrange)
        {
            recharger("*100*",125);
        }
        else if(v.getId()==R.id.buttonOoredoo)
        {
            recharger("*101*",90);
        }
        else if(v.getId()==R.id.buttonTelecom)
        {
            recharger("*123*",125);
        }
    }
}
