package com.devsenses.minebea.model.loginmodel;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class LoginModel implements Parcelable{

    private static final String FIELD_META_DATA = "meta_data";
    private static final String FIELD_DATA = "data";


    @SerializedName(FIELD_META_DATA)
    private MetaDatum mMetaDatum;
    @SerializedName(FIELD_DATA)
    private Datum mDatum;


    public LoginModel(){

    }

    public void setMetaDatum(MetaDatum metaDatum) {
        mMetaDatum = metaDatum;
    }

    public MetaDatum getMetaDatum() {
        return mMetaDatum;
    }

    public void setDatum(Datum datum) {
        mDatum = datum;
    }

    public Datum getDatum() {
        return mDatum;
    }

    public LoginModel(Parcel in) {
        mMetaDatum = in.readParcelable(MetaDatum.class.getClassLoader());
        mDatum = in.readParcelable(Datum.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        public LoginModel[] newArray(int size) {
        return new LoginModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mMetaDatum, flags);
        dest.writeParcelable(mDatum, flags);
    }

    @Override
    public String toString(){
        return "metaDatum = " + mMetaDatum + ", datum = " + mDatum;
    }


}