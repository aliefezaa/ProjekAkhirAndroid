package com.example.mounticket.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "mountains")
public class Mountain implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String alamat;
    private String height;
    private String image;
    private String harga;


    public Mountain() {
    }

    // Constructor dengan parameter untuk menginisialisasi semua atribut
    public Mountain(int id, String name, String alamat, String height, String image, String harga) {
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.height = height;
        this.image = image;
        this.harga = harga;
    }

    protected Mountain(Parcel in) {
        id = in.readInt();
        name = in.readString();
        alamat = in.readString();
        height = in.readString();
        image = in.readString();
        harga = in.readString();
    }

    public static final Creator<Mountain> CREATOR = new Creator<Mountain>() {
        @Override
        public Mountain createFromParcel(Parcel in) {
            return new Mountain(in);
        }

        @Override
        public Mountain[] newArray(int size) {
            return new Mountain[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(alamat);
        dest.writeString(height);
        dest.writeString(image);
        dest.writeString(harga);
    }
}