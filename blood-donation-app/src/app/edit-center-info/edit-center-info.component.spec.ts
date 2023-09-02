import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditCenterInfoComponent } from './edit-center-info.component';

describe('EditCenterInfoComponent', () => {
  let component: EditCenterInfoComponent;
  let fixture: ComponentFixture<EditCenterInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditCenterInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditCenterInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
