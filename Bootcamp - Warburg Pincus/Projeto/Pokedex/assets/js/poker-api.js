const pokemon = {}

 pokemon.getPokemonsDetail = (pokemonD) => {
         return fetch(pokemonD.url)
           .then(response => response.json()
          .then(convertoPokemonToModel)
         )
  
}


function convertoPokemonToModel(pokemonDetails){
    const pokemonModel= new PokemonModel();

     pokemonModel.name = pokemonDetails.name;
     pokemonModel.number =pokemonDetails.order;
       
     const types  =pokemonDetails.types.map((types)=>types.type.name);
     const [type] = types;
     pokemonModel.type =type;
     pokemonModel.types =types;
     pokemonModel.photo = pokemonDetails.sprites.other.dream_world.front_default;


     return pokemonModel

  }

pokemon.getPokemons = (offset=0,limit=5) => {
const ENDPOINT = "https://pokeapi.co/api/v2/pokemon?"
const url =`${ENDPOINT}offset=${offset}&limit=${limit}`


  return  fetch(url)
         .then((response)=> response.json())
         .then((jsonBody) =>jsonBody.results)
         .then((pokemons) => pokemons.map(pokemon.getPokemonsDetail))
         .then(detailRequest => Promise.all(detailRequest))
         .then(detailPokemon => detailPokemon)
         
         .catch((erro)=>console.log(erro));
}


