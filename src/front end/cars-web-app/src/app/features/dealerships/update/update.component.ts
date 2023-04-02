import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/common/service/services.service';
import { DealershipDTO, DealershipsDTO } from '../components/overview/models/dealerships.models';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})

export class UpdateComponent {
  dealershipID?:number;
  name?:string;
  capacity?: number;
  address?: string;
  reputation?: number;
  hasService?: boolean;
  offersTradeIn?:boolean;

  constructor(private apiSvc: ApiService, private router:Router){}
  updateDealership(){
    if(this.dealershipID&&this.name&&this.capacity&&this.address&&this.reputation&&this.hasService&&this.offersTradeIn){
      const dealership: DealershipsDTO = {
        name:this.name,
        capacity: this.capacity,
        address: this.address,
        reputation: this.reputation,
        hasService: this.hasService,
        offersTradeIn: this.offersTradeIn
      }
      this.apiSvc.updateDealership(dealership, this.dealershipID).subscribe(result => {
        this.router.navigateByUrl('dealerships');
      }
      ,(err) =>{alert(err), console.log(err)}
      );
    }
  }
}
