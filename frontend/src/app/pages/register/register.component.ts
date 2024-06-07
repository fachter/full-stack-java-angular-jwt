import {Component, inject} from '@angular/core';
import {environment} from "../../../environments/environment";
import {UserInterface} from "../../models/user.interface";
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {HttpClient} from "@angular/common/http";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";

@Component({
  selector: 'my-app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FloatLabelModule,
    InputTextModule,
    ButtonModule
  ],
  templateUrl: './register.component.html',
  styles: ``
})
export class RegisterComponent {
  formBuilder = inject(FormBuilder);
  http = inject(HttpClient)
  loginService = inject(LoginService)

  form = this.formBuilder.nonNullable.group({
    username: ["", Validators.required],
    password: ["", Validators.required],
    confirmPassword: ["", Validators.required]
  })

  onSubmit(): void {
    console.log("submitting")
    if (!this.form.valid) {
      return;
    }
    let data = {
      username: this.form.getRawValue().username,
      password: this.form.getRawValue().password
    }
    console.log(data)
    this.http.post<UserInterface>(environment.apiUrl + "/register", data)
      .subscribe({
        next: (user) => {
          this.loginService.login(user);
        },
        error: (err) => {
          console.log(err)
        }
      })
  }

}
