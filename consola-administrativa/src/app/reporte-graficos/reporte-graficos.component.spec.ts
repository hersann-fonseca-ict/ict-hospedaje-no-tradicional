import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteGraficosComponent } from './reporte-graficos.component';

describe('ReporteGraficosComponent', () => {
  let component: ReporteGraficosComponent;
  let fixture: ComponentFixture<ReporteGraficosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReporteGraficosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReporteGraficosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
