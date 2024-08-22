import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsuariosAfiliadosComponent } from './usuarios-afiliados.component';

describe('UsuariosAfiliadosComponent', () => {
  let component: UsuariosAfiliadosComponent;
  let fixture: ComponentFixture<UsuariosAfiliadosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsuariosAfiliadosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsuariosAfiliadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
