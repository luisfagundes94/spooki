package com.luisfagundes.data.model

import com.google.gson.annotations.SerializedName

data class ProductionCompanyResponse(
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String
)
