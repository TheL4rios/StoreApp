package com.TheLarios.store.Pojos

import com.google.gson.annotations.SerializedName

data class ResponseProducts(

	@field:SerializedName("payLoad")
	val payLoad: List<PayLoadItem?>? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)