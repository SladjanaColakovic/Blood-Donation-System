import { Component } from '@angular/core';
import { CenterService } from '../../services/center.service';
import { AuthService } from '../../authentication/auth.service';
import * as alertifyjs from 'alertifyjs';

@Component({
  selector: 'app-center-profile',
  templateUrl: './center-profile.component.html',
  styleUrls: ['./center-profile.component.css']
})
export class CenterProfileComponent {

  center: any = {} as any;

  url: any;
  public selectedFile

  constructor(private centerService: CenterService, private authService: AuthService) { }

  ngOnInit() {
    let username = this.authService.getUser();
    this.centerService.getManagerCenter(username).subscribe((response: any) => {
      this.center = response;
    })
  }

  centerChanged(newCenter: any) {
    this.center = newCenter
  }

  changeImage(event: any) {
    this.selectedFile = event.target.files[0];
    if (!event.target.files[0] || event.target.files[0].length == 0) {
      return;
    }

    var mimeType = event.target.files[0].type;

    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    var reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);

    reader.onload = (_event) => {

      this.url = reader.result;
    }

    const formData = new FormData();

    formData.append("centerId", new Blob([JSON.stringify(this.center.id)], { type: "application/json" }));
    formData.append("image", this.selectedFile, this.selectedFile.name);

    this.centerService.changeImage(formData).subscribe((response: any) => {
      this.center = response;
    }, error => {
      alertifyjs.set('notifier', 'position', 'bottom-center');
      alertifyjs.error('Prekoračena je maksimalna veličina slike od 1048576 bajta', 10);
    })

  }


}
