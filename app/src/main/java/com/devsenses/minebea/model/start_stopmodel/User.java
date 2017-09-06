package com.devsenses.minebea.model.start_stopmodel;

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import android.os.Parcel;


public class User implements Parcelable{

    private static final String FIELD_LAST_LOGIN = "last_login";
    private static final String FIELD_ID = "id";
    private static final String FIELD_FIRST_NAME = "first_name";
    private static final String FIELD_CREATED_AT = "created_at";
    private static final String FIELD_PERSIST_CODE = "persist_code";
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
    @SerializedName(FIELD_PERSIST_CODE)
    private String mPersistCode;
    @SerializedName(FIELD_LAST_LOGOUT)
    private String mLastLogout;
    @SerializedName(FIELD_LAST_NAME)
    private String mLastName;
    @SerializedName(FIELD_UPDATED_AT)
    private String mUpdatedAt;
    @SerializedName(FIELD_EMAIL)
    private String mEmail;


    public User(){

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

    public void setPersistCode(String persistCode) {
        mPersistCode = persistCode;
    }

    public String getPersistCode() {
        return mPersistCode;
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
        if(obj instanceof User){
            return ((User) obj).getId() == mId;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return ((Long)mId).hashCode();
    }

    public User(Parcel in) {
        mLastLogin = in.readString();
        mId = in.readLong();
        mFirstName = in.readString();
        mCreatedAt = in.readString();
        mPersistCode = in.readString();
        mLastLogout = in.readString();
        mLastName = in.readString();
        mUpdatedAt = in.readString();
        mEmail = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
        return new User[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLastLogin);
        dest.writeLong(mId);
        dest.writeString(mFirstName);
        dest.writeString(mCreatedAt);
        dest.writeString(mPersistCode);
        dest.writeString(mLastLogout);
        dest.writeString(mLastName);
        dest.writeString(mUpdatedAt);
        dest.writeString(mEmail);
    }

    @Override
    public String toString(){
        return "lastLogin = " + mLastLogin + ", id = " + mId + ", firstName = " + mFirstName + ", createdAt = " + mCreatedAt + ", persistCode = " + mPersistCode + ", lastLogout = " + mLastLogout + ", lastName = " + mLastName + ", updatedAt = " + mUpdatedAt + ", email = " + mEmail;
    }


}