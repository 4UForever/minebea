package com.devsenses.minebea.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.devsenses.minebea.R;
import com.devsenses.minebea.listener.OnDialogStopProcessListener;

/**
 * Created by Horus on 2/2/2015.
 */
public class DialogStopRunning extends MaterialDialog.Builder {

    Context context;

    View view;
    EditText edtDescription;
    TextView lbProcessNumber;

    OnDialogStopProcessListener listener;
    String textProcess;

    public DialogStopRunning(@NonNull Context context ,String textProcess , OnDialogStopProcessListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.textProcess = textProcess;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_stopprocess, null);
        customView(view , true);

        positiveText("STOP").positiveColor(context.getResources().getColor(R.color.red));
        negativeText("CANCEL");


        cancelable(false);
        autoDismiss(false);

        initUIDialog();
        initEventClick();
    }
    void initUIDialog(){
        edtDescription = (EditText)view.findViewById(R.id.editTextDescription);
        lbProcessNumber = (TextView)view.findViewById(R.id.textViewProcessNumber);
        lbProcessNumber.setText(textProcess);
    }
    void initEventClick(){
        callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {
                String textComment = edtDescription.getText().toString();
                if (textComment.length() > 0){
                    listener.clickStop(textComment);
                    autoDismiss(true);
                }
                else {
                    Toast.makeText(context, context.getResources().getString(R.string.toast_please_input_comment) , Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                listener.clickCancel();
                autoDismiss(true);
            }
        });
    }

//    public enum TYPE_DIALOG_FAIED{
//        LOGINFAILD , NODATA
//    }
}