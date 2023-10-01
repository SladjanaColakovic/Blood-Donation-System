import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CenterService } from 'src/app/services/center.service';
import * as alertifyjs from 'alertifyjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-center-info-edit',
  templateUrl: './center-info-edit.component.html',
  styleUrls: ['./center-info-edit.component.css']
})
export class CenterInfoEditComponent {

  @Input() center: any = {} as any
  @Output() emitCenterChanged = new EventEmitter<any>();

  submitted: boolean;

  editCenterForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    address: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    description: new FormControl('', Validators.required),
    capacity: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    workingTimeFrom: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
    workingTimeTo: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),
  })

  constructor(private centerService: CenterService, private router: Router) { }

  ngOnInit() {
    this.submitted = false;
  }

  editCenterInfo() {
    this.submitted = true;
    if (this.editCenterForm.valid) {
      let data = {
        id: this.center.id,
        name: this.center.name,
        email: this.center.email,
        address: this.center.address,
        city: this.center.city,
        country: this.center.country,
        phoneNumber: this.center.phoneNumber,
        workingTimeFrom: this.center.workingTimeFrom,
        workingTimeTo: this.center.workingTimeTo,
        capacity: this.center.capacity,
        description: this.center.description
      }

      this.centerService.editCenterInfo(data).subscribe((response: any) => {
        this.center = response;
        this.emitCenterChanged.emit(this.center);
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.success('Uspješno ažuriranje podataka', 4);
        this.router.navigate(['/editCenter'])
      }, error => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.error(error.error, 15);
      })
    }
  }

}
