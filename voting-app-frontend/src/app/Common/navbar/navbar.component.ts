import { DOCUMENT } from '@angular/common';
import { Token } from '@angular/compiler';
import { Component, inject, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatIconModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  document=inject(DOCUMENT);
  currentRoute!: string;
isTokenPresent: any;

  
  constructor(private route: ActivatedRoute) {
    this.currentRoute = this.route.snapshot.url.join('/');
     this.isTokenPresent= this.document.defaultView?.localStorage.getItem('token');
    
  }

  logout() {
    this.document.defaultView?.localStorage.clear();
  }

}
