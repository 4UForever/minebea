package com.devsenses.minebea.model.loginmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Datum implements Parcelable{

    private static final String FIELD_LAST_LOGIN = "last_login";
    private static final String FIELD_ID = "id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_CREATED_AT = "created_at";
    private static final String FIELD_GROUPS = "groups";
    private static final String FIELD_MODELS = "models";
    private static final String FIELD_LAST_LOGOUT = "last_logout";
    private static final String FIELD_LAST_NAME = "last_name";
    private static final String FIELD_UPDATED_AT = "updated_at";
    private static final String FIELD_EMAIL = "email";


    @SerializedName(FIELD_LAST_LOGIN)
    private String mLastLogin;
    @SerializedName(FIELD_ID)
    private long mId;
    @SerializedName(FIELD_FIRST_NAME)
    private String mFirstName;
    @SerializedName(FIELD_CREATED_AT)
    private String mCreatedAt;
    @SerializedName(FIELD_GROUPS)
    private List<Group> mGroups;
    @SerializedName(FIELD_MODELS)
    private List<Model> mModels;
    @SerializedName(FIELD_LAST_LOGOUT)
    private String mLastLogout;
    @SerializedName(FIELD_LAST_NAME)
    private String mLastName;
    @SerializedName(FIELD_UPDATED_AT)
    private String mUpdatedAt;
    @SerializedName(FIELD_EMAIL)
    private String mEmail;


    public Datum(){

    }

    public void setLastLogin(String lastLogin) {
        mLastLogin = lastLogin;
    }

    public String getLastLogin() {
        return mLastLogin;
    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setGroups(List<Group> groups) {
        mGroups = groups;
    }

    public List<Group> getGroups() {
        return mGroups;
    }

    public void setModels(List<Model> models) {
        mModels = models;
    }

    public List<Model> getModels() {
        return mModels;
    }

    public void setLastLogout(String lastLogout) {
        mLastLogout = lastLogout;
    }

    public String getLastLogout() {
        return mLastLogout;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getEmail() {
        return mEmail;
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
        mLastLogin = in.readString();
        mId = in.readLong();
        mFirstName = in.readString();
        mCreatedAt = in.readString();
        mGroups = new ArrayList<Group>();
        in.readTypedList(mGroups, Group.CREATOR);
        mModels = new ArrayList<Model>();
        in.readTypedList(mModels, Model.CREATOR);
        mLastLogout = in.readString();
        mLastName = in.readString();
        mUpdatedAt = in.readString();
        mEmail = in.readString();
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
        dest.writeString(mLastLogin);
        dest.writeLong(mId);
        dest.writeString(mFirstName);
        dest.writeString(mCreatedAt);
        dest.writeTypedList(mGroups);
        dest.writeTypedList(mModels);
        dest.writeString(mLastLogout);
        dest.writeString(mLastName);
        dest.writeString(mUpdatedAt);
        dest.writeString(mEmail);
    }

    @Override
    public String toString(){
        return "lastLogin = " + mLastLogin + ", id = " + mId + ", firstName = " + mFirstName + ", createdAt = " + mCreatedAt + ", groups = " + mGroups + ", models = " + mModels + ", lastLogout = " + mLastLogout + ", lastName = " + mLastName + ", updatedAt = " + mUpdatedAt + ", email = " + mEmail;
    }


}