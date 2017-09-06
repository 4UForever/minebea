package com.devsenses.minebea.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;

/**
 * Created by Administrator on 3/26/2015.
 */
public class CameraUtils {


    public static boolean checkHasCamera(Context context){
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            //yes
            Log.i("camera", "This device has camera!");
            return true;
        }else{
            //no
            Log.i("camera", "This device has no camera!");
            return false;
        }
    }

    public static boolean checkTypeCameraReady(int cameraType) {
        boolean typeCameraReady = false;
        int cameraCount = 0;
        Camera cam = null;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
//        Toast.makeText(ScanQrActivity.this, cameraCount + "", Toast.LENGTH_SHORT).show();
        for (int camIdx = 0; camIdx<cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == cameraType) {
                typeCameraReady = true;
            }
        }
        return typeCameraReady;
    }
}
