import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/authentication/auth.service';
import { ErrorAlertComponent } from 'src/app/error-alert/error-alert.component';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-info-edit',
  templateUrl: './user-info-edit.component.html',
  styleUrls: ['./user-info-edit.component.css']
})
export class UserInfoEditComponent {

  @Input() user: any = {} as any
  @Output() emitUserChanged = new EventEmitter<any>();
  username = ""
  message = ""

  submitted: boolean;

  @ViewChild(ErrorAlertComponent) alert: ErrorAlertComponent;
  alertClosed = true;

  editForm = new FormGroup({
    address: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required),
    phone: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),

  })

  constructor(private authService: AuthService, private userService: UserService) { }

  ngOnInit() {
    this.submitted = false;
    this.username = this.authService.getUser();
  }

  editUserInfo() {
    this.submitted = true;
    if (this.editForm.valid) {
      let data = {
        username: this.username,
        address: this.user.address,
        city: this.user.city,
        country: this.user.country,
        phoneNumber: this.user.phoneNumber
      }
      this.userService.edit(data).subscribe((response: any) => {
        this.user = response;
        this.emitUserChanged.emit(this.user);
        this.message = "Uspješno ažuriranje podataka"
        this.alertClosed = false
        this.alert.setAlertTime();
      }, error => {
        this.message = "Neuspješno ažuriranje podataka"
        this.alertClosed = false
        this.alert.setAlertTime();
      })
    }
  }

  closeAlert(event: any) {
    this.alertClosed = event
  }

}
