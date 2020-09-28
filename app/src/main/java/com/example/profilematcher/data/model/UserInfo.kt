package com.example.profilematcher.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.profilematcher.util.Constants
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Result(
    @SerializedName("results")
    val userList : List<UserInfo>
) : Parcelable

@Parcelize
data class UserInfo(

    @ColumnInfo(name = "gender")
    @SerializedName("gender")
    val gender : String,
    @Ignore
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name : Name,
    @Ignore
    @ColumnInfo(name = "dob")
    @SerializedName("dob")
    val dob : DOB,
    @Ignore
    @ColumnInfo(name = "picture")
    @SerializedName("picture")
    val picture: ProfileImage,
    @Ignore
    @ColumnInfo(name = "location")
    @SerializedName("location")
    val location  : Location,
    @Ignore
    @PrimaryKey
    @ColumnInfo(name ="login")
    @SerializedName("login")
    val login: Login

) : Parcelable

@Parcelize
data class Name (
    @SerializedName("title")
    val title: String,

    @SerializedName("first")
    val firstName : String,

    @SerializedName("last")
    val lastName : String

):Parcelable
@Parcelize
data class DOB (
    @SerializedName("age")
    val age : String
) : Parcelable


@Parcelize
data class ProfileImage (
    @SerializedName("large")
    val url : String
) : Parcelable

@Parcelize
data class Location(
    @SerializedName("state")
    val state : String,
    @SerializedName("country")
    val country : String
) : Parcelable

@Parcelize
data class Login(
    @SerializedName("uuid")
    val uuid : String
) : Parcelable


@Entity(tableName = "UserInfo")
data class LocalUserInfo(

    @ColumnInfo(name = "status")
    var status : Int,
    @ColumnInfo(name = "gender")
    val gender : String,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name : String,
    @ColumnInfo(name = "age")
    val age : String,
    @ColumnInfo(name = "avtarUrl")
    val avtarUrl: String,
    @ColumnInfo(name = "location")
    val location  : String,
    @PrimaryKey
    @ColumnInfo(name ="uuid")
    val uuid: String
){
    constructor() : this(Constants.PROFILE_NO_ACTION,"","","","","","")
}


data class UpdateItemInfo(

    var postion : Int,
    var localUserInfo: LocalUserInfo
) {
}