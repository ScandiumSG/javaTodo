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

  putData(updatedTask: Task): void {
    this.http.put<Task>(this.baseUrl, updatedTask).subscribe({
      next: () => {
        this.fetchData();
      },
      error: (err) => {
        console.error("Error: ", err)
      }
    })
  }

  postData(newTask: PostTask): void {
    this.http.post<Task>(this.baseUrl, newTask).subscribe({
      next: () => {
        this.fetchData(); // refetch data
      },
      error: (err) => {
        console.error("Error: ", err); 
      }
    });
  }

  deleteData(task: Task): void {
    this.http.delete<Task>(this.baseUrl.concat(task.id.toString())).subscribe({
      next: () => {
        this.fetchData();
      }
    })
  }

  ngOnDestroy(): void {
    this.recievedData.unsubscribe();
  }
}
