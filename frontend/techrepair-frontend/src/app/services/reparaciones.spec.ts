import { TestBed } from '@angular/core/testing';

import { Reparaciones } from './reparaciones';

describe('Reparaciones', () => {
  let service: Reparaciones;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Reparaciones);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
