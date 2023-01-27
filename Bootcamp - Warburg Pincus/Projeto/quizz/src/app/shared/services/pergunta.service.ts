import { ArrayType } from '@angular/compiler';
import { Injectable } from '@angular/core';
import {AngularFirestore} from '@angular/fire/compat/firestore'
import { Pergunta } from 'src/app/model/Pergunta';
import  quiz_questions from  'src/app/data/quizz_questions.json';

@Injectable({
  providedIn: 'root'
})
export class PerguntaService {
  

  constructor(private fireDd : AngularFirestore) { }
    
   recuperarPergunta(){
    return this.fireDd.collection('Perguntas').snapshotChanges()
   }
   recuperarPerguntasQuiz(){
       return quiz_questions;
   }}

