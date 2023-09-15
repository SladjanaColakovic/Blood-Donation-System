import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterSearchSortComponent } from './center-search-sort.component';

describe('CenterSearchSortComponent', () => {
  let component: CenterSearchSortComponent;
  let fixture: ComponentFixture<CenterSearchSortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CenterSearchSortComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CenterSearchSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
