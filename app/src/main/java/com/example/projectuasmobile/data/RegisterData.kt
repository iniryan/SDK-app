package com.example.projectuasmobile.data

class RegisterData (val fullname: String, val email:String, val username:String, val password: String?, val booth: Booth?)

class Booth(val boothName: String, val boothDescription: String, val owner: RegisterData)

