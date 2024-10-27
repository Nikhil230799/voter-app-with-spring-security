import { Component, Inject, inject, OnInit } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DOCUMENT, NgFor } from '@angular/common';
import { error } from 'node:console';
import { FormsModule, NgModel } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NavbarComponent, NgFor, FormsModule,MatInputModule, MatButtonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  router = inject(Router);
  http = inject(HttpClient);
  document = inject(DOCUMENT)
  radioButtonVal!: number;
  localStorage = document.defaultView?.localStorage;

  
  giveVote() {
    console.log(this.radioButtonVal);
  }


  logout() {
    localStorage.removeItem('token')
    this.router.navigateByUrl('/login')
  }
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
      if (resp.responseCode = 200) {
        this.candidates = resp.data;
        console.log(this.candidates)
      }
    }, error => {
      this.router.navigateByUrl("login")

    })
  }
  // this.http.get("http://localhost:8000").subscribe(data => {
  // console.log(data);
  //  });
}
