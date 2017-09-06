package com.devsenses.minebea.model.errormodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ErrorModel implements Parcelable{

    private static final String FIELD_META_DATA = "meta_data";
    private static final String FIELD_DATA = "data";


    @SerializedName(FIELD_META_DATA)
    private MetaDatum mMetaDatum;
    @SerializedName(FIELD_DATA)
    private String mDatum;


    public ErrorModel(){

    }

    public void setMetaDatum(MetaDatum metaDatum) {
        mMetaDatum = metaDatum;
    }

    public MetaDatum getMetaDatum() {
        return mMetaDatum;
    }

    public void setDatum(String datum) {
        mDatum = datum;
    }

    public String getDatum() {
        return mDatum;
    }

    public ErrorModel(Parcel in) {
        mMetaDatum = in.readParcelable(MetaDatum.class.getClassLoader());
        mDatum = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ErrorModel> CREATOR = new Creator<ErrorModel>() {
        public ErrorModel createFromParcel(Parcel in) {
            return new ErrorModel(in);
        }

        public ErrorModel[] newArray(int size) {
        return new ErrorModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mMetaDatum, flags);
        dest.writeString(mDatum);
    }

    @Override
    public String toString(){
        return "metaDatum = " + mMetaDatum + ", datum = " + mDatum;
    }


}