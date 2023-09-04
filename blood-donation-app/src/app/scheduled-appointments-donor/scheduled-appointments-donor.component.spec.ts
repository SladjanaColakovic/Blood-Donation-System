import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduledAppointmentsDonorComponent } from './scheduled-appointments-donor.component';

describe('ScheduledAppointmentsDonorComponent', () => {
  let component: ScheduledAppointmentsDonorComponent;
  let fixture: ComponentFixture<ScheduledAppointmentsDonorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduledAppointmentsDonorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduledAppointmentsDonorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
