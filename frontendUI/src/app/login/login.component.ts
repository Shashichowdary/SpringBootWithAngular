import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  validateUser!: boolean;

  constructor(public fb: FormBuilder,
    private router : Router,
    private ngZone : NgZone,
    private loginService : LoginService,
    private authService : AuthService) {
      this.loginForm = this.fb.group({
        userName: [''],
        password: [''], 
      })
    }

  ngOnInit() {
    this.loginForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  loginFormSubmit() : any {
    this.loginService.Login(this.loginForm.value).subscribe(
      (res: boolean) => {
        this.validateUser = res;
        if(this.validateUser==true){
        console.log('User Logged In Successfully');
        const userName = this.loginForm.value.userName;
        console.log("userName when logged In", userName);
        this.authService.login(userName);
        //var expirationDate = new Date();
        //expirationDate.setTime(expirationDate.getTime() + 60 * 60 * 1000);
        //document.cookie = "username=" + userName + "; expires=" + expirationDate.toUTCString() + "; path=/localhost:4200;";
        this.ngZone.run(() => this.router.navigateByUrl('/dashBoard'));
        }else if(this.validateUser==false){
          console.log('User LogIn Failed');
          alert("Invalid Credentials!!!");
        }
      },
      (err) => {
        alert("Something went Wrong, Please try again!!!");
        console.log(err);
      }
    );
  }

  //this.userService.GetUsers().subscribe((res: any) =>{
    //console.log(res);
    //this.Users = res;
  //});

  clearForm() {
    // Clear the input fields and reset form state
    this.loginForm.reset();
  }

  // Helper methods to access form controls
  get userName() {
    
    return this.loginForm.get('userName');
  }

  get password() {
    return this.loginForm.get('password');
  }
}
