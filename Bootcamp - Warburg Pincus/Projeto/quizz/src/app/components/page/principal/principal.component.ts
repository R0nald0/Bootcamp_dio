import { Component, OnInit } from '@angular/core';
import { UserRankingModel } from 'src/app/model/UserRakingModel';
import { PerguntaService } from 'src/app/shared/services/pergunta.service';
import {NgForm} from '@angular/forms'
import { DomSanitizer } from '@angular/platform-browser';


@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {
   list : UserRankingModel[]=[]
    
   email =""
   password =""

   isLogginVerified = false

   constructor(private firebaseDbService : PerguntaService , private sanitization:DomSanitizer ){}

   
   class_error = ['input_error_container > input']
  ngOnInit(): void {
      this.getRanking()
  }

   getRanking() {
       this.firebaseDbService.recuperarRankingUser().subscribe({
        next: data => data.forEach((it)=>{
             let item = it.payload.doc.data() as UserRankingModel
     
           // this.firebaseDbService.getUrlImageFromStorage(`${item.id}`)
             /*  this.firebaseDbService.getUrlImageFromStorage(`${item.id}`).subscribe({
                next : (img: string | String) =>   
                     {  this.sanitization.bypassSecurityTrustResourceUrl(`${img}`)
                      item.urlImagemPerfil=(img)
                    }
              }) */
                       
             this.list.push(item);
        }),
        error: erro => console.log("erro ao buscar dados",erro)
       });
    }

    validateData(dados : NgForm){    
    
      if(dados.valid == true){
           
      }
    }

    onClick(){
   
      this.isLogginVerified = !this.isLogginVerified;
      console.log(!this.isLogginVerified)
    }
  }
