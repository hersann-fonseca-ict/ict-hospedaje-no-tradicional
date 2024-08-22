import { Component, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { CommonService } from '../services/common.service';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit {
  private unsubscribe$ = new Subject<void>();
  loading = true;
  constructor(private common: CommonService) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.common.loadingService.pipe(takeUntil(this.unsubscribe$)).subscribe(data => {
          this.loading = data;
        });
    });
  }
}