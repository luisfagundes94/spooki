package com.luisfagundes.data.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("results") val results: List<TrailerResponse>
)
