import { Component, NgZone, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-edit-details',
  templateUrl: './edit-details.component.html',
  styleUrls: ['./edit-details.component.scss']
})

export class EditDetailsComponent implements OnInit {
  getUserName : any;
  editForm!: FormGroup;

  constructor(public fb: FormBuilder,
    private router : Router,
    private ngZone : NgZone,
    private activatedRoute: ActivatedRoute,
    private userService : UserService
    ) {
      this.getUserName = this.activatedRoute.snapshot.paramMap.get('userName');
        this.userService.GetUserByUserName(this.getUserName).subscribe((res: { [x: string]: any}) => {
          console.log("response" + res)
          const formattedDate = formatDateOfBirth(res['dateOfBirth']);
          this.editForm.setValue({
            firstName: res['firstName'],
            lastName: res['lastName'],
            gender: res['gender'],
            email: res['email'],
            dateOfBirth: formattedDate,
            mobileNumber: res['mobileNumber']
          });
        });
    }

  ngOnInit() {
    this.editForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      gender: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dateOfBirth: ['', Validators.required],
      mobileNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
    });
  }

  editOnSubmit(): any {
    this.userService.UpdateUserByUserName(this.getUserName, this.editForm.value).subscribe(
      () => {
        console.log('Data Updated Successfully!');
        alert("User Profile is Updated SUccessfully!!!");
        this.ngZone.run(() => this.router.navigateByUrl('/dashBoard'));
      },
      (err) => {
        console.log(err);
      }
    );
  }

  clearForm() {
    // Clear the input fields and reset form state
    this.editForm.reset();
  }

  // Helper methods to access form controls
  get firstName() {
    return this.editForm.get('firstName');
  }

  get lastName() {
    return this.editForm.get('lastName');
  }

  get gender() {
    return this.editForm.get('gender');
  }

  get email() {
    return this.editForm.get('email');
  }

  get dateOfBirth() {
    return this.editForm.get('dateOfBirth');
  }

  get mobileNumber(){
    return this.editForm.get('mobileNumber');
  }
}


function formatDateOfBirth(date: any) {
  const dateOfBirth = new Date(date);
          const formattedDate = `${dateOfBirth.getFullYear()}-${(dateOfBirth.getMonth() + 1)
          .toString()
          .padStart(2, '0')}-${dateOfBirth.getDate().toString().padStart(2, '0')}`;
          return formattedDate;
}

