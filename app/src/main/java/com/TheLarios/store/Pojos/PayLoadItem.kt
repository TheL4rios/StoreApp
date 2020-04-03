package com.TheLarios.store.Pojos

import com.google.gson.annotations.SerializedName

data class PayLoadItem(

	@field:SerializedName("longDescription")
	val longDescription: String? = null,

	@field:SerializedName("imgUrl")
	val imgUrl: String? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)