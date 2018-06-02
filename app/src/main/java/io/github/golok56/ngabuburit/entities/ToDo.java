package io.github.golok56.ngabuburit.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity
public class ToDo implements Parcelable {

    public static Parcelable.Creator CREATOR = new Parcelable.Creator<ToDo>() {
        @Override
        public ToDo createFromParcel(Parcel source) {
            return new ToDo(source);
        }

        @Override
        public ToDo[] newArray(int size) {
            return new ToDo[size];
        }
    };

    @PrimaryKey(autoGenerate = true) private int id;
    private String nama;
    private String deskripsi;
    private int status;

    public ToDo(int id, String nama, String deskripsi, int status) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    @Ignore
    public ToDo(String nama, String deskripsi, int status) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public ToDo(Parcel parcel) {
        id = parcel.readInt();
        nama = parcel.readString();
        deskripsi = parcel.readString();
        status = parcel.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(deskripsi);
        dest.writeInt(status);
    }
}
