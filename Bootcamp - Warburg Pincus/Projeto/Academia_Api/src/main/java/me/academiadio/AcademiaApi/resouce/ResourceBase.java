package me.academiadio.AcademiaApi.resouce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ResourceBase<T> {
    protected ResponseEntity<T> responderItemCriadoComUri(T object){
         return ResponseEntity.status(HttpStatus.CREATED).body(object);
    }
}
