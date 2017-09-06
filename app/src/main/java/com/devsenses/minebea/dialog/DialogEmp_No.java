package com.devsenses.minebea.dialog;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.devsenses.minebea.R;
import com.devsenses.minebea.listener.OnDialogEmp_NoListener;
import com.devsenses.minebea.utils.Utils;


/**
 * Created by Administrator on 26/1/2558.
 */
public class DialogEmp_No extends MaterialDialog.Builder {

    private OnDialogEmp_NoListener callback;
    private View viewDialogEmp_no;
    private EditText editTextEmpNo;
    private Context context;
    public DialogEmp_No(@NonNull Context context, OnDialogEmp_NoListener callback) {
        super(context);
        this.context = context;
        this.callback = callback;
//        this.viewDialogEmp_no = Utils.inflateLayout(context ,R.layout.dialog_emp_no );

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.viewDialogEmp_no = inflater.inflate(R.layout.dialog_emp_no, null);

        this.customView(viewDialogEmp_no , true);
        this.positiveText("OK").positiveColor(R.color.blueText);
        this.negativeText("CANCEL");

        this.cancelable(false);
        autoDismiss(false);

        initUIDialog();
        initEventDialog();

    }
    void initUIDialog(){
        editTextEmpNo = (EditText)viewDialogEmp_no.findViewById(R.id.editTextEmpNo);

    }
    void initEventDialog(){
        callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                Log.d("DIALOG" , "onPositive");
                String emp_no = editTextEmpNo.getText().toString() ;
                if (emp_no.length()>0){
                    autoDismiss(true);
                    callback.onClickDialog_OK(emp_no);
                    Utils.clearKeyboard(context, editTextEmpNo);
                }
                else {
                    //Plest insert Data
                    Toast.makeText(context , context.getResources().getString(R.string.pleaseInput) , Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onNegative(MaterialDialog dialog) {
                Log.d("DIALOG" , "onNegative");
                callback.onClickDialog_Cancel();
                autoDismiss(true);
            }
        });
    }


}
