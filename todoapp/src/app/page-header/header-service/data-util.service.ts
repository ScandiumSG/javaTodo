import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { DataFetcherService } from '../../services/data-fetcher.service';
import { Task } from '../../utils/interfaces';

@Injectable({
  providedIn: 'root'
})
export class DataUtilService implements OnDestroy {
  public numberOfTasks: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  public numberOfIncompleteTasks: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor(private data: DataFetcherService) {
    this.data.recievedData.subscribe(value => {
      this.numberOfTasks.next(value.length);
      this.numberOfIncompleteTasks.next(value.filter(task => !task.completed).length);
    })
   }

   updateUtils(data: Array<Task>) {
    this.numberOfTasks.next(data.length);
    this.numberOfIncompleteTasks.next(data.filter(task => !task.completed).length)
   }

   ngOnDestroy(): void {
     this.data.recievedData.unsubscribe();
   }
}
