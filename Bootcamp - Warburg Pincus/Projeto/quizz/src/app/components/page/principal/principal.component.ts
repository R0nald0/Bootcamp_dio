import { Component, OnInit } from '@angular/core';
import { UserRankingModel } from 'src/app/model/UserRakingModel';
import { PerguntaService } from 'src/app/shared/services/pergunta.service';
import {ɵSafeResourceUrl} from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {
   list : UserRankingModel[]=[]

   constructor(private firebaseDbService : PerguntaService , private sanitization:DomSanitizer){}


  ngOnInit(): void {
      this.getRanking()
  }

    getRanking() {
       this.firebaseDbService.recuperarRankingUser().subscribe({
        next: data => data.forEach((it)=>{
             let item = it.payload.doc.data() as UserRankingModel
             let ite  = this.sanitization.bypassSecurityTrustUrl(`${item.urlImagemPerfil}`)
             item.urlImagemPerfil = `${it}`;
             this.list.push(item);
        }),
        error: erro => console.log("erro ao buscar dados",erro)
       });
    }
  }
