package com.devsenses.minebea.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.devsenses.minebea.R;
import com.devsenses.minebea.activity.MainActivity;
import com.devsenses.minebea.adapter.SpinnerDialogAdapter;
import com.devsenses.minebea.model.loginmodel.Line;
import com.devsenses.minebea.model.loginmodel.Model;
import com.devsenses.minebea.model.loginmodel.Process;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horus on 1/28/2015.
 */
public class DialogEmpListSelect extends MaterialDialog.Builder implements AdapterView.OnItemSelectedListener{
    private Spinner spinModel , spinLineNo ;
    View view;

    private List<Model> modelData ;
    private SpinnerDialogAdapter spinnerModelAdapter;
    private SpinnerDialogAdapter spinnerLineAdapter;
   // private OnDialogEmpListSelect callback;
    private TextView textEmpNo ;

    private String qrCode;

    public DialogEmpListSelect(@NonNull Context context ,String qrCode , List<Model> modelData) {
        super(context);

        this.modelData = modelData;
       // this.callback = callback;
        this.qrCode = qrCode;


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_emp_listselect, null);

        this.customView(view , true);
        this.positiveText("Login").positiveColor(R.color.blueText);

        this.cancelable(false);


        initUIDialog();
        initData();
        initEventDialog();


    }
    void initUIDialog(){
        textEmpNo = (TextView)view.findViewById(R.id.textEmpNo);
        spinModel  =(Spinner) view.findViewById(R.id.spinnerModel);
        spinLineNo =(Spinner) view.findViewById(R.id.spinnerLineNo);
    }
    void initData(){
        textEmpNo.setText(qrCode);
        List<String> listModel = getListModel();

        addDataSpinerModel();
        spinModel.setOnItemSelectedListener(this);
       // spinLineNo.setAdapter(da);
    }
    void initEventDialog(){
        callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                super.onPositive(dialog);
              //  callback.onDialogEmpListSelectOK();

                int positionModel = spinModel.getSelectedItemPosition();
                int positionLine  = spinLineNo.getSelectedItemPosition();
                int lineId = (int)modelData.get(positionModel).getLines().get(positionLine).getId();
                String lineName = modelData.get(positionModel).getLines().get(positionLine).getTitle();
                int modelId = (int)modelData.get(positionModel).getId();
                String modelName = modelData.get(positionModel).getTitle();

                Intent toMain = new Intent(context , MainActivity.class);

                    Bundle bundle = new Bundle();
                    ArrayList<Process> processData = (ArrayList<Process>) modelData.get(positionModel).getLines().get(positionLine).getProcesses();
                    bundle.putParcelableArrayList(MainActivity.KEY_DATA, processData);
                    bundle.putInt(MainActivity.KEY_LINE_ID , lineId);
                    bundle.putString(MainActivity.KEY_LINE_Name ,lineName );
                    bundle.putInt(MainActivity.KEY_MODEL_ID , modelId);
                    bundle.putString(MainActivity.KEY_MODEL_NAME ,modelName );
                    bundle.putString(MainActivity.KEY_QR_CODE , qrCode);

                toMain.putExtras(bundle);
                context.startActivity(toMain);

            }
        });
    }
    void addDataSpinerModel(){
        spinnerModelAdapter = new SpinnerDialogAdapter(context ,R.layout.item_spiner , getListModel());
        spinModel.setAdapter(spinnerModelAdapter);
    }
    void addDataSpinerLineMo(int modelPosition){
        List<String> arr = getListLine(modelPosition);

        spinnerLineAdapter = new SpinnerDialogAdapter(context, R.layout.item_spiner, arr);
        spinLineNo.setAdapter(spinnerLineAdapter);
        spinnerLineAdapter.notifyDataSetChanged();

    }
    private List<String> getListModel() {
        List<String> listModel = new ArrayList<String>();
        for (int i = 0 ; i < modelData.size() ; i++) {
            listModel.add(modelData.get(i).getTitle());
        }
        return listModel;
    }
    private List<String> getListLine(int modelPosition){
        List<String> listLine = new ArrayList<String>();
        List<Line> line = modelData.get(modelPosition).getLines();
        for (int i = 0 ; i < line.size() ; i++) {
            listLine.add(line.get(i).getTitle());
        }
        return listLine;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("onItemSelected","onItemSelected");
        //Toast.makeText(this.context , "onItemSelected : "+modelData.get(position).getLines().size() , 1).show();

        Log.d("onItemSelected","onItemSelected");
        addDataSpinerLineMo(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
