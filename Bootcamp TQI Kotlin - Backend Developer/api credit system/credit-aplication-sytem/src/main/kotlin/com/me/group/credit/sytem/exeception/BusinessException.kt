package com.me.group.credit.sytem.exeception

data class BusinessException(override val message: String?):RuntimeException(message ) {

}