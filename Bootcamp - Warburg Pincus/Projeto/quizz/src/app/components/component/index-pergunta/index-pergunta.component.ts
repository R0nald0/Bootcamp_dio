import { Component, Input, OnInit } from '@angular/core';
import { AngularFirestore } from '@angular/fire/compat/firestore';

@Component({
  selector: 'app-index-pergunta',
  templateUrl: './index-pergunta.component.html',
  styleUrls: ['./index-pergunta.component.css']
})
export class IndexPerguntaComponent implements OnInit {
 @Input() 
 indexPerguntaAtual = 0
 @Input() 
 totalPerguntas =0
 @Input()
    index = 0;

    constructor(){ 
    }
  ngOnInit(): void {
  
  }
  
}
