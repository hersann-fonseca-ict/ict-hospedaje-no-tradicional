import { TestBed } from '@angular/core/testing';

import { OAuthHelperService } from './oauth-helper.service';

describe('OAuthHelperService', () => {
  let service: OAuthHelperService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OAuthHelperService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
