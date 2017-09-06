package com.devsenses.minebea.model.documentmodel;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class RequestParam implements Parcelable{

    private static final String FIELD_PROCESS_ID = "process_id";
    private static final String FIELD_QR_CODE = "qr_code";
    private static final String FIELD_PRODUCT_ID = "product_id";
    private static final String FIELD_LINE_ID = "line_id";


    @SerializedName(FIELD_PROCESS_ID)
    private int mProcessId;
    @SerializedName(FIELD_QR_CODE)
    private String mQrCode;
    @SerializedName(FIELD_PRODUCT_ID)
    private int mProductId;
    @SerializedName(FIELD_LINE_ID)
    private int mLineId;


    public RequestParam(){

    }

    public void setProcessId(int processId) {
        mProcessId = processId;
    }

    public int getProcessId() {
        return mProcessId;
    }

    public void setQrCode(String qrCode) {
        mQrCode = qrCode;
    }

    public String getQrCode() {
        return mQrCode;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setLineId(int lineId) {
        mLineId = lineId;
    }

    public int getLineId() {
        return mLineId;
    }

    public RequestParam(Parcel in) {
        mProcessId = in.readInt();
        mQrCode = in.readString();
        mProductId = in.readInt();
        mLineId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RequestParam> CREATOR = new Creator<RequestParam>() {
        public RequestParam createFromParcel(Parcel in) {
            return new RequestParam(in);
        }

        public RequestParam[] newArray(int size) {
        return new RequestParam[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mProcessId);
        dest.writeString(mQrCode);
        dest.writeInt(mProductId);
        dest.writeInt(mLineId);
    }

    @Override
    public String toString(){
        return "processId = " + mProcessId + ", qrCode = " + mQrCode + ", productId = " + mProductId + ", lineId = " + mLineId;
    }


}