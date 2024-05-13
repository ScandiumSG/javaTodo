import { Component } from '@angular/core';
import { UserObject } from '../../../utils/interfaces';
import { UserHandlerService } from '../../../services/user-service/user-handler.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-user-options',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './user-options.component.html',
  styleUrl: './user-options.component.css'
})
export class UserOptionsComponent {
  userData: UserObject | undefined;

  constructor(private userHandler: UserHandlerService) {
    this.userHandler.user.subscribe((value) => {
      console.log("Recieved new user data")
      if (value.username === "") {
        this.userData = undefined
      } else {
        this.userData = value;
      }})
  }

  submitLogin() {
    console.log("Attempted to log in");
    this.userHandler.attemptLogin("testuser", "password");
  }
}
