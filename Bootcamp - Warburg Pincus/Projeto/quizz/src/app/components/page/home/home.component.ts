import { Component, OnInit} from '@angular/core';
import { Opcao } from 'src/app/model/opcao';
import { Pergunta } from 'src/app/model/Pergunta';
import { PerguntaService } from 'src/app/shared/services/pergunta.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  pergunta = new Pergunta() 
  totalPergunta = 0;
  opcoes: Opcao[] =[]
  indexPegunta = 0
  
  constructor(private servicePergunta: PerguntaService){}

   ngOnInit(): void {
    this.pergunta.titulo = "primeira pergunta"
      this.recuperarPeguntas()
    }
    
   recuperarPeguntas(){
     const lista = this.servicePergunta.recuperarPerguntasQuiz()
            this.totalPergunta = lista.questions.length
            this.pergunta.titulo =  lista.questions[this.indexPegunta].question
            this.opcoes = lista.questions[this.indexPegunta].options
    }       
}

// .subscribe(data =>{
//   this.listaPergunta =  data.map((e:any) =>{
//          this.pergunta.titulo =  e.payload.doc.data()['Titulo']
//          this.pergunta.RespostaCerta =  e.payload.doc.data()['RespostaCerta']
//          this.pergunta.list =  e.payload.doc.data()['Resposta']
          
//            console.log(this.pergunta)
           
//            return this.pergunta
//        },
//       )
//   }) 