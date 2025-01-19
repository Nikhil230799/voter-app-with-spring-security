import { Component, inject } from '@angular/core';
import { LoginComponent } from "../login/login.component";
import { DOCUMENT } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { RegisterComponent } from "../register/register.component";
import { ForgotPasswordComponent } from "../forgot-password/forgot-password.component";

@Component({
  selector: 'app-user-auth',
  standalone: true,
  imports: [LoginComponent, RegisterComponent, ForgotPasswordComponent],
  templateUrl: './user-auth.component.html',
  styleUrl: './user-auth.component.css'
})
export class UserAuthComponent {
  title = 'login page';

  document = inject(DOCUMENT);
  currentRoute!: String;

  constructor(private route: ActivatedRoute) {
    this.currentRoute = this.route.snapshot.url.join('/');
  }
}
