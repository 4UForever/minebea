package com.devsenses.minebea.activity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.devsenses.minebea.R;
import com.devsenses.minebea.constant.Constant;
import com.devsenses.minebea.dialog.DialogStopRunning;
import com.devsenses.minebea.dialog.DialogWithText;
import com.devsenses.minebea.expandview.ExpandView;
import com.devsenses.minebea.fragment.GridMemuDocumentFragment;
import com.devsenses.minebea.fragment.ListDocumentFragment;
import com.devsenses.minebea.listener.OnDialogStopProcessListener;
import com.devsenses.minebea.listener.OnLoadDocumentListener;
import com.devsenses.minebea.listener.ProcessStatusListener;
import com.devsenses.minebea.listener.StartProcessListener;
import com.devsenses.minebea.listener.StopProcessListener;
import com.devsenses.minebea.model.documentmodel.Datum;
import com.devsenses.minebea.model.documentmodel.DocumenModel;
import com.devsenses.minebea.model.errormodel.ErrorModel;
import com.devsenses.minebea.model.loginmodel.Process;
import com.devsenses.minebea.model.processmodel.ProcessStatusModel;
import com.devsenses.minebea.type.DownloadType;
import com.devsenses.minebea.utils.DownloadFile;
import com.devsenses.minebea.utils.Task;
import com.devsenses.minebea.utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

