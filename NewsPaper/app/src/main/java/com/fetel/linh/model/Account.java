package com.fetel.linh.model;

import android.os.Parcel;
import android.os.Parcelable;



public class Account implements Parcelable{
    private String username,password;

    public Account() {
    }

    public Account(Parcel parcel){

    }
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);

    }

    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        @Override
        public Account createFromParcel(Parcel parcel) {
            return new Account(parcel);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
