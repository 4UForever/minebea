package com.devsenses.minebea.model.start_stopmodel;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class Line implements Parcelable{

    private static final String FIELD_ID = "id";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_CREATED_AT = "created_at";
    private static final String FIELD_UPDATED_AT = "updated_at";


    @SerializedName(FIELD_ID)
    private long mId;
    @SerializedName(FIELD_TITLE)
    private String mTitle;
    @SerializedName(FIELD_CREATED_AT)
    private String mCreatedAt;
    @SerializedName(FIELD_UPDATED_AT)
    private String mUpdatedAt;


    public Line(){

    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Line){
            return ((Line) obj).getId() == mId;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return ((Long)mId).hashCode();
    }

    public Line(Parcel in) {
        mId = in.readLong();
        mTitle = in.readString();
        mCreatedAt = in.readString();
        mUpdatedAt = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Line> CREATOR = new Creator<Line>() {
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        public Line[] newArray(int size) {
        return new Line[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mTitle);
        dest.writeString(mCreatedAt);
        dest.writeString(mUpdatedAt);
    }

    @Override
    public String toString(){
        return "id = " + mId + ", title = " + mTitle + ", createdAt = " + mCreatedAt + ", updatedAt = " + mUpdatedAt;
    }


}