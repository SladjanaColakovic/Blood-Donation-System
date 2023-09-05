import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodCenterAppointmentHistoryComponent } from './blood-center-appointment-history.component';

describe('BloodCenterAppointmentHistoryComponent', () => {
  let component: BloodCenterAppointmentHistoryComponent;
  let fixture: ComponentFixture<BloodCenterAppointmentHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodCenterAppointmentHistoryComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodCenterAppointmentHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
