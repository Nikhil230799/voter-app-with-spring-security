import { Component, inject } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { RouterLink } from '@angular/router';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [NavbarComponent, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  title = 'Vote Ease';
  document = inject(DOCUMENT);
  isTokenPresent: any;

  constructor() {
    this.isTokenPresent = this.document.defaultView?.localStorage.getItem('token');

    
  }
}
