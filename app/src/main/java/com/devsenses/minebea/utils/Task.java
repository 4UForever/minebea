package com.devsenses.minebea.utils;

import android.app.Activity;
import android.util.Log;

import com.devsenses.minebea.constant.Constant;
import com.devsenses.minebea.dialog.LoadingDialog;
import com.devsenses.minebea.listener.LoginListener;
import com.devsenses.minebea.listener.OnLoadDocumentListener;
import com.devsenses.minebea.listener.ProcessStatusListener;
import com.devsenses.minebea.listener.StartProcessListener;
import com.devsenses.minebea.listener.StopProcessListener;
import com.devsenses.minebea.model.documentmodel.DocumenModel;
import com.devsenses.minebea.model.loginmodel.LoginModel;
import com.devsenses.minebea.model.processmodel.ProcessStatusModel;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * Created by Horus on 2/12/2015.
 */
public class Task {
    Activity act;
    public Task(Activity act) {
        this.act = act;
    }

    public  void asyncLogin(Activity act ,String qrCode , final LoginListener listener){
       final LoadingDialog dialog = new LoadingDialog(this.act);
       dialog.show();



        for (int ii = 0 ; ii<=10;ii++){
            Log.d("asyncLogin", "asyncLogin");
        }

        String url = Constant.urlLogin;

        RequestParams params = new RequestParams();
        params.put("qr_code", qrCode);

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(this.act , url , params , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                if (statusCode == 200){
                    Gson gson = new Gson();
                    LoginModel modelData = gson.fromJson(new String(responseBody), LoginModel.class);
                    listener.onLoginSuccess(modelData);

                }
                else {
                    listener.onLoginFaile();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onLoginFaile();
                dialog.dismiss();

            }

        });


//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("qr_code", qrCode);
//
//        AQuery aq = new AQuery(act);
//        aq.ajax(url, params, JSONObject.class, new AjaxCallback<JSONObject>() {
//
//            @Override
//            public void callback(String url, JSONObject json, AjaxStatus status) {
//
//                if (status.getCode() == 200){
//                    Gson gson = new Gson();
//                    LoginModel modelData = gson.fromJson(new String(json.toString()), LoginModel.class);
//                    listener.onLoginSuccess(modelData);
//
//                }
//                else {
//                    listener.onLoginFaile();
//                }
//                dialog.dismiss();
//
//
//            }
//        });


    }

    public static void processStatus(final Activity act , String qr_code , String line_id , String product_id , String process_id ,final ProcessStatusListener listener){
        final int STATUS_RUNNING = 1;
        final int STATUS_STOPPING = 0;
        String url = Constant.urlProcessStatus;

        RequestParams params = new RequestParams();
        params.put("qr_code", qr_code);
        params.put("line_id", line_id);
        params.put("product_id", product_id);
        params.put("process_id", process_id);

        final LoadingDialog dialog = new LoadingDialog(act);
        dialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
           client.post(act , url , params ,new AsyncHttpResponseHandler() {
               @Override
               public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                    if (statusCode == 200) {
                        Gson gson = new Gson();
                        ProcessStatusModel processStatusModel = gson.fromJson(new String(bytes), ProcessStatusModel.class);
                        if ((int) processStatusModel.getDatum().getStatus() == STATUS_RUNNING) {
                            listener.onProcessRunning(processStatusModel);
                        } else if ((int) processStatusModel.getDatum().getStatus() == STATUS_STOPPING) {
                            listener.onProcessStopping(processStatusModel);
                        }
                    }
                   else {
                        listener.onFailure();
                    }
                   dialog.dismiss();
               }

               @Override
               public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                   listener.onFailure();
                   dialog.dismiss();
               }
           });
    }
    public static void startProcess(final Activity act , String qr_code , String line_id , String process_id , String product_id ,final StartProcessListener listener){

        String url = Constant.urlStartProcess;

        RequestParams params = new RequestParams();
        params.put("qr_code", qr_code);
        params.put("line_id", line_id);
        params.put("process_id", process_id);
        params.put("product_id" ,product_id );

        final LoadingDialog dialog = new LoadingDialog(act);
        dialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(act , url , params ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
               listener.onStartSuccess();
               dialog.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if (bytes != null)
                    listener.onStartFailure(new String(bytes));
                else
                    listener.onNoInternet();

                dialog.dismiss();
            }
        });
    }
    public static void stopProcess(final Activity act , String qr_code ,String textCommemt , String line_id , String process_id ,String product_id ,final StopProcessListener listener){

        String url = Constant.urlStopProcess;

        RequestParams params = new RequestParams();
        params.put("qr_code", qr_code);
        params.put("comment", textCommemt);
        params.put("line_id", line_id);
        params.put("process_id", process_id);
        params.put("product_id" ,product_id );

        final LoadingDialog dialog = new LoadingDialog(act);
        dialog.show();

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(act , url , params ,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                listener.onStopSuccess();
                dialog.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                if (bytes != null)
                    listener.onStopFailure(new String(bytes));
                else
                    listener.onNoInternet();

                dialog.dismiss();
            }
        });
    }

//    public static void getDocumentList(Activity act , String qrCode ,String process_id , String product_id , final OnLoadDocumentListener listener){
//        String url =  String.format(Constant.UrlGetDocument , qrCode , process_id , product_id);
//        final LoadingDialog dialog = new LoadingDialog(act);
//        dialog.show();
//
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.get(url , new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                Gson gson = new Gson();
//                DocumentModel documentModel = gson.fromJson(new String(responseBody), DocumentModel.class);
//
//                listener.onLoadSuccess(documentModel);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                listener.onStartFailure();
//                dialog.dismiss();
//            }
//        });
//
//    }
    public static void getDocumentList(Activity act , String qrCode , String modelId , String lineId , String processId ,final OnLoadDocumentListener listener){
        String url = String.format(Constant.getUrlGetDocumentList , qrCode , lineId , modelId , processId);////s1 =qrcode s2 lineId  s3 productId , s4 processId
        final LoadingDialog dialog = new LoadingDialog(act);
        dialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                DocumenModel documenModel = gson.fromJson(new String(responseBody) , DocumenModel.class);
                listener.onLoadSuccess(documenModel);
                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                listener.onStartFailure();
                dialog.dismiss();
            }
        });

    }

}
