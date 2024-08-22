import { TestBed } from '@angular/core/testing';

import { LocalizacionInmuebleService } from './localizacion-inmueble.service';

describe('LocalizacionInmuebleService', () => {
  let service: LocalizacionInmuebleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LocalizacionInmuebleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
