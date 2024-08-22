import { Component, OnInit } from '@angular/core';
import { CommonService } from 'app/services/common/common.service';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
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
