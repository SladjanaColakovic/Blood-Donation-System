import { Component } from '@angular/core';
import { CenterService } from '../../services/center.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-center-detail',
  templateUrl: './center-detail.component.html',
  styleUrls: ['./center-detail.component.css']
})
export class CenterDetailComponent {

  constructor(private centerService: CenterService, private activatedRoute: ActivatedRoute) { }

  id: string;
  center: any = {} as any;

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this.centerService.getById(this.id).subscribe((response: any) => {
        this.center = response;
        console.log(this.center)
      })
    })
  }
}
