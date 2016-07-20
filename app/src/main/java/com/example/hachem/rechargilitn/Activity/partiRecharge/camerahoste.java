package com.example.hachem.rechargilitn.Activity.partiRecharge;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.os.Environment;
import android.util.Log;

import com.commonsware.cwac.camera.PictureTransaction;
import com.commonsware.cwac.camera.SimpleCameraHost;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class camerahoste extends SimpleCameraHost implements Camera.FaceDetectionListener
{
	protected static final String PHOTO_TAKEN = "photo_taken";
	protected String _path;
	private static final String TAG2 = "OCVSample::Activity";
	public static Activity context = null;
	int height = 0;
	int width = 0;
	Boolean isAutoFocus = false;

	public camerahoste(Activity _ctxt, int x, int y, Boolean focus)
	{
		super(_ctxt);
		width = x;
		height = y;
		context = _ctxt;
		isAutoFocus = focus;
		// TODO Auto-generated constructor stub
	}
	public camerahoste(Context _ctxt)
	{
		super(_ctxt);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean useSingleShotMode()
	{
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	protected File getPhotoDirectory()
	{
		// TODO Auto-generated method stub
		return new File(Environment.getExternalStorageDirectory().toString() + "/QuickRecharge");
	}
	@Override
	protected String getPhotoFilename()
	{
		// TODO Auto-generated method stub
		return "/test.jpg";
	}
	@Override
	protected File getPhotoPath()
	{
		// TODO Auto-generated method stub
		return new File(Environment.getExternalStorageDirectory().toString() + "/QuickRecharge/test.jpg");
	}
	@Override
	protected boolean scanSavedImage()
	{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void onFaceDetection(Face[] faces, Camera camera)
	{
		// TODO Auto-generated method stub

	}
	@SuppressWarnings("static-access")
	@Override
	public void saveImage(PictureTransaction xact, byte[] image)
	{

		// TODO Auto-generated method stub
		Log.v("QuickRecharge", "bits");


		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		//Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
		Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

		double scale = (width / 500.) * (bitmap.getHeight() / (double) height);
		Log.v("QuickRecharge", String.valueOf(scale));
		bitmap = bitmap.createBitmap(bitmap, (int) (bitmap.getWidth() - 432 * scale) / 2, (int) (bitmap.getHeight() - 64 * scale) / 2, (int) (432 * scale), (int) (64 * scale));
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		byte[] byteArray = stream.toByteArray();

		super.saveImage(xact, byteArray);
		System.exit(0);
    }
	public void onAutoFocus(boolean success, Camera camera)
	{
		super.onAutoFocus(success, camera);
		isAutoFocus = false;

	}
}