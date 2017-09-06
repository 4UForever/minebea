package com.devsenses.minebea.constant;

import android.os.Environment;

/**
 * Created by Horus on 2/10/2015.
 */
public class Constant {
    private static String EndNode = "http://staging-minebea.devsenses.net";
   // private static String EndNode = "http://192.168.1.95";  //

    public static String urlLogin = EndNode + "/api/user/login";
    public static String urlProcessStatus = EndNode + "/api/process/check-status";
    public static String urlStartProcess = EndNode + "/api/activity/process/start";
    public static String urlStopProcess = EndNode + "/api/activity/process/end";


    public static String UrlGetDocument = EndNode + "/api/document?qr_code=%s&process_id=%s&product_id=%s";    //s1 = qrcode   s2 = processid  s3 = product_id
    public static String urlGetDocument_PI = EndNode + "/api/document/pi/download?qr_code=%s";
    public static String urlGetDocument_PI_PR = EndNode + "/api/document/pi-pr/download?qr_code=%s";
    public static String urlGetDocument_RE = EndNode + "/api/document/re/download?qr_code=%s";
    public static String urlGetDocument_PI_SET = EndNode + "/api/document/pi-set/download?qr_code=%s";

    public static String getUrlGetDocumentList = EndNode + "/api/document-category?qr_code=%s&line_id=%s&product_id=%s&process_id=%s"; //s1 =qrcode s2 lineId  s3 productId , s4 processId

    public static String pathExternalStorage = Environment.getExternalStorageDirectory()+"";
    public static String pathMinebea = "/Minebea";

}
