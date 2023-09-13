package com.me.group.credit.sytem.exeception

data class ExeceptionsDetails(
        val title :String,
        val timeStamp :String,
        val status : Int,
        val exception:String,
        val detail:MutableMap<String,String?>
)
