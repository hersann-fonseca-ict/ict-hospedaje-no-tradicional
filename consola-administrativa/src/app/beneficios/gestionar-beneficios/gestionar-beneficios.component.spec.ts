import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarBeneficiosComponent } from './gestionar-beneficios.component';

describe('GestionarBeneficiosComponent', () => {
  let component: GestionarBeneficiosComponent;
  let fixture: ComponentFixture<GestionarBeneficiosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionarBeneficiosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionarBeneficiosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
