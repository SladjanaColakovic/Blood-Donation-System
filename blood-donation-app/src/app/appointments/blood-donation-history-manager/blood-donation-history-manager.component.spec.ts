import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodDonationHistoryManagerComponent } from './blood-donation-history-manager.component';

describe('BloodDonationHistoryManagerComponent', () => {
  let component: BloodDonationHistoryManagerComponent;
  let fixture: ComponentFixture<BloodDonationHistoryManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodDonationHistoryManagerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodDonationHistoryManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
