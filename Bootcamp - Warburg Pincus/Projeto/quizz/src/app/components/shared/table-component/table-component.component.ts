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
   listUsers: UserRankingModel[]=[];
   
   imgAsset ='assets/images/user_profile_white.jpeg'
   constructor(){

   }
  ngOnInit(): void {
      this.addListOnView()
      this.initList()
    }

    addListOnView(){
      this.listRankingUser.forEach((it) =>{
      
        console.log(it.urlImagemPerfil)
      
         console.log(it.nome)
       });  
    }


   initList(){
     this.listRankingUser.forEach((it) =>{ 
        if (it.urlImagemPerfil === "" || it.urlImagemPerfil == null) {
             it.urlImagemPerfil = this.imgAsset
        }
       this.listUsers.push(it)
     })
   }
  
}
