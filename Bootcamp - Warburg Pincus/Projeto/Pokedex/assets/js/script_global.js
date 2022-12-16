const pokemonList =document.getElementById('pokemonList');
const btnLoadMore =document.getElementById('loadMore');
const limit = 5;
let offset = 0;

function loadMoreItens(offset, limit){
    pokemon.getPokemons(offset,limit).then((listPokemon =[] )=>{
       const newHtml = listPokemon.map((pokemon)=>
    ` <li class="pokemon ${pokemon.type}">             
            <span class="number">#${pokemon.order}</span>
            <span class="nome">${pokemon.name}</span>
            <div class="detail">
                    <ol class= "types ${pokemon.type} ">
                        ${pokemon.types.map((type) =>`<li class="type ${type}">${type}</li>`).join("")}
                    </ol>
                <img src="${pokemon.photo}" 
            alt=${pokemon.name}>
            </div>  
        </li>
        `
       ).join('')   
       pokemonList.innerHTML += newHtml; 
       })    
   } 

 loadMoreItens(offset,limit)  ;

btnLoadMore.addEventListener('click',()=>{
       offset += limit
       loadMoreItens(offset, limit);  
});