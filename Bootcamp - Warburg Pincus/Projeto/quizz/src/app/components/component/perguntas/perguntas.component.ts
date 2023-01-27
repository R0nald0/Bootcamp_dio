import { Component, Input, Output } from '@angular/core';
import { Opcao } from 'src/app/model/opcao';

@Component({
  selector: 'app-perguntas',
  templateUrl: './perguntas.component.html',
  styleUrls: ['./perguntas.component.css']
})
export class PerguntasComponent {
  
  @Input()
   opcoes :Opcao[] = []
  


   opcoesEscolhida(idOpcao : number) {
      console.log(idOpcao);
    }
}
