package com.luisfagundes.data.model

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("cast") val results: List<ActorResponse>
)