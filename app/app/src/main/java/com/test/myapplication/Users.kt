package com.test.myapplication

data class Users(
    var name : String = "name",
    var id : String = "id",
    var pw : String = "pw",
    var mbti:String = "mbti",
    var loc:String = "location",
    var like:String = "interesting",
    var accept:Boolean
){
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name,
            "id" to id,
            "pw" to pw,
            "mbti" to mbti,
            "loc" to loc,
            "like" to like,
            "accept" to accept
        )
    }
}
