import { Component,Input, OnInit, ɵSafeResourceUrl} from '@angular/core';
import { UserRankingModel } from 'src/app/model/UserRakingModel';

@Component({
  selector: 'app-table-component',
  templateUrl: './table-component.component.html',
  styleUrls: ['./table-component.component.css']
})
export class TableComponentComponent implements OnInit{

  @Input()
   listRankingUser: UserRankingModel[]=[];
   position = 1
   imgAsset ='assets/images/user_profile_white.jpeg'
   constructor(){

   }
  ngOnInit(): void {
      this.addListOnView()
    }


    addListOnView(){
      this.listRankingUser.forEach((it) =>{
      
        console.log(it.urlImagemPerfil)
         this.position++
         console.log(it.nome)
       });  
    }



  
}
