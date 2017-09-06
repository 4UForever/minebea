package com.devsenses.minebea.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.devsenses.minebea.R;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by Horus on 2/11/2015.
 */
public class LoadingDialog extends ProgressDialog {
    private CircleProgressBar progressBar;
    private Context context;
    public LoadingDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_loading);

        setIndeterminate(true);
        setCancelable(false);

        initDialog();

    }
    void initDialog(){
         progressBar = (CircleProgressBar)findViewById(R.id.progress);
         progressBar.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
    }
    public void setProgressDownloading(int persen){
        progressBar.setProgress(persen);
    }


}
