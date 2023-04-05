
import {AngularFirestore} from '@angular/fire/compat/firestore'
import { AngularFireStorage, AngularFireStorageModule, AngularFireStorageReference } from '@angular/fire/compat/storage';

import  quiz_questions from  'src/app/data/quizz_questions.json';
import { finalize, Observable } from 'rxjs';
import { FileUpload } from 'src/app/model/FileUpload';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class PerguntaService {
  
  baseUrl= "assets/users/"
  pathFireBase= "/"
  profileUrl = "content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F33/ORIGINAL/NONE/image%2Fjpeg/1876078791"

  
  constructor(private fireDb : AngularFirestore, private storegeDb : AngularFireStorage) { }
    
  getDownloadImg(fileUpload : FileUpload){
     const filePath = `${this.baseUrl}/${fileUpload.name}`;
     const storageRef = this.storegeDb.ref(filePath);
     const uploadTask = this.storegeDb.upload(filePath,fileUpload.file)
     
     uploadTask.snapshotChanges().pipe(
      finalize(() =>{
        storageRef.getDownloadURL().subscribe({
          next: url => {
            fileUpload.url = url
            fileUpload.name = fileUpload.file.name
          }       
        })
      })
     );
    }

     getUrlImageFromStorage(id:String){
       try {
         const ref = this.storegeDb.ref('perfil')
         .child(`${id}`)
         .child("fotoPerfil01")
         .getDownloadURL() as Observable<File>

        ref.subscribe({
             next: file => {console.log(file.size)}
        })
          
       } catch (error) {
            throw new Error("erro ao carregar image" + error);    
       }
    }

    
    getUrlImageFromRankingList(){
      const ref = this.storegeDb.refFromURL(this.profileUrl).getDownloadURL().subscribe(
        url => {
          console.log(url) 
        }
      )
      //console.log(ref) 
      //return ref
    
 }




    recuperarPergunta() {
    return this.fireDb.collection('Perguntas').snapshotChanges()
   }
 
   recuperarRankingUser(){
     try {
      return this.fireDb.collection('ranking').snapshotChanges();
    } catch (e) {
        throw new Error("erro ao buscar lista Raking ");    
    }
   }
   
   recuperarPerguntasQuiz(){
       return quiz_questions;
   }}

