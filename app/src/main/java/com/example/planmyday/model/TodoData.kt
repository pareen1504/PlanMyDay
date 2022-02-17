package com.example.planmyday.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class TodoData(
    @SerializedName("userId") var userId: Int? = 0,
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("title") var title: String? = "",
    @SerializedName("completed") var completed: Boolean? = false
) : Parcelable