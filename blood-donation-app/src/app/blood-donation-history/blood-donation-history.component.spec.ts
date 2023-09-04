import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodDonationHistoryComponent } from './blood-donation-history.component';

describe('BloodDonationHistoryComponent', () => {
  let component: BloodDonationHistoryComponent;
  let fixture: ComponentFixture<BloodDonationHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodDonationHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodDonationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
