package com.devsenses.minebea.dialog;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.devsenses.minebea.R;

/**
 * Created by Horus on 1/28/2015.
 */
public class DialogLoginFaied extends MaterialDialog.Builder {
    public DialogLoginFaied(@NonNull Context context , TYPE_DIALOG_FAIED TYPE) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_loginfaied, null);
        customView(view , true);
        if (TYPE.equals(TYPE_DIALOG_FAIED.NODATA)){
            TextView text = (TextView)view.findViewById(R.id.textDialogFaild);
           text.setText( context.getResources().getString(R.string.textLogin_Nodata));
        }
        positiveText("OK").positiveColor(R.color.blueText);
        autoDismiss(true);
        initEventClick();
    }
    void initEventClick(){
        callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onPositive(MaterialDialog dialog) {

                super.onPositive(dialog);
            }
        });
    }

    public enum TYPE_DIALOG_FAIED{
        LOGINFAILD , NODATA
    }
}
