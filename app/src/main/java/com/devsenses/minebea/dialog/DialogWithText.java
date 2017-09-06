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
public class DialogWithText extends MaterialDialog.Builder {
    public DialogWithText(@NonNull Context context,  String message) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_loginfaied, null);
        customView(view, true);

            TextView text = (TextView)view.findViewById(R.id.textDialogFaild);
            text.setText(message);

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

}
