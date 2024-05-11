import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private httpClient: HttpClient) {}
  api = 'http://localhost:8080';
  public save(amount: number , userId:string , auctionId:string , isRegistry:boolean): Observable<any> {
    return this.httpClient.get(
      `${this.api}/payment/createTransaction/${amount}?isRegistry=${isRegistry}&userId=${userId}&auctionId=${auctionId}`
    );
  }

  public setStatus(userId:string , auctionId:string , orderId:string , isFailure:boolean){
    return this.httpClient.post<any>(`${this.api}/payment/set-status?userId=${userId}&auctionId=${auctionId}&transactionId=${orderId}&isFailure=${isFailure}`, null)

  }
}
