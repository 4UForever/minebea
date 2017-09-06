package com.devsenses.minebea.model.errormodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MetaDatum implements Parcelable{

    private static final String FIELD_ERRORS = "errors";
    private static final String FIELD_SUCCESS = "success";
    private static final String FIELD_REQUEST_PARAMS = "request_params";
    private static final String FIELD_NEXT_PAGE = "next_page";
    private static final String FIELD_PREVIOUS_PAGE = "previous_page";


    @SerializedName(FIELD_ERRORS)
    private List<String> mErrors;
    @SerializedName(FIELD_SUCCESS)
    private String mSuccess;
    @SerializedName(FIELD_REQUEST_PARAMS)
    private RequestParam mRequestParam;
    @SerializedName(FIELD_NEXT_PAGE)
    private String mNextPage;
    @SerializedName(FIELD_PREVIOUS_PAGE)
    private String mPreviousPage;


    public MetaDatum(){

    }

    public void setErrors(List<String> errors) {
        mErrors = errors;
    }

    public List<String> getErrors() {
        return mErrors;
    }

    public void setSuccess(String success) {
        mSuccess = success;
    }

    public String getSuccess() {
        return mSuccess;
    }

    public void setRequestParam(RequestParam requestParam) {
        mRequestParam = requestParam;
    }

    public RequestParam getRequestParam() {
        return mRequestParam;
    }

    public void setNextPage(String nextPage) {
        mNextPage = nextPage;
    }

    public String getNextPage() {
        return mNextPage;
    }

    public void setPreviousPage(String previousPage) {
        mPreviousPage = previousPage;
    }

    public String getPreviousPage() {
        return mPreviousPage;
    }

    public MetaDatum(Parcel in) {
        in.readArrayList(String.class.getClassLoader());
        mSuccess = in.readString();
        mRequestParam = in.readParcelable(RequestParam.class.getClassLoader());
        mNextPage = in.readString();
        mPreviousPage = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MetaDatum> CREATOR = new Creator<MetaDatum>() {
        public MetaDatum createFromParcel(Parcel in) {
            return new MetaDatum(in);
        }

        public MetaDatum[] newArray(int size) {
        return new MetaDatum[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mErrors);
        dest.writeString(mSuccess);
        dest.writeParcelable(mRequestParam, flags);
        dest.writeString(mNextPage);
        dest.writeString(mPreviousPage);
    }

    @Override
    public String toString(){
        return "errors = " + mErrors + ", success = " + mSuccess + ", requestParam = " + mRequestParam + ", nextPage = " + mNextPage + ", previousPage = " + mPreviousPage;
    }


}