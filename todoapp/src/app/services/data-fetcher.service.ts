import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject, async, debounceTime, interval, pipe, repeat, tap, throttle, timeInterval } from 'rxjs';
import { PostTask, Task } from '../utils/interfaces';
import { error } from 'node:console';
import { setInterval } from 'node:timers';

@Injectable({
  providedIn: 'any'
})

export class DataFetcherService implements OnDestroy {
  public recievedData: BehaviorSubject<Task[]> =  new BehaviorSubject<Task[]>([]);
  private baseUrl: string = 'http://localhost:8080/task/v2/';

  constructor(private http: HttpClient) {
    this.fetchData();
  }
  
  fetchData(): void {
    this.http.get<Task[]>(this.baseUrl)
    .pipe(tap(tasks => {this.recievedData.next(tasks);}))
    .subscribe({error: (e) => console.log(e)});
  }

  postData(newTask: PostTask): void {
    this.http.post<Task>(this.baseUrl, newTask).subscribe({
      next: (res) => {
        console.log(res); 
        this.fetchData(); // refetch data
      },
      error: (error) => {
        console.error('Error:', error); 
      }
    });
  }

  ngOnDestroy(): void {
    this.recievedData.unsubscribe();
  }
}
