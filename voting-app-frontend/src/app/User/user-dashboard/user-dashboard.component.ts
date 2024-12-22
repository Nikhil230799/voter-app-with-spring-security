import { DOCUMENT, NgClass, NgFor, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { AngularToastifyModule, ToastService } from 'angular-toastify';


@Component({
  selector: 'app-user-dashboard',
  standalone: true,
  imports: [FormsModule, NgFor, MatButtonModule, AngularToastifyModule, NgIf],
  templateUrl: './user-dashboard.component.html',
  styleUrl: './user-dashboard.component.css'
})
export class UserDashboardComponent implements OnInit {
  candidates !: { cd_name: string; cd_id: number; }[];
  radioButtonVal: number = 0;
  voteStatus: any=false ;
  router = inject(Router);
  http = inject(HttpClient);
  toaster = inject(ToastService);
  document = inject(DOCUMENT);


  giveVote() {
    this.http.get(`http://localhost:2020/voting/user/votecandidate?cd_id=${this.radioButtonVal}`).subscribe((resp: any) => {
      console.log(resp.responseCode)
      if (resp.responseCode === 200) {
        this.toaster.success(resp.data);
        this.voteStatus=true;
        this.document.defaultView?.localStorage.setItem("votestatus",this.voteStatus);
      }
      else if (resp.responseCode === 409) {
        this.toaster.error(resp.data);
      }
    }, error => {
      this.toaster.error("Internal server error, Try again after some time.")
    })
  }


  ngOnInit(): void {
    this.voteStatus=this.document.defaultView?.localStorage.getItem("votestatus");
    this.http.get("http://localhost:2020/voting/user/getCandidatesList").subscribe((resp: any) => {

      if (resp.responseCode = 200) {
        this.candidates = resp.data;
      }
    }, error => {
      this.router.navigateByUrl("login")
    })
  }


}
