import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/page/home/home.component';
import { PrincipalComponent } from './components/page/principal/principal.component';

const routes: Routes = [
  {
    path: '',component:PrincipalComponent
  },
  {
    path: 'home',component:HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
