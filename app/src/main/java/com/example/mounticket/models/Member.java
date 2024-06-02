package com.example.mounticket.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable {
    private String name;
    private int age;
    private String phone;
    private int mountainId;

    public Member(String name, int age, String phone, int mountainId) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.mountainId = mountainId;
    }

    protected Member(Parcel in) {
        name = in.readString();
        age = in.readInt();
        phone = in.readString();
        mountainId = in.readInt();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(phone);
        dest.writeInt(mountainId);
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public int getMountainId() {
        return mountainId;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMountainId(int mountainId) {
        this.mountainId = mountainId;
    }
}