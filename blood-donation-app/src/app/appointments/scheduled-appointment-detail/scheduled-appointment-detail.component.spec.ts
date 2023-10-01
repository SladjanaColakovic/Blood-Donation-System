import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduledAppointmentDetailComponent } from './scheduled-appointment-detail.component';

describe('ScheduledAppointmentDetailComponent', () => {
  let component: ScheduledAppointmentDetailComponent;
  let fixture: ComponentFixture<ScheduledAppointmentDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduledAppointmentDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ScheduledAppointmentDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
