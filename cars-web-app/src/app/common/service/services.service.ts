import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { DealershipDTO, DealershipsDTO, DealershipStatisticDTO } from 'src/app/features/dealerships/components/overview/models/dealerships.models';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl:string = "http://13.49.148.93/";

  constructor(private http: HttpClient) { }
  getDealerships(): Observable<DealershipDTO[]>{
    return this.http.get(`${this.baseUrl}dealerships`) as Observable<DealershipDTO[]>;
  }

  getDealership(dealershipId: number): Observable<DealershipDTO>{
    return this.http.get(`${this.baseUrl}dealerships/${dealershipId}`) as Observable<DealershipDTO>;
  }

  addDealership(dealership: DealershipsDTO): Observable<DealershipsDTO>{
    return this.http.post(`${this.baseUrl}dealerships`, dealership) as Observable<DealershipsDTO>;
  }

  updateDealership(dealership: DealershipsDTO, dealershipID:number): Observable<DealershipDTO>{
    return this.http.put(`${this.baseUrl}dealerships/${dealershipID}`, dealership) as Observable<DealershipDTO>;
  }

  deleteDealership(dealershipID:number):Observable<string> {
    return this.http.delete(`${this.baseUrl}dealerships/${dealershipID}`) as Observable<string>;
  }

  getDealershipsStatistic(): Observable<DealershipStatisticDTO[]>{
    return this.http.get(`${this.baseUrl}dealerships/avgInventory`) as Observable<DealershipStatisticDTO[]>;
  }
}
