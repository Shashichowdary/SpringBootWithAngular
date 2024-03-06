// reset-password.component.ts

import { Component, NgZone, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../service/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.scss'],
})
export class ResetPasswordComponent implements OnInit {
  resetPasswordForm!: FormGroup;
  securityQuestions: string[] = [];
  validateAnswer!: boolean;

  constructor(private fb: FormBuilder, private userService: UserService, private router : Router,
    private ngZone : NgZone,) {}

  ngOnInit() {
    this.resetPasswordForm = this.fb.group({
      username: ['', Validators.required],
      securityQuestion1: ['', Validators.required],
      securityAnswer1: ['', Validators.required],
      password: ['', [Validators.required, this.passwordValidator]],
        confirmPassword: ['', [Validators.required]],
    }, { validators: this.passwordMatchValidator });
  }

  searchUsername(){
    const username = this.resetPasswordForm.get('username')?.value;
    if(username){
      console.log("userName", username);
      this.userService.GetSecurityQuestions(username).subscribe(
        (response) => {
          alert("Success!!! UserName Exists, Please select the security Questions!!!")
          this.securityQuestions = response;
        },
        (error) => {
          alert("Error!!!, Might be becuase of below reasons \n 1. UserName Doesn't exist \n 2. Server might be down")
          console.error('Error fetching the Security Questions', error);
        }
      )
    }
  }

  resetPassword() : any{
      this.userService.ResetPassword(this.resetPasswordForm.value).subscribe(
        (res: boolean) => {
          this.validateAnswer = res;
          if(this.validateAnswer){
          console.log('Password Reset Successfull!');
          this.ngZone.run(() => this.router.navigateByUrl('/login'));
          }else{
            console.log('Reset Password Failed');
            alert("Invalid Security Answer, Try Again!!!");
          }
        },
        (err) => {
          console.log(err);
        }
      );
  }

   

  passwordValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const value = control.value;

    // Define the password pattern
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{7,15}$/;

    // Check if the value matches the pattern
    return passwordPattern.test(value) ? null : { invalidPassword: true };
  }

  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')!.value;
    const confirmPassword = formGroup.get('confirmPassword')!.value;
  
    return password === confirmPassword ? null : { passwordMismatch: true };
  }  

  // Helper methods to access form controls
  get username() {
    return this.resetPasswordForm.get('username');
  }

  get securityQuestion1() {
    return this.resetPasswordForm.get('securityQuestion1');
  }

  get securityAnswer1() {
    return this.resetPasswordForm.get('securityAnswer1');
  }

  get password() {
    return this.resetPasswordForm.get('password');
  }

  get confirmPassword() {
    return this.resetPasswordForm.get('confirmPassword');
  }
}
