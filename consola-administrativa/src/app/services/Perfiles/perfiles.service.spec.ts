
import { PerfilesService } from './perfiles.service';
import { TestBed } from '@angular/core/testing';

describe('PerfilesService', () => {
  let service: PerfilesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PerfilesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
