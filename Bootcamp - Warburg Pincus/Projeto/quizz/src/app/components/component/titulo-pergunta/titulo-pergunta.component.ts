import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-titulo-pergunta',
  templateUrl: './titulo-pergunta.component.html',
  styleUrls: ['./titulo-pergunta.component.css']
})
export class TituloPerguntaComponent {
  @Input()
    titulo = "";

}
