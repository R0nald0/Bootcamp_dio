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
  indexPegunta =0;
  acertos =0;
  listaPegunta : Pergunta[]= []
  
  constructor(private servicePergunta: PerguntaService){}

   ngOnInit(): void {
 
    this.recuperarPerguntasFirebase()  
    //this.recuperarPeguntas()
    
    }
    
   recuperarPeguntas(){
     const lista = this.servicePergunta.recuperarPerguntasQuiz()
            this.totalPergunta = lista.questions.length
            this.pergunta.Titulo =  lista.questions[this.indexPegunta].question
           // this.opcoes = lista.questions[this.indexPegunta].options
            console.log("indexPEr=" + this.indexPegunta +  " totalPergunta" + this.totalPergunta)   
              
             this.listaPegunta.forEach((p) => {
                console.log(p.Titulo)
             })
    
          }    

    listarPeguntar(){
      this.totalPergunta = this.listaPegunta.length
      this.pergunta.Titulo = this.listaPegunta[this.indexPegunta].Titulo
      this.pergunta.Resposta = this.listaPegunta[this.indexPegunta].Resposta
    } 
    
    getIndexPergunta(idRepostaEscolhida:number){     
        if(this.indexPegunta +1 <= this.totalPergunta){
            this.verificarRespostaCerta(
                     idRepostaEscolhida,
                     this.listaPegunta[this.indexPegunta]
                )   
                this.atualizarPeguntas()                    
        } else{     
           alert("Fim das perguntsas")
        }
    }

    verificarRespostaCerta(idRepostaEscolhida : number,pergunta : Pergunta){
           if(idRepostaEscolhida === Number.parseInt( pergunta.RespostaCerta) ){
              this.acertos +=1
              console.log("acertos= "+ this.acertos)
           }else{
            console.log("erro= ")
           }   
           
    }
    
    atualizarPeguntas(){
        this.indexPegunta +=1
          this.listarPeguntar()
    }

    
recuperarPerguntasFirebase(){
 this.servicePergunta.recuperarPergunta().subscribe({
   next:data => data.map((d) => {
      let item = d.payload.doc.data() as Pergunta
      this.listaPegunta.push(item)
      this.listarPeguntar()  
  }),error:eer => console.log(eer.message)
 })    
 
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