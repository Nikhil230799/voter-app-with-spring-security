import { NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { RouterLink } from '@angular/router';
import { AngularToastifyModule, ToastService } from 'angular-toastify';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [FormsModule, AngularToastifyModule, NgIf, MatFormFieldModule, ReactiveFormsModule, MatInputModule, MatButtonModule, RouterLink],

  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  captchaUrl = 'http://localhost:2020/voting/captcha/generate';
  verifyUrl = 'http://localhost:2020/voting/captcha/verify';
  verifyOtp = 'http://localhost:2020/voting/otp/verify';
  captchaInput = '';
  captchaKey = '';
  showOtpfield: boolean = false;
  iseditable: boolean = false;
  toaster = inject(ToastService);
  http = inject(HttpClient);
  formBuilder = inject(FormBuilder);
  // http = inject(HttpClient);
  apicaptcha: any;
  userForm: any = {
    username: String,
    captcha: String
  };

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      username: [{ value: '', disabled: this.iseditable }, [Validators.required, Validators.minLength(4),]],
      captcha: [{ value: '', disabled: this.iseditable }, Validators.required],
      otp: ['']
    });
    this.refreshCaptcha();
  }



  refreshCaptcha(): void {

    this.http.get<any>(this.captchaUrl)
      .subscribe((resp) => {
        this.captchaInput = resp.data.captcha
        this.captchaKey = resp.data.key
        this.showOtpfield = false
        this.userForm.controls['captcha'].enable();
        this.userForm.controls['username'].enable();
        this.iseditable = !this.iseditable
      }, (error) => {
        if (error.error.responseCode = 400)
          this.toaster.error(error.error.data)
      })
  }

  submitForm() {
    if (!this.showOtpfield) {
      this.http.post<any>(this.verifyUrl, { captcha: this.userForm.value.captcha, key: this.captchaKey })
        .subscribe((resp) => {
          this.captchaInput = resp.captcha
          if (resp.data) {
            this.toaster.success("OTP is sent to registered mail.")
            this.showOtpfield = true;
            this.userForm.controls['captcha'].disable();
            this.userForm.controls['username'].disable();
            this.iseditable = !this.iseditable
          }
          if (!resp.data) {
            this.toaster.success("Invalid username or captcha")
            this.refreshCaptcha();
          }
        })
    }
    else {
      this.http.post<any>(this.verifyOtp, { captcha: this.userForm.value.captcha, key: this.captchaKey })
      .subscribe((resp) => {
        this.captchaInput = resp.captcha
        if (resp.data) {
          this.toaster.success("OTP is sent to registered mail.")
          this.showOtpfield = true;
          this.userForm.controls['captcha'].disable();
          this.userForm.controls['username'].disable();
          this.iseditable = !this.iseditable
        }
        if (!resp.data) {
          this.toaster.success("Invalid username or captcha")
          this.refreshCaptcha();
        }
      })

    }

  }
}

