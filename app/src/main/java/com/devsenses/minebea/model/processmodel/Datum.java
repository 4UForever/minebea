package com.devsenses.minebea.model.processmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Datum implements Parcelable{

    private static final String FIELD_ID = "id";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_CREATED_AT = "created_at";
    private static final String FIELD_AVAILABLE = "available";
    private static final String FIELD_UPDATED_AT = "updated_at";
    private static final String FIELD_AVAILABLE_MESSAGE = "available_message";


    @SerializedName(FIELD_ID)
    private long mId;
    @SerializedName(FIELD_STATUS)
    private int mStatus;
    @SerializedName(FIELD_TITLE)
    private String mTitle;
    @SerializedName(FIELD_CREATED_AT)
    private String mCreatedAt;
    @SerializedName(FIELD_AVAILABLE)
    private boolean mAvailable;
    @SerializedName(FIELD_UPDATED_AT)
    private String mUpdatedAt;
    @SerializedName(FIELD_AVAILABLE_MESSAGE)
    private String mVailable_message;


    public Datum(){

    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getStatus() {
        return mStatus;
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

    public void setAvailable(boolean available) {
        mAvailable = available;
    }
    public boolean getAvailable() {
       return mAvailable ;
    }

    public boolean isAvailable() {
        return mAvailable;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public String getmVailable_message() {
        return mVailable_message;
    }

    public void setmVailable_message(String mVailable_message) {
        this.mVailable_message = mVailable_message;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Datum){
            return ((Datum) obj).getId() == mId;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return ((Long)mId).hashCode();
    }

    public Datum(Parcel in) {
        mId = in.readLong();
        mStatus = in.readInt();
        mTitle = in.readString();
        mCreatedAt = in.readString();
        mAvailable = in.readInt() == 1 ? true: false;
        mUpdatedAt = in.readString();
        mVailable_message = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
        return new Datum[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeInt(mStatus);
        dest.writeString(mTitle);
        dest.writeString(mCreatedAt);
        dest.writeInt(mAvailable ? 1 : 0);
        dest.writeString(mUpdatedAt);
        dest.writeString(mVailable_message);
    }

    @Override
    public String toString(){
        return "id = " + mId + ", status = " + mStatus + ", title = " + mTitle + ", createdAt = " + mCreatedAt + ", available = " + mAvailable + ", updatedAt = " + mUpdatedAt;
    }


}