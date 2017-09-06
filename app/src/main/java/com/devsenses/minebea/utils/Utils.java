package com.devsenses.minebea.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Horus on 2/13/2015.
 */
public class Utils {
    public static View inflateLayout(Context context , int layoutInflate){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layoutInflate, null);
        return view;
    }
    public static String getDateCurent(){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE,MMMM yy" , Locale.ENGLISH);
        String currentDate = sdf.format(new Date());
        Log.d("currentDate ", currentDate);
        return currentDate;
    }
    public static void clearKeyboard(Context context , EditText editText){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
}
