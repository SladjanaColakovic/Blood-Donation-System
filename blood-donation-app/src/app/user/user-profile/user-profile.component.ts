import { Component} from '@angular/core';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent {

  user: any = {} as any
  url = "";
  username = ""

  constructor(private userService: UserService, private authService: AuthService){}

  ngOnInit(){
    this.username = this.authService.getUser();
    this.userService.current(this.username).subscribe((response: any) => {
      this.user = response;
      if(this.user.gender == 'Female'){
        this.url = "assets/female.png"
      }else{
        this.url = "assets/male.png"
      }
    })
  }

  userChanged(newUser: any){
    this.user = newUser;
  }

}
