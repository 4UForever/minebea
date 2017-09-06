package com.devsenses.minebea.listener;

import com.devsenses.minebea.model.processmodel.ProcessStatusModel;

/**
 * Created by Administrator on 2/20/2015.
 */
public interface ProcessStatusListener {
    public void onProcessRunning(ProcessStatusModel processStatusModel);
    public void onProcessStopping(ProcessStatusModel processStatusModel);
    public void onFailure();


}
