import {Component, inject} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../../services/login.service";
import {UserInterface} from "../../models/user.interface";
import {environment} from "../../../environments/environment";
import {FloatLabelModule} from "primeng/floatlabel";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";

@Component({
  selector: 'my-app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FloatLabelModule,
    InputTextModule,
    ButtonModule
  ],
  templateUrl: './login.component.html',
  styles: ``
})
export class LoginComponent {

  formBuilder = inject(FormBuilder);
  http = inject(HttpClient)
  loginService = inject(LoginService)

  form = this.formBuilder.nonNullable.group({
    username: ["", Validators.required],
    password: ["", Validators.required],
  })

  onSubmit(): void {
    console.log("logging in")
    let data = this.form.getRawValue();
    this.http.post<UserInterface>(environment.apiUrl + "/authenticate", data)
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
