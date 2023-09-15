import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodDonationGaleryComponent } from './blood-donation-galery.component';

describe('BloodDonationGaleryComponent', () => {
  let component: BloodDonationGaleryComponent;
  let fixture: ComponentFixture<BloodDonationGaleryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodDonationGaleryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodDonationGaleryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
