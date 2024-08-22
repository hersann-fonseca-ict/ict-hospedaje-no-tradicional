import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteHaciendaComponent } from './reporte-hacienda.component';

describe('ReporteHaciendaComponent', () => {
  let component: ReporteHaciendaComponent;
  let fixture: ComponentFixture<ReporteHaciendaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteHaciendaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteHaciendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