/**
 * Created by Horus on 1/30/2015.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener{

    public static String KEY_DATA = "modelData";
    public static String KEY_LINE_ID = "idLine";
    public static String KEY_LINE_Name = "LineName";
    public static String KEY_MODEL_ID = "idModel";
    public static String KEY_MODEL_NAME = "modelName";

    public static String KEY_QR_CODE = "qrCode";

    private LinearLayout layoutExpandHead;
    private LinearLayout layoutExpandChild;
    LinearLayout layoutLogout;

    List<Process> processModel;
    int lineId;
    int modelId ;
    String lineName;
    String modelName;

    ExpandView expandView;
    TextView lbTextProcessHeader;
    TextView lbTextDate;
    CheckBox chkProcess;

    TextView lbTextModel , lbTextLine , lbTextEmpNo;
    TextView lbStatus;


   // TextView lbNoDocument;
//    GridView gridViewDocument;
//    GridDocumentAdapter gridDocumentAdapter;

    //ImageView imPI , imRE , imPR , imSET;

    int positionProcessSelect = 0;

    String qrCode = "baz";

    FrameLayout frameLayoutDocument;
    DocumenModel documenModel ;

     ProcessStatusModel processStatusModelG;

    boolean RESET_STAT_CLICK_STOP = false;
    boolean statClickStop = RESET_STAT_CLICK_STOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initEvent();
        initIntent();

        addViewExpandProcess();
        initDataProcess();
     //   initGridDocument();
       // loadDocument();
    }
    void initUI(){
        layoutExpandChild = (LinearLayout) findViewById(R.id.ExpandChild);
        //set visibility to GONE
        layoutExpandChild.setVisibility(View.GONE);
        layoutExpandHead = (LinearLayout) findViewById(R.id.ExpandHead);
        layoutLogout = (LinearLayout) findViewById(R.id.layoutLogout);
        lbTextProcessHeader = (TextView)findViewById(R.id.lblListHeader);
        lbTextDate = (TextView)findViewById(R.id.textDate);

        lbTextModel = (TextView)findViewById(R.id.lbTextModel);
        lbTextLine = (TextView)findViewById(R.id.lbTextLine);
        lbTextEmpNo = (TextView)findViewById(R.id.lbTextEmpNo);
        lbStatus = (TextView)findViewById(R.id.lbStatus);
        chkProcess = (CheckBox)findViewById(R.id.chkProcess);

        frameLayoutDocument = (FrameLayout)findViewById(R.id.fragmentDocument);

//
//        lbNoDocument = (TextView)findViewById(R.id.lbNoDocument);
//        lbNoDocument.setVisibility(View.GONE);
//        gridViewDocument = (GridView)findViewById(R.id.gridViewDocument);
//        gridViewDocument.setNumColumns(2);




    }
    void initEvent(){

       // gridViewDocument.setOnItemClickListener(this);
        layoutLogout.setOnClickListener(this);
        expandView = new ExpandView(this , layoutExpandHead , layoutExpandChild);
        layoutExpandHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLICK" , "CLICK");
                if (layoutExpandChild.getVisibility()==View.GONE){
                    //expandView
                    expandView.expand();
                }else{
                    //Close expand view
                    expandView.collapse();
                }
            }
        });
        chkProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (processStatusModelG.getDatum().getAvailable() == true) {

                    if (chkProcess.isChecked()) {
                        //start running process
                        if (!statClickStop) {
                            startProcess();
                        }
                    } else {
                        //stop running process
                        // stopProcess();
                        DialogStopRunning dialogStopRunning = new DialogStopRunning(MainActivity.this, lbTextProcessHeader.getText().toString(), new OnDialogStopProcessListener() {
                            @Override
                            public void clickStop(String textComment) {
                                // Toast.makeText(MainActivity.this , "ok " , Toast.LENGTH_SHORT).show();
                                stopProcess(textComment);
                            }

                            @Override
                            public void clickCancel() {
                                statClickStop = true;
                                chkProcess.setChecked(true);  //set to defualt Running
                                //  Toast.makeText(MainActivity.this , "cancel " , Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialogStopRunning.show();
                    }
                }
                else {
                    DialogWithText dialogWithText = new DialogWithText(MainActivity.this ,processStatusModelG.getDatum().getmVailable_message());
                    dialogWithText.show();
                   // Toast.makeText(MainActivity.this , processStatusModelG.getDatum().getmVailable_message() , Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MainActivity.this , chkProcess.isChecked()+ "" , Toast.LENGTH_SHORT).show();
                    if (!chkProcess.isChecked())
                        chkProcess.setChecked(true);
                    else
                        chkProcess.setChecked(false);
                }
            }
        });


    }
    void initIntent(){
        Bundle bundle = getIntent().getExtras();
        processModel = bundle.getParcelableArrayList(KEY_DATA);

        lineId =    bundle.getInt(KEY_LINE_ID);
        modelId =   bundle.getInt(KEY_MODEL_ID);
        lineName =  bundle.getString(KEY_LINE_Name);
        modelName = bundle.getString(KEY_MODEL_NAME);
        qrCode =    bundle.getString(KEY_QR_CODE);

    }
    void initDataProcess(){
        lbTextProcessHeader.setText(processModel.get(0).getTitle() +" : "+processModel.get(0).getNumber());   //text Process defult
        lbTextDate.setText(Utils.getDateCurent());                     //set Date
        lbTextModel.setText(modelName);
        lbTextLine.setText(lineName);
        lbTextEmpNo.setText(qrCode);

        processStatus(0);  // check process First
    }
    void addViewExpandProcess(){
        LayoutInflater inflater = (LayoutInflater)   getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for ( int i = 0 ; i<processModel.size() ; i++){
            View view = inflater.inflate(R.layout.item_chile_expan, null);
            TextView textProcess = (TextView)view.findViewById(R.id.lblListItem);
            textProcess.setText(processModel.get(i).getTitle() +" : "+processModel.get(i).getNumber());
            layoutExpandChild.addView(view);
            final String textProcessSelect = processModel.get(i).getTitle() +" : "+processModel.get(i).getNumber();
            final int positionProcess = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lbTextProcessHeader.setText(textProcessSelect);   //text Process select to header
                    expandView.collapse(); // close expandView
                    positionProcessSelect = positionProcess;
                    processStatus(positionProcessSelect);
                }
            });
        }
    }
//    void initGridDocument(){
//        gridDocumentAdapter = new GridDocumentAdapter(MainActivity.this );
//        gridViewDocument.setAdapter(gridDocumentAdapter);
//    }


    @Override
    public void onClick(View view) {
        String url;
        if (view == layoutLogout){this.finish();}

    }


    void processStatus(int processPosition){
        statClickStop = RESET_STAT_CLICK_STOP;

        Task.processStatus(MainActivity.this, qrCode, lineId + "", modelId +"", processModel.get(processPosition).getId() + "", new ProcessStatusListener() {

            @Override
            public void onProcessRunning(ProcessStatusModel processStatusModel) {
                processStatusModelG = processStatusModel;
               // Toast.makeText(MainActivity.this, processStatusModel.getDatum().getAvailable()+"", Toast.LENGTH_SHORT).show();
                chkProcess.setChecked(true);  // set button to start running
                lbStatus.setText(getResources().getString(R.string.lbRunning));

                chkProcess.setEnabled(true);

//                if ( processStatusModel.getDatum().getAvailable()){    //Check For Disable Button
//                    chkProcess.setEnabled(true);
//                }
//                else{
//                    chkProcess.setEnabled(false);
//                }
            }

            @Override
            public void onProcessStopping(ProcessStatusModel processStatusModel) {
                processStatusModelG = processStatusModel;
              //  Toast.makeText(MainActivity.this, "Stopping", Toast.LENGTH_SHORT).show();
                lbStatus.setText(getResources().getString(R.string.lbStop));
                chkProcess.setChecked(false);// set button to stop running

                chkProcess.setEnabled(true);

//                if ( processStatusModel.getDatum().getAvailable()){    //Check For Disable Button
//                    chkProcess.setEnabled(true);
//                }
//                else{
//                    chkProcess.setEnabled(false);
//                }

            }

            @Override
            public void onFailure() {
                processStatusModelG = null;
                lbStatus.setText(getResources().getString(R.string.lbFailure));
                chkProcess.setChecked(false);// set button to stop running
                chkProcess.setEnabled(false);

            }
        });

     //  getDocumentList();
        getDocumentList();

    }
    void startProcess(){
        Task.startProcess(MainActivity.this, qrCode, lineId + "", processModel.get(positionProcessSelect).getId() + "", modelId + "", new StartProcessListener() {

            @Override
            public void onStartSuccess() {
                // Toast.makeText(MainActivity.this , "onStartSuccess" , Toast.LENGTH_LONG).show();
                lbStatus.setText(getResources().getString(R.string.lbRunning));
            }

            @Override
            public void onStartFailure(String text) {
//                 Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                ErrorModel errorModel = new Gson().fromJson(text , ErrorModel.class);
                DialogWithText dialogWithText = new DialogWithText(MainActivity.this , errorModel.getMetaDatum().getErrors().get(0).toString());
                dialogWithText.show();
                chkProcess.setChecked(false);
                lbStatus.setText(getResources().getString(R.string.lbStop));
            }

            @Override
            public void onNoInternet() {
                chkProcess.setChecked(false);
                lbStatus.setText(getResources().getString(R.string.lbFailure));
            }
        });
    }
    void stopProcess(String textComment){
        Task.stopProcess(MainActivity.this, qrCode, textComment, lineId + "", processModel.get(positionProcessSelect).getId() + "", modelId + "", new StopProcessListener() {
            @Override
            public void onStopSuccess() {
                // Toast.makeText(MainActivity.this , "onStopSuccess" , Toast.LENGTH_LONG).show();
                lbStatus.setText(getResources().getString(R.string.lbStop));
            }

            @Override
            public void onStopFailure(String text) {
                ErrorModel errorModel = new Gson().fromJson(text , ErrorModel.class);
                DialogWithText dialogWithText = new DialogWithText(MainActivity.this , errorModel.getMetaDatum().getErrors().get(0).toString());
                dialogWithText.show();
                chkProcess.setChecked(true);
                lbStatus.setText(getResources().getString(R.string.lbRunning));
            }

            @Override
            public void onNoInternet() {
                chkProcess.setChecked(true);
                lbStatus.setText(getResources().getString(R.string.lbFailure));
            }
        });
    }
    void getDocumentList(){
        //Task.getDocumentList();
        Task.getDocumentList(MainActivity.this, qrCode, modelId + "", lineId + "", processModel.get(positionProcessSelect).getId() + "", new OnLoadDocumentListener() {
            @Override
            public void onLoadSuccess(DocumenModel documentModel) {
                documenModel = documentModel;
                showGridDocument();
            }

            @Override
            public void onStartFailure() {

            }
        });
    }
    void showGridDocument(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentDocument, GridMemuDocumentFragment.newInstance(documenModel));
            fragmentTransaction.commit();

    }
    public void showListDocument(Datum data){
//        Toast.makeText(MainActivity
//                .this,data.getDocuments().size() +" "+data.getDocuments().get(0).getTitle(), Toast.LENGTH_SHORT).show();;
        //List<Document> d = data.getDocuments();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentDocument, ListDocumentFragment.newInstance(data));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
    }

    public void loadDocument(String url ,final String fileName){

       // DownloadType typeDownload =  DownloadFile.downloadFile(MainActivity.this, "http://staging-minebea.devsenses.net/api/document/pi-pr/download?qr_code=bar", "PI.pdf");
        DownloadType typeDownload =  DownloadFile.downloadFile(MainActivity.this, url+"?qr_code="+qrCode, fileName);
        if (typeDownload.equals(DownloadType.LOADSUCCESS)){
            BroadcastReceiver onComplete = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                   // Toast.makeText(MainActivity.this, getResources().getString(R.string.downloadSuccess), Toast.LENGTH_SHORT).show();

                    File file = new File(Constant.pathExternalStorage + Constant.pathMinebea + "/" + fileName);
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    context.startActivity(intent);
                }
            };
            this.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        }
        else if (typeDownload.equals(DownloadType.HAVEFILE)){
            File file = new File(Constant.pathExternalStorage + Constant.pathMinebea + "/" + fileName);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);

        }
        else if (typeDownload.equals(DownloadType.LOADFAIL)){

        }
    }
    void deleteFile(){
        //Toast.makeText(MainActivity.this , "Delete File" , Toast.LENGTH_SHORT).show();
        File dir = new File(Constant.pathExternalStorage + Constant.pathMinebea);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteFile();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
            getSupportFragmentManager().popBackStack();
    }
    //    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////        Datum data = (Datum) parent.getAdapter().getItem(position);
////        loadDocument(data.getDownloadUrl() , data.getTitle());
//    }


}
