import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarTipoServiciosComponent } from './gestionar-tipo-servicios.component';

describe('GestionarTipoServiciosComponent', () => {
  let component: GestionarTipoServiciosComponent;
  let fixture: ComponentFixture<GestionarTipoServiciosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GestionarTipoServiciosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionarTipoServiciosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
