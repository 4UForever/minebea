package com.devsenses.minebea.model.start_stopmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class StartStopModel implements Parcelable{

    private static final String FIELD_META_DATA = "meta_data";
    private static final String FIELD_DATA = "data";


    @SerializedName(FIELD_META_DATA)
    private MetaDatum mMetaDatum;
    @SerializedName(FIELD_DATA)
    private Datum mDatum;


    public StartStopModel(){

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

    public StartStopModel(Parcel in) {
        mMetaDatum = in.readParcelable(MetaDatum.class.getClassLoader());
        mDatum = in.readParcelable(Datum.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StartStopModel> CREATOR = new Creator<StartStopModel>() {
        public StartStopModel createFromParcel(Parcel in) {
            return new StartStopModel(in);
        }

        public StartStopModel[] newArray(int size) {
        return new StartStopModel[size];
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