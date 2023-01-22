import { Component } from '@angular/core';
import { PokemonData } from 'src/app/model/pokemonData';
import { PokemonService } from 'src/app/services/pokemon.service';

@Component({
  selector: 'app-card-component',
  templateUrl: './card-component.component.html',
  styleUrls: ['./card-component.component.css']
})
export class CardComponentComponent {
  pokemon : PokemonData 

  constructor(private pokemonService : PokemonService){
       this.pokemon ={
         id : 0,
         name : "",
         types:[],
         sprites:{
          front_default : ""
         }

       }
  }
   
  ngOnInit() {
    
      this.getPokemon('ditto')  
  }

  getPokemon(searchName : string){
      this.pokemonService.getPokemonsApi(searchName).subscribe({
        next:data => {
          console.log(data);
          this.pokemon.name =data.name
          this.pokemon.sprites.front_default = data.sprites.front_default
          this.pokemon.types = data.types
        },
        error: erro => console.log(erro)
      })
     
      console.log(searchName)
    }
 
}
