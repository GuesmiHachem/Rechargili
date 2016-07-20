package com.example.hachem.rechargilitn.Activity.partiRecharge;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.commonsware.cwac.camera.CameraFragment;
import com.commonsware.cwac.camera.CameraView;
import com.commonsware.cwac.camera.PictureTransaction;
import com.commonsware.cwac.camera.SimpleCameraHost;
import com.example.hachem.rechargilitn.R;


public class camreFragment extends CameraFragment
{

    int click = 1;
    //ProgressDialog pd;
    Boolean isAutofocus = false;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        //  pd = new ProgressDialog(getActivity());
        SimpleCameraHost.Builder builder= new SimpleCameraHost.Builder(new camerahoste(getActivity(),width,height,isAutofocus));
        setCameraHost(builder.useFullBleedPreview(true).build());
    }
    @SuppressLint("ClickableViewAccessibility") @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        View content=inflater.inflate(R.layout.camera, container, false);
        CameraView cameraView=(CameraView)content.findViewById(R.id.camera);
        setCameraView(cameraView);
        //ImageView iv = (ImageView) content.findViewById(R.id.imageView1);
        return(content);
    }

    public void makeshot()
    {
        PictureTransaction xact = new PictureTransaction(getCameraHost());
        takePicture(xact);
        click = 1;
    }
    public void takefocus()
    {
        autoFocus();
    }

}
