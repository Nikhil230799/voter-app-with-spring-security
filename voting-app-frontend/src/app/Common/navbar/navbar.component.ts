import { DOCUMENT } from '@angular/common';
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


  constructor(private route: ActivatedRoute) {
    this.currentRoute = this.route.snapshot.url.join('/');
  }

  logout() {
    this.document.defaultView?.localStorage.removeItem('token');
  }

}
