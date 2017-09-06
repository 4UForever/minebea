package com.devsenses.minebea.activity;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.devsenses.minebea.R;
import com.devsenses.minebea.dialog.DialogEmpListSelect;
import com.devsenses.minebea.dialog.DialogEmp_No;
import com.devsenses.minebea.dialog.DialogLoginFaied;
import com.devsenses.minebea.listener.LoginListener;
import com.devsenses.minebea.listener.OnDialogEmp_NoListener;
import com.devsenses.minebea.model.loginmodel.LoginModel;
import com.devsenses.minebea.model.loginmodel.Model;
import com.devsenses.minebea.utils.CameraUtils;
import com.devsenses.minebea.utils.Task;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;

import java.util.List;

public class ScanQrActivity extends Activity implements OnQRCodeReadListener , View.OnClickListener {
    private  String TAG = "SCAN_ACTIVITY";


    private int BACK_CAMERA = 0;
    private int FRONT_CAMERA = 1;
    private int CAMETA_TYPE = FRONT_CAMERA;

    private QRCodeReaderView qrDecoderView;
    private ImageView btn_Scan , btn_Emp_no ;



    boolean qrStatus = true;

    private LoginModel modelData;
    String qrCode = "baz";

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        initUI();
        initEvent();
        //openFrontFacingCameraGingerbread();
    }


    void initUI(){
        qrDecoderView = (QRCodeReaderView) findViewById(R.id.qrDecoderView);
//        if (CameraUtils.checkHasCamera(ScanQrActivity.this)){           //Disable Back Camera
//            if (!CameraUtils.checkTypeCameraReady(CAMETA_TYPE)){
//                if (CAMETA_TYPE == FRONT_CAMERA){
//                    CAMETA_TYPE = BACK_CAMERA;
//                }
//                else if (CAMETA_TYPE == BACK_CAMERA){
//                    CAMETA_TYPE = FRONT_CAMERA;
//                }
//            }
            qrDecoderView.setCameraType(CAMETA_TYPE);
//        }


        btn_Scan = (ImageView) findViewById(R.id.imageScan);
        btn_Emp_no = (ImageView) findViewById(R.id.imageEmp_no);
    }
    void initEvent(){
        task = new Task(ScanQrActivity.this);

        qrDecoderView.setOnQRCodeReadListener(this);
        //btn_Scan.setOnClickListener(this);    //disble set camera
        btn_Emp_no.setOnClickListener(this);
    }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
       // qrDecoderView.setOnQRCodeReadListener(null);

        Log.d(TAG, text);
        if (text.length()>0){
            CallServiceLogin(text);   // true
          //  CallServiceLogin(qrCode);    //For Test
        }

    }
    void CallServiceLogin(String emp_no){

        stopCameraPreview();

//        for (int i = 0 ; i<=10;i++){
//            Log.d("CallServiceLogin" , "CallServiceLogin");
//        }
       qrCode = emp_no;


        if (qrStatus){
//            for (int i = 0 ; i<=10;i++){
//                Log.d("CallServiceLogin+++" , "CallServiceLogin++");
//            }
            qrStatus = false;

            task.asyncLogin(null ,qrCode, new LoginListener() {

                @Override
                public void onLoginSuccess(LoginModel modelReturn) {
                    modelData = modelReturn;
                  //  modelReturn.getDatum().getModels().get(0).
                    showDialogListSelect(modelReturn.getDatum().getModels());
                   // stopCameraPreview();
                    for (int ii = 0 ; ii<=10;ii++){
                        Log.d("onLoginSuccess" , "onLoginSuccess");
                    }
                }

                @Override
                public void onLoginFaile() {
                    DialogLoginFaied dialogLoginFaied = new DialogLoginFaied(ScanQrActivity.this , DialogLoginFaied.TYPE_DIALOG_FAIED.LOGINFAILD);
                    dialogLoginFaied.show();
                   startCameraPreview();
                }
            });
        }

    }
    void showDialogListSelect(List<Model> modelList){
        if (modelList.size()>0) {
            DialogEmpListSelect dialogEmpListSelect = new DialogEmpListSelect(this, qrCode, modelList);
            dialogEmpListSelect.show();
        }
        else {
            DialogLoginFaied dialogLoginFaied = new DialogLoginFaied(ScanQrActivity.this , DialogLoginFaied.TYPE_DIALOG_FAIED.NODATA);
            dialogLoginFaied.show();
            startCameraPreview();
        }
    }

    // Called when your device have no camera
    @Override
    public void cameraNotFound() {

    }

    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
       // Toast.makeText(this , "onResume" , Toast.LENGTH_SHORT).show();

       // qrStatus = true;

        startCameraPreview();

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopCameraPreview();
    }
    void startCameraPreview(){
        if (CameraUtils.checkHasCamera(ScanQrActivity.this)){
            qrStatus = true;
            qrDecoderView.getCameraManager().startPreview();
        }
    }
    void stopCameraPreview(){
        if (CameraUtils.checkHasCamera(ScanQrActivity.this)){
            qrDecoderView.getCameraManager().stopPreview();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btn_Scan){
            if (CAMETA_TYPE == BACK_CAMERA){
                CAMETA_TYPE = FRONT_CAMERA;
            }
            else {
                CAMETA_TYPE = BACK_CAMERA;
            }

            stopCameraPreview();

            this.onCreate(null); //Refresh This Activity

        }
        else if (v == btn_Emp_no){
            Log.d(TAG , "CLICK");
            stopCameraPreview();
            DialogEmp_No dialogEmp_no = new DialogEmp_No(this , new OnDialogEmp_NoListener() {
                @Override
                public void onClickDialog_OK(String emp_no) {
                   // stopCameraPreview();
                    CallServiceLogin(emp_no);
                }

                @Override
                public void onClickDialog_Cancel() {
                    //startCameraPreview();
                    startCameraPreview();

                }
            });
            dialogEmp_no.show();
        }
    }


}
