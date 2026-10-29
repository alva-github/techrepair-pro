import { TestBed } from '@angular/core/testing';

import { Ifixit } from './ifixit';

describe('Ifixit', () => {
  let service: Ifixit;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Ifixit);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
