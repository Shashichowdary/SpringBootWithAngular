import { Component, NgZone, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';


function forbiddenNameValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const forbidden = /[0-9!@#$%^&*(),.?":{}|<>]/.test(control.value);
    return forbidden ? { 'forbiddenName': { value: control.value } } : null;
  };
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;

  constructor(public fb: FormBuilder,
    private router : Router,
    private ngZone : NgZone,
    private userService : UserService
    ) {
      this.signupForm = this.fb.group({
        firstName: [''],
        lastName: [''],
        gender: [''],
        email: [''],
        dateOfBirth: [''],
        mobileNumber:[''],
        password: [''],
        securityQuestion1: [''],
        securityAnswer1:[''],
        securityQuestion2: [''],
        securityAnswer2: [''],
      })
    }

    ngOnInit() {
      this.signupForm = this.fb.group({
        firstName: ['', [Validators.required, this.noSpecialCharOrNumbers]],
        lastName: ['', [Validators.required, this.noSpecialCharOrNumbers]],
        gender: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        dateOfBirth: ['', Validators.required],
        mobileNumber: [
          '',
          [
            Validators.required,
            Validators.pattern(/^\d+$/),
            this.validateMobileNumberLength,
          ],
        ],
        password: ['', [Validators.required, this.passwordValidator]],
        confirmPassword: ['', [Validators.required]],
        securityQuestion1: ['', Validators.required],
        securityAnswer1: ['', Validators.required],
        securityQuestion2: ['', Validators.required],
        securityAnswer2: ['', Validators.required],
      }, { validators: this.passwordMatchValidator });
    }

  signupFormSubmit(): any {
    this.userService.CreateNewUser(this.signupForm.value).subscribe(
      () => {
        console.log('Data Added Successfully!');
        alert("User Registration sucessfull!!!")
        this.ngZone.run(() => this.router.navigateByUrl('/login'));
      },
      (err) => {
        alert("Error, This can be due to any of the following reasons \n 1.Duplicate UserName or EmailId!!!   \n 2. The Server might be down!!!");
        console.log(err);
      }
    );
  }

  resetForm(): void {
    this.signupForm.reset(); // Resets the form to its initial state
  }

  

  
  get firstName() {
    return this.signupForm.get('firstName');
  }

  get lastName() {
    return this.signupForm.get('lastName');
  }

  get gender() {
    return this.signupForm.get('gender');
  }

  get email() {
    return this.signupForm.get('email');
  }

  get dateOfBirth() {
    return this.signupForm.get('dateOfBirth');
  }

  get mobileNumber(){
    return this.signupForm.get('mobileNumber');
  }

  get password() {
    return this.signupForm.get('password');
  }

  get confirmPassword() {
    return this.signupForm.get('confirmPassword');
  }

  get securityQuestion1() {
    return this.signupForm.get('securityQuestion1');
  }

  get securityAnswer1() {
    return this.signupForm.get('securityAnswer1');
  }

  get securityQuestion2() {
    return this.signupForm.get('securityQuestion2');
  }

  get securityAnswer2() {
    return this.signupForm.get('securityAnswer2');
  }

  validateMobileNumberLength(control: any): { [key: string]: boolean } | null {
    const value = control.value;
    const isValidLength = value && value.length === 10;

    return isValidLength ? null : { invalidMobileNumberLength: true };
  }
  
  passwordValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const value = control.value;

    // Define the password pattern
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{7,15}$/;

    // Check if the value matches the pattern
    return passwordPattern.test(value) ? null : { invalidPassword: true };
  }

  // noSpecialCharOrNumbers(): ValidatorFn {
  //   return (control: AbstractControl): { [key: string]: any } | null => {
  //     const forbidden = /[0-9!@#$%^&*(),.?":{}|<>]/.test(control.value);
  //     return forbidden ? { 'forbiddenName': { value: control.value } } : null;
  //   };
  // }

  noSpecialCharOrNumbers(control: AbstractControl): { [key: string]: boolean } | null {
    const value = control.value;

    //No SPecial Characters Pattern
    const namePattern = /^[a-zA-Z ]+$/;
    // Check if the value matches the pattern
    return namePattern.test(value) ? null : { invalidName: true };
  }
  
  // checking password is matching with confirmpassword or not
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')!.value;
    const confirmPassword = formGroup.get('confirmPassword')!.value;
  
    return password === confirmPassword ? null : { passwordMismatch: true };
  }  
}
