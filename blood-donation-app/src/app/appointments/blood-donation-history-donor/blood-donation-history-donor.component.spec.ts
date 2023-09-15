import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodDonationHistoryDonorComponent } from './blood-donation-history-donor.component';

describe('BloodDonationHistoryComponent', () => {
  let component: BloodDonationHistoryDonorComponent;
  let fixture: ComponentFixture<BloodDonationHistoryDonorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodDonationHistoryDonorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodDonationHistoryDonorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
