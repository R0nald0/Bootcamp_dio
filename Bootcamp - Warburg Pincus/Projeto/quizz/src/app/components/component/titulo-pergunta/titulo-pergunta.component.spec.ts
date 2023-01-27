import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TituloPerguntaComponent } from './titulo-pergunta.component';

describe('TituloPerguntaComponent', () => {
  let component: TituloPerguntaComponent;
  let fixture: ComponentFixture<TituloPerguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TituloPerguntaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TituloPerguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
