package com.devsenses.minebea.model.start_stopmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Datum implements Parcelable{

    private static final String FIELD_TYPE = "type";
    private static final String FIELD_ID = "id";
    private static final String FIELD_PROCESS = "process";
    private static final String FIELD_CREATED_AT = "created_at";
    private static final String FIELD_USER = "user";
    private static final String FIELD_LINE = "line";
    private static final String FIELD_UPDATED_AT = "updated_at";


    @SerializedName(FIELD_TYPE)
    private String mType;
    @SerializedName(FIELD_ID)
    private long mId;
    @SerializedName(FIELD_PROCESS)
    private Process mProcess;
    @SerializedName(FIELD_CREATED_AT)
    private String mCreatedAt;
    @SerializedName(FIELD_USER)
    private User mUser;
    @SerializedName(FIELD_LINE)
    private Line mLine;
    @SerializedName(FIELD_UPDATED_AT)
    private String mUpdatedAt;


    public Datum(){

    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setProcess(Process process) {
        mProcess = process;
    }

    public Process getProcess() {
        return mProcess;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public User getUser() {
        return mUser;
    }

    public void setLine(Line line) {
        mLine = line;
    }

    public Line getLine() {
        return mLine;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
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
        mType = in.readString();
        mId = in.readLong();
        mProcess = in.readParcelable(Process.class.getClassLoader());
        mCreatedAt = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
        mLine = in.readParcelable(Line.class.getClassLoader());
        mUpdatedAt = in.readString();
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
        dest.writeString(mType);
        dest.writeLong(mId);
        dest.writeParcelable(mProcess, flags);
        dest.writeString(mCreatedAt);
        dest.writeParcelable(mUser, flags);
        dest.writeParcelable(mLine, flags);
        dest.writeString(mUpdatedAt);
    }

    @Override
    public String toString(){
        return "type = " + mType + ", id = " + mId + ", process = " + mProcess + ", createdAt = " + mCreatedAt + ", user = " + mUser + ", line = " + mLine + ", updatedAt = " + mUpdatedAt;
    }


}