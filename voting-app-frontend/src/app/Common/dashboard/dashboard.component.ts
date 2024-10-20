import { Component, inject, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavbarComponent,NgFor],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {


  router = inject(Router);
  logout() {
    localStorage.removeItem('token')
    this.router.navigateByUrl('/login')

  }
  http = inject(HttpClient);
  //  headers=inject(HttpHeaders)

  // callusercontroller() {
  //   // this.headers.set('content-type', 'application/json')
  //   // this.headers.set('alo', 'application/json')
  //   this.http.get("http://localhost:2020/voting/user/test",).subscribe((resp: any) => {

  //   })
  // }

  candidates !: { cd_name: string; cd_id: number; }[]; 
  
  ngOnInit(): void {
    this.http.get("http://localhost:2020/voting/user/getCandidatesList").subscribe((resp: any) => {
      console.log(resp)
      this.candidates=resp.data;
      console.log(this.candidates)
    })
  }
  // this.http.get("http://localhost:8000").subscribe(data => {
  // console.log(data);
  //  });
}
