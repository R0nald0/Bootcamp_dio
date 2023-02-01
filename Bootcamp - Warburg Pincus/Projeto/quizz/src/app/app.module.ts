import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AngularFireModule} from '@angular/fire/compat'
import { environment } from '../environments/environment';
import { HomeComponent } from './components/page/home/home.component';
import { TituloPerguntaComponent } from './components/component/titulo-pergunta/titulo-pergunta.component';
import { IndexPerguntaComponent } from './components/component/index-pergunta/index-pergunta.component';
import { PerguntasComponent } from './components/component/perguntas/perguntas.component';
import { PrincipalComponent } from './components/page/principal/principal.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TituloPerguntaComponent,
    IndexPerguntaComponent,
    PerguntasComponent,
    PrincipalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AngularFireModule.initializeApp(environment.firebase)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
