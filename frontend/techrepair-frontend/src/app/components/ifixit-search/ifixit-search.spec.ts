import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IfixitSearch } from './ifixit-search';

describe('IfixitSearch', () => {
  let component: IfixitSearch;
  let fixture: ComponentFixture<IfixitSearch>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IfixitSearch]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IfixitSearch);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
