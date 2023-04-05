import { Component, Input } from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';



@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent {

 loginFomr = new FormGroup({
   email: new FormControl("",[Validators.required, Validators.email]),
   password : new FormControl("",[Validators.required,Validators.min(5),])
 })

 
  get email (){
     return this.loginFomr.get('email');
  }

get password(){

   return this.loginFomr.get('password');
}

onSubmit(){

}

}
