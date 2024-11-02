import { NgFor } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [FormsModule, NgFor, MatButtonModule],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent implements OnInit {
  candidates !: { cd_name: string; cd_id: number; }[];
  radioButtonVal!: number;
  router = inject(Router);
  http = inject(HttpClient);
  giveVote() {
    console.log(this.radioButtonVal);
  }


  ngOnInit(): void {
    this.http.get("http://localhost:2020/voting/user/getCandidatesList").subscribe((resp: any) => {
      if (resp.responseCode = 200) {
        this.candidates = resp.data;
      }
    }, error => {
      this.router.navigateByUrl("login")
    })
  }

}
