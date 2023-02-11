import { ArrayType } from '@angular/compiler';
import { ErrorHandler, Injectable } from '@angular/core';
import {AngularFirestore} from '@angular/fire/compat/firestore'
import { Pergunta } from 'src/app/model/Pergunta';
import  quiz_questions from  'src/app/data/quizz_questions.json';

@Injectable({
  providedIn: 'root'
})
export class PerguntaService {


  constructor(private fireDb : AngularFirestore) { }
    
    recuperarPergunta() {
    return this.fireDb.collection('Perguntas').snapshotChanges()
   }
 
   recuperarRankingUser(){
     try {
      return this.fireDb.collection('ranking').snapshotChanges()
    } catch (e) {
        throw new Error("erro ao buscar lista Raking ");    
    }
   }

   recuperarPerguntasQuiz(){
       return quiz_questions;
   }}

