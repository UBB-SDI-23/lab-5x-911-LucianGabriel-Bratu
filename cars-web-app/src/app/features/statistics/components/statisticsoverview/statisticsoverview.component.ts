import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common/service/services.service';
import { DealershipStatisticDTO } from 'src/app/features/dealerships/components/overview/models/dealerships.models';

@Component({
  selector: 'app-statisticsoverview',
  templateUrl: './statisticsoverview.component.html',
  styleUrls: ['./statisticsoverview.component.css']
})
export class StatisticsoverviewComponent implements OnInit{

  dealershipsStatistics: DealershipStatisticDTO[] = [];

  constructor(private apiSvc: ApiService, private router: Router)  {}
  ngOnInit(): void {
    this.apiSvc.getDealershipsStatistic().subscribe((result:DealershipStatisticDTO[]) =>{
      this.dealershipsStatistics = result;
      console.log(this.dealershipsStatistics);
    })
  }

}
