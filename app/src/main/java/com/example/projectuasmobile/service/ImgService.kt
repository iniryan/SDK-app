package com.example.projectuasmobile.service

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

class UploadResponse {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
}

class UploadResponseList : ArrayList<UploadResponse>()

internal interface ImgService {
    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part("ref") ref: RequestBody,
        @Part("refId") refId: RequestBody,
        @Part("field") field: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<UploadResponseList>
}

