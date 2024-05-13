import { Component, OnDestroy } from '@angular/core';
import { DataUtilService } from './header-service/data-util.service';
import { UserOptionsComponent } from './user-options/user-options/user-options.component';

@Component({
  selector: 'app-page-header',
  standalone: true,
  imports: [UserOptionsComponent],
  templateUrl: './page-header.component.html',
  styleUrl: './page-header.component.css'
})
export class PageHeaderComponent implements OnDestroy {
  totalTasks: number = 0;
  incompleteTasks: number = 0;

  constructor (private dataFetcher: DataUtilService) { 
    this.dataFetcher.numberOfTasks.subscribe((value: number) => {
      this.totalTasks = value;
    })
    this.dataFetcher.numberOfIncompleteTasks.subscribe((value: number) => {
      this.incompleteTasks = value;
    })
  }

  ngOnDestroy(): void {
    this.dataFetcher.numberOfTasks.unsubscribe();
    this.dataFetcher.numberOfIncompleteTasks.unsubscribe();
  }
}
