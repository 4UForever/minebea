package com.devsenses.minebea.model.documentmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class DocumenModel implements Parcelable{

    private static final String FIELD_META_DATA = "meta_data";
    private static final String FIELD_DATA = "data";


    @SerializedName(FIELD_META_DATA)
    private MetaDatum mMetaDatum;
    @SerializedName(FIELD_DATA)
    private List<Datum> mData;


    public DocumenModel(){

    }

    public void setMetaDatum(MetaDatum metaDatum) {
        mMetaDatum = metaDatum;
    }

    public MetaDatum getMetaDatum() {
        return mMetaDatum;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public List<Datum> getData() {
        return mData;
    }

    public DocumenModel(Parcel in) {
        mMetaDatum = in.readParcelable(MetaDatum.class.getClassLoader());
        mData = new ArrayList<Datum>();
        in.readTypedList(mData, Datum.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DocumenModel> CREATOR = new Creator<DocumenModel>() {
        public DocumenModel createFromParcel(Parcel in) {
            return new DocumenModel(in);
        }

        public DocumenModel[] newArray(int size) {
        return new DocumenModel[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mMetaDatum, flags);
        dest.writeTypedList(mData);
    }

    @Override
    public String toString(){
        return "metaDatum = " + mMetaDatum + ", data = " + mData;
    }


}