package com.luisfagundes.data.model

import com.google.gson.annotations.SerializedName

data class ActorResponse(
    @SerializedName("profile_path") val profilePath: String?
)
