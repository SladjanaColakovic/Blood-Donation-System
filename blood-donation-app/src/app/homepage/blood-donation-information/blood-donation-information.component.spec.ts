import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodDonationInformationComponent } from './blood-donation-information.component';

describe('BloodDonationInformationComponent', () => {
  let component: BloodDonationInformationComponent;
  let fixture: ComponentFixture<BloodDonationInformationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodDonationInformationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodDonationInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
