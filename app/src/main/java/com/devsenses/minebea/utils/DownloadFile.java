package com.devsenses.minebea.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.devsenses.minebea.R;
import com.devsenses.minebea.constant.Constant;
import com.devsenses.minebea.dialog.LoadingDialog;
import com.devsenses.minebea.type.DownloadType;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;

/**
 * Created by Administrator on 2/23/2015.
 */
public class DownloadFile {
    public static void startDownload(Activity act , String url){

        //final CircleProgressBar progressBar = new CircleProgressBar(act);
        final LoadingDialog progressBar = new LoadingDialog(act);
        progressBar.show();

        Ion.with(act)
                .load(url)
                .progress(new ProgressCallback() {
                    @Override
                    public void onProgress(long downloaded, long total) {
                        progressBar.setProgress((int) downloaded);
                    }
                })
                .write(new File(Environment.getExternalStorageDirectory() + ""))
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File result) {
                        progressBar.dismiss();
                    }
                });
    }
    public static DownloadType downloadFile(Activity activity, String url ,String fileName) {
        String path = Constant.pathExternalStorage + Constant.pathMinebea;
//        Random generator = new Random();
//        int n = 10000;
//        n = generator.nextInt(n);
       // String fname = "Image-" + n + ".jpg";
        String fname = fileName;
        File file = new File(path, fname);
        if (!file.exists()) {
            //file.delete();

            try {
                DownloadManager dmgr = (DownloadManager) activity.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                request.setAllowedOverRoaming(false);
                request.setTitle(fname);
                request.setDescription(activity.getResources().getString(R.string.downloading));
                request.setDestinationInExternalPublicDir(Constant.pathMinebea, fname);
                dmgr.enqueue(request);
                // refresh gellary
                //MediaScannerConnection.scanFile(activity, new String[]{file.getPath()}, new String[]{"image/jpeg"}, null);
               // Toast.makeText(activity , activity.getResources().getString(R.string.downloadSuccess ), Toast.LENGTH_SHORT).show();
                return DownloadType.LOADSUCCESS;
            } catch (Exception e) {
                Toast.makeText(activity , activity.getResources().getString(R.string.downloadNotSuccess ), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                return DownloadType.LOADFAIL;
            }
        }
        else {
           // Toast.makeText(activity , activity.getResources().getString(R.string.downloadNotSuccess )+"SADDS", Toast.LENGTH_SHORT).show();
            return DownloadType.HAVEFILE;
        }
    }
}
