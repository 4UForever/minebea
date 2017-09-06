package com.devsenses.minebea.listener;

/**
 * Created by Administrator on 2/20/2015.
 */
public interface StopProcessListener {
    public void onStopSuccess();
    public void onStopFailure(String text);
    public void onNoInternet();
}
