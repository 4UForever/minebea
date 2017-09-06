package com.devsenses.minebea.listener;

import com.devsenses.minebea.model.loginmodel.LoginModel;

/**
 * Created by Horus on 2/12/2015.
 */
public interface LoginListener {
    public void onLoginSuccess(LoginModel modelReturn);
    public void onLoginFaile();
}
