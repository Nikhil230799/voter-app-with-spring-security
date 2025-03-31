import { Component } from '@angular/core';

import { LoginComponent } from "./Auth/login/login.component";
import {  RouterLink, RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./Common/navbar/navbar.component";
 

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ LoginComponent, NavbarComponent,RouterOutlet,RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Vote Ease';

}

