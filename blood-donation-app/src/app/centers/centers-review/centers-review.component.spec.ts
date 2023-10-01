import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentersReviewComponent } from './centers-review.component';

describe('CentersReviewComponent', () => {
  let component: CentersReviewComponent;
  let fixture: ComponentFixture<CentersReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CentersReviewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentersReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
