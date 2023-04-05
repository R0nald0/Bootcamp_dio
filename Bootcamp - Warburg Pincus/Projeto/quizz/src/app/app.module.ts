import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AngularFireModule} from '@angular/fire/compat'
import { environment } from '../environments/environment';
import { HomeComponent } from './components/page/home/home.component';
import { TituloPerguntaComponent } from './components/component/titulo-pergunta/titulo-pergunta.component';
import { IndexPerguntaComponent } from './components/component/index-pergunta/index-pergunta.component';
import { PerguntasComponent } from './components/component/perguntas/perguntas.component';
import { PrincipalComponent } from './components/page/principal/principal.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { TableComponentComponent } from './components/shared/table-component/table-component.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponentComponent } from './components/component/login-component/login-component.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { ErrorStateMatcher, ShowOnDirtyErrorStateMatcher } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TituloPerguntaComponent,
    IndexPerguntaComponent,
    PerguntasComponent,
    PrincipalComponent,
    NavbarComponent,
    TableComponentComponent,
    LoginComponentComponent,
    
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    AngularFireModule.initializeApp(environment.firebase),
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  providers: [ {provide: ErrorStateMatcher, useClass: ShowOnDirtyErrorStateMatcher}],
  bootstrap: [AppComponent]
})
export class AppModule { }
