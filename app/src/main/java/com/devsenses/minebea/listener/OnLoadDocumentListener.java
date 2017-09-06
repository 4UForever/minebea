package com.devsenses.minebea.listener;


import com.devsenses.minebea.model.documentmodel.DocumenModel;

/**
 * Created by Administrator on 2/23/2015.
 */
public interface OnLoadDocumentListener {
    public void onLoadSuccess(DocumenModel documentModel);
    public void onStartFailure();
}
