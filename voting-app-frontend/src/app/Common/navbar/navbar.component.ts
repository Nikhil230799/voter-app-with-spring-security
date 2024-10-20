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
  currentRoute!: string;
  showUrl!: boolean;

  constructor(private route: ActivatedRoute) {
    if (this.route.snapshot.url.join('/') == "login") {
      this.currentRoute = this.route.snapshot.url.join('/');
      console.log(this.route.snapshot.url.join('/'))
      this.showUrl = true;
    }
    if (this.route.snapshot.url.join('/') == "register") {
      this.currentRoute = this.route.snapshot.url.join('/register');
      console.log(this.route.snapshot.url.join('/'))
      this.showUrl = false;
    }

  }

}
