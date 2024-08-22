import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AsignarClientePorAnalistaComponent } from './asignarClientePorAnalista.component';

describe('AsignarClientePorAnalistaComponent', () => {
  let component: AsignarClientePorAnalistaComponent;
  let fixture: ComponentFixture<AsignarClientePorAnalistaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AsignarClientePorAnalistaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AsignarClientePorAnalistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
