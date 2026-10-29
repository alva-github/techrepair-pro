import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReparacionForm } from './reparacion-form';

describe('ReparacionForm', () => {
  let component: ReparacionForm;
  let fixture: ComponentFixture<ReparacionForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReparacionForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReparacionForm);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
