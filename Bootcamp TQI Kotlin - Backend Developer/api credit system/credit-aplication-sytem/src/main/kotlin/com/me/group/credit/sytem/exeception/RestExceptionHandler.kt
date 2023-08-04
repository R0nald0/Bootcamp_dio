package com.me.group.credit.sytem.exeception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestControllerAdvice
class RestExceptionHandler {
    private val dateFormat = DateTimeFormatter.ofPattern("dd-MM-YYYY, HH:mm:ss")
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handlerValidException(ex:MethodArgumentNotValidException):ResponseEntity<ExeceptionsDetails>{
      val erro : MutableMap<String,String?> = HashMap()
      ex.bindingResult.allErrors.stream().forEach {objectError ->
             val fieldName = (objectError as FieldError).field
            val messageError = objectError.defaultMessage
              erro[fieldName] = messageError
      }
      return ResponseEntity(
              ExeceptionsDetails(
                      title = "Bad request",
                      timeStamp = LocalDateTime.now().format(dateFormat),
                      status =  ex.statusCode.value(), //HttpStatus.BAD_REQUEST.value(),
                      exception = ex.message,
                      detail = erro
              ),
              HttpStatus.BAD_REQUEST
      )

  }

    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(ex:DataAccessException):ResponseEntity<ExeceptionsDetails>{
        return ResponseEntity(
                ExeceptionsDetails(
                        title = "Conflict",
                        timeStamp = LocalDateTime.now().format(dateFormat),
                        status =  HttpStatus.CONFLICT.value(),
                        exception = ex.javaClass.name,
                        detail = mutableMapOf(ex.cause.toString() to ex.localizedMessage)
                ),
                HttpStatus.CONFLICT
        )

    }

    @ExceptionHandler(BusinessException::class)
    fun handlerValidException(ex:BusinessException):ResponseEntity<ExeceptionsDetails>{
        return ResponseEntity(
                ExeceptionsDetails(
                        title = "Bad Request",
                        timeStamp = LocalDateTime.now().format(dateFormat),
                        status =  HttpStatus.BAD_REQUEST.value(),
                        exception = ex.javaClass.name,
                        detail = mutableMapOf(ex.cause.toString() to ex.localizedMessage)
                ),
                HttpStatus.BAD_REQUEST
        )

    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handlerValidException(ex:IllegalArgumentException):ResponseEntity<ExeceptionsDetails>{
        return ResponseEntity(
                ExeceptionsDetails(
                        title = "Bad Request",
                        timeStamp = LocalDateTime.now().format(dateFormat),
                        status =  HttpStatus.BAD_REQUEST.value(),
                        exception = ex.javaClass.name,
                        detail = mutableMapOf(ex.cause.toString() to ex.localizedMessage)
                ),
                HttpStatus.BAD_REQUEST
        )

    }

}