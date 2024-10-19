import { Component, inject } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
   
  router = inject(Router);
  logout() {
    localStorage.removeItem('token')
    this.router.navigateByUrl('/login')

  }
  http = inject(HttpClient);
//  headers=inject(HttpHeaders)
  
  callusercontroller() {
    // this.headers.set('content-type', 'application/json')
    // this.headers.set('alo', 'application/json')
    this.http.get("http://localhost:2020/voting/user/test",).subscribe((resp: any) => {
      console.log(resp);
    })
  }

}
