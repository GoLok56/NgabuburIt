package io.github.golok56.ngabuburit.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

@Entity
data class ToDo (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var nama: String,
        var deskripsi: String,
        var status: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nama)
        parcel.writeString(deskripsi)
        parcel.writeInt(status)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ToDo> {
        override fun createFromParcel(parcel: Parcel) = ToDo(parcel)

        override fun newArray(size: Int): Array<ToDo?> = arrayOfNulls(size)
    }
}