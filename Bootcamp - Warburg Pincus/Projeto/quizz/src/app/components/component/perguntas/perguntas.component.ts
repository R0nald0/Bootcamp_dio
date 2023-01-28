import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Opcao } from 'src/app/model/opcao';

@Component({
  selector: 'app-perguntas',
  templateUrl: './perguntas.component.html',
  styleUrls: ['./perguntas.component.css']
})
export class PerguntasComponent {
  
  @Input()
   opcoes :Opcao[] = []

  @Output()
     eventoEscolhaOpcao= new EventEmitter<number>()

   opcoesEscolhida(idOpcao : number) {
      this.eventoEscolhaOpcao.emit(idOpcao)
    
    }
}
