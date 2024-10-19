import {  Component, inject, OnInit } from '@angular/core';
import { NavbarComponent } from "../../Common/navbar/navbar.component";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NavbarComponent, MatFormFieldModule, MatInputModule, MatButtonModule, ReactiveFormsModule, CommonModule, HttpClientModule, ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',


})

export class LoginComponent implements OnInit {
  
  
  title = 'login page';
  formBuilder=inject(FormBuilder);
  http=inject(HttpClient);
  router=inject(Router);

  loginuser(userForm: any) {
    // this.http.post("http://localhost:2020/voting/auth/dologin", userForm).subscribe((resp: any) => {
    //   // console.log(resp.data)
    //   if(resp.responseCode==200 && resp.responseDesc=="login success")
    //     {
    //       localStorage.setItem("token", resp.data)
    //       this.router.navigateByUrl("dashboard")
    //     }
    // },error=>{
    //   alert("hello")
    // }) 
    this.http.post<any>("http://localhost:2020/voting/auth/dologin", userForm)
  .subscribe((resp) => {
    if (resp.responseCode === 200 && resp.responseDesc === "login success") {
      localStorage.setItem("token", resp.data);
      this.router.navigateByUrl("dashboard");
    }
  }, error => {
    console.error("Login error:", error);
    alert("Login failed.");
  });
  }
  
  submitForm() {

    if (this.userForm?.valid) {
    
     this.loginuser(this.userForm.value);
    }
  }
  userForm: any = {
    username: String,
    password: String
  };

  // constructor(private formBuilder: FormBuilder) { }

  

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', Validators.required]
    });
  }
}
