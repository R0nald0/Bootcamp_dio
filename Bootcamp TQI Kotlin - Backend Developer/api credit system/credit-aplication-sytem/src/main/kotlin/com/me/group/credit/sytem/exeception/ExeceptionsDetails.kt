package com.me.group.credit.sytem.exeception

import java.time.LocalDate
import java.time.LocalDateTime

data class ExeceptionsDetails(
        val title :String,
        val timeStamp :String,
        val status : Int,
        val exception:String,
        val  detail:MutableMap<String,String?>
)
