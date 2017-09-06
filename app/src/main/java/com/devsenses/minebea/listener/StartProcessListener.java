package com.devsenses.minebea.listener;

/**
 * Created by Administrator on 2/20/2015.
 */
public interface StartProcessListener {
    public void onStartSuccess();
    public void onStartFailure(String text);
    public void onNoInternet();
}
