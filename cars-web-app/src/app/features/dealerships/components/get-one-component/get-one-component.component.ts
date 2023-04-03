import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/common/service/services.service';
import { DealershipDTO } from '../overview/models/dealerships.models';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-get-one-component',
  templateUrl: './get-one-component.component.html',
  styleUrls: ['./get-one-component.component.css']
})


export class GetOneComponentComponent implements OnInit{
  id?:number;
  dealership?: DealershipDTO;

  constructor(private apiSvc: ApiService, private activatedRoute: ActivatedRoute){}
  ngOnInit(): void{
    this.activatedRoute.params.subscribe(params => {
      this.id = params['id'];
      this.apiSvc.getDealership(this.id!).subscribe((dealership: DealershipDTO) =>{
        this.dealership = dealership;
        this.dealership.id = this.id!;
      })
    });
}
}
