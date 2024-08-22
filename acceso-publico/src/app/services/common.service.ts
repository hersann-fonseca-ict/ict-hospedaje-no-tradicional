import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommonService {
  private loading = new BehaviorSubject(false);
  loadingService = this.loading.asObservable();

  constructor() { }
  _setLoading(item: any) {
    this.loading.next(item);
  }
}