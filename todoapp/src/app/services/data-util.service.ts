import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { DataFetcherService } from './data-fetcher.service';
import { Task } from '../utils/interfaces';

@Injectable({
  providedIn: 'root'
})
export class DataUtilService {
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
}
