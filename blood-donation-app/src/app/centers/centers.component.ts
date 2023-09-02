import { Component } from '@angular/core';
import { CenterService } from '../services/center.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent {

  centers: any[]

  constructor(private centerService: CenterService, private router: Router){}

  ngOnInit(){
    this.centerService.getAll().subscribe((response: any) => {
      this.centers = response;
    })
  }

  centerDetail(id: any){
    this.router.navigate(['/center', id]);
  }
}
