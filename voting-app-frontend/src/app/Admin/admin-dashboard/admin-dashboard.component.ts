import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgClass, NgIf } from '@angular/common';
import { blob } from 'node:stream/consumers';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [MatInputModule, MatFormFieldModule, MatTableModule, NgIf, NgClass],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})


export class AdminDashboardComponent implements OnInit {

  tabSelector: boolean = true
  reportType: string = this.tabSelector ? "user" : "candidates";
  voterList!: {
    cd_id: number;
    cd_name: String;
    cd_votes: number;
  }[];

  userList !: {
    usr_id: number;
    usr_email: String;
    usr_phoneNo: String;
    usr_role: String;
    usr_userStatus: boolean;
    usr_username: String;
    usr_voteStatus: Boolean;
    candidates: {
      cd_name: String;
      cd_id: Number
    }
  }[];

  displayedColumns: string[] = [
    'usr_id',
    'usr_username',
    'usr_email',
    'usr_phoneNo',
    'usr_userStatus',
    'usr_role',
    'usr_voteStatus',
    'cd_name',
  ];
  dataSource: MatTableDataSource<any> | null = null;


  http = inject(HttpClient)
  router = inject(Router)

  ngOnInit(): void {
    this.getUserdetails();
  }



  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    if (this.dataSource != null) { this.dataSource.filter = filterValue.trim().toLowerCase(); }
  }


  toggleTabSelector() {
    this.tabSelector = !this.tabSelector
    this.reportType = this.tabSelector ? "user" : "candidates"; 
    if (this.tabSelector) {
      this.getUserdetails()
      this.displayedColumns = [
        'usr_id',
        'usr_username',
        'usr_email',
        'usr_phoneNo',
        'usr_userStatus',
        'usr_role',
        'usr_voteStatus',
        'cd_name',
      ];

    } else {
      this.getVoterdetails();
      this.displayedColumns = [
        'cd_id',
        'cd_name',
        'cd_votes'
      ];

    }
  }

  getVoterdetails() {
    this.http.get("http://localhost:2020/voting/admin/getCandidatesList").subscribe((resp: any) => {
      this.voterList = resp.data;
      this.dataSource = new MatTableDataSource(this.voterList);

    }, error => {
      console.log(error)
      this.router.navigateByUrl("login")
    })
  }

  getUserdetails() {
    this.http.get(`http://localhost:2020/voting/admin/userList`).subscribe((resp: any,) => {
      this.userList = resp.data;
      // console.log(this.userList)
      this.dataSource = new MatTableDataSource(this.userList);

    }, error => {
      console.log(error)
      this.router.navigateByUrl("login")
    })
  }

  exportToExcel() {
    this.http.get(`http://localhost:2020/voting/admin/userList/excel/${this.reportType}`, { responseType: 'blob' }).subscribe((resp: any,) => {
      const blob = new Blob([resp], { type: 'application/octet-stream' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = `${this.reportType}list.xlsx`; // File name for the download
      document.body.appendChild(a);
      a.click();
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    }, error => {
      console.error('Error downloading the file', error);
      this.router.navigateByUrl("login");
    })
  }
}
