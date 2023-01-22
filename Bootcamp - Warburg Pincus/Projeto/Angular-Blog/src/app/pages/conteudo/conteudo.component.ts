import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { dataFake } from '../../data/dataFake';

@Component({
  selector: 'app-conteudo',
  templateUrl: './conteudo.component.html',
  styleUrls: ['./conteudo.component.css']
})
export class ConteudoComponent {
  artigoImage = ""
  artigoTitulo = ""
  artigoDescrcao = " "

  private id: string | null = "0"

  constructor(private router: ActivatedRoute) {
    router.paramMap.subscribe(paramMap => {
      this.id = paramMap.get("id")
    });

  }

  ngOnInit() {
    this.adicionarConteudo(this.id);
  }

  

  adicionarConteudo(id: string | null) {
    const result = (dataFake.filter(dado => dado.id == id))[0]

    if (result != null) {
      this.artigoTitulo = result.titulo
      this.artigoImage = result.imagem
      this.artigoDescrcao = result.descricao
    }
  }

}

