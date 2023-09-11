import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterInfoEditComponent } from './center-info-edit.component';

describe('CenterInfoEditComponent', () => {
  let component: CenterInfoEditComponent;
  let fixture: ComponentFixture<CenterInfoEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CenterInfoEditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CenterInfoEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
