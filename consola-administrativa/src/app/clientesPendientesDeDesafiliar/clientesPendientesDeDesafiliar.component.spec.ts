import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientesPendientesDeDesafiliarComponent } from './clientesPendientesDeDesafiliar.component';

describe('ClientesPendientesDeDesafiliarComponent', () => {
  let component: ClientesPendientesDeDesafiliarComponent;
  let fixture: ComponentFixture<ClientesPendientesDeDesafiliarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClientesPendientesDeDesafiliarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientesPendientesDeDesafiliarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
