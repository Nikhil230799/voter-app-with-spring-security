import { DOCUMENT } from '@angular/common';
import { Token } from '@angular/compiler';
import { Component, inject, OnInit } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { TokenDecoderService } from '../../service/token-decoder.service';
@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatIconModule, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  document = inject(DOCUMENT);
  tokenDecoder = inject(TokenDecoderService);
  currentRoute!: string;
  isTokenPresent: any;
  isTokenExpired: boolean = false;
  token!: any;
  constructor(private route: ActivatedRoute) {
    this.currentRoute = this.route.snapshot.url.join('/');
     this.isTokenPresent = this.document.defaultView?.localStorage.getItem('token');
  }

  logout() {
    this.document.defaultView?.localStorage.clear();
  }

  ngOnInit(): void {
    this.token = this.tokenDecoder.decodeToken(this.document.defaultView?.localStorage.getItem('token'));
    console.log(this.token)
    if (this.token == null || Math.floor(Date.now() / 1000) > this.token.exp) {
      this.isTokenExpired = true
      this.document.defaultView?.localStorage.clear();
    }
  }

}
