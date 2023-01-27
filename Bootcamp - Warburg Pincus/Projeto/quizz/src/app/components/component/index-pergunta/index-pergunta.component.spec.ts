import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexPerguntaComponent } from './index-pergunta.component';

describe('IndexPerguntaComponent', () => {
  let component: IndexPerguntaComponent;
  let fixture: ComponentFixture<IndexPerguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IndexPerguntaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IndexPerguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
