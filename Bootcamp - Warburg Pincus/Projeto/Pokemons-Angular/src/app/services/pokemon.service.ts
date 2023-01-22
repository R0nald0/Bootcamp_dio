import { HttpClient } from '@angular/common/http';
import { Injectable} from '@angular/core';
import { Observable } from 'rxjs';
import { PokemonData } from '../model/pokemonData';

@Injectable({
  providedIn: 'root'
})
export class PokemonService {
 baseUrl = "https://pokeapi.co/api/v2/pokemon/"
 
  constructor(private http : HttpClient) { }

  getPokemonsApi( nomePokemon: string ):Observable<PokemonData>{
    try{
      return this.http.get<PokemonData>(`${this.baseUrl}${nomePokemon}`)
    }catch{
       throw new Error();
       
    }
    
  }
  
}

