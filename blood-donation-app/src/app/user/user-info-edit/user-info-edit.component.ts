import { Component, EventEmitter, Input, Output} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/authentication/auth.service';
import { UserService } from 'src/app/services/user.service';
import * as alertifyjs from 'alertifyjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-info-edit',
  templateUrl: './user-info-edit.component.html',
  styleUrls: ['./user-info-edit.component.css']
})
export class UserInfoEditComponent {

  @Input() user: any = {} as any
  @Output() emitUserChanged = new EventEmitter<any>();
  username = ""

  submitted: boolean;

  editForm = new FormGroup({
    address: new FormControl('', Validators.required),
    city: new FormControl('', Validators.required),
    country: new FormControl('', Validators.required),
    phone: new FormControl('', [Validators.required, Validators.pattern('[0-9]*')]),

  })

  constructor(private authService: AuthService, private userService: UserService, private router: Router) { }

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
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.success('Uspješno ažuriranje podataka', 4);
        this.router.navigate(['/profile'])
      }, error => {
        alertifyjs.set('notifier', 'position', 'bottom-center');
        alertifyjs.error(error.error, 15);
      })
    }
  }

}
