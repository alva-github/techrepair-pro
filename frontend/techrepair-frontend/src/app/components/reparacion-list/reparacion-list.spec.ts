import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReparacionList } from './reparacion-list';

describe('ReparacionList', () => {
  let component: ReparacionList;
  let fixture: ComponentFixture<ReparacionList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReparacionList],
    }).compileComponents();

    fixture = TestBed.createComponent(ReparacionList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
