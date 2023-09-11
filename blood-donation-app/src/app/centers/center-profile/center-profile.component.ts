import { Component } from '@angular/core';
import { CenterService } from '../../services/center.service';
import { AuthService } from '../../authentication/auth.service';

@Component({
  selector: 'app-center-profile',
  templateUrl: './center-profile.component.html',
  styleUrls: ['./center-profile.component.css']
})
export class CenterProfileComponent {

  center: any = {} as any;

  constructor(private centerService: CenterService, private authService: AuthService){}

  ngOnInit(){
    let username = this.authService.getUser();
    this.centerService.getManagerCenter(username).subscribe((response: any) => {
      this.center = response;
    })
  }

  centerChanged(newCenter: any){
    this.center = newCenter
  }

}
