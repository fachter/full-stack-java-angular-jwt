import {Component, inject} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {NavigationComponent} from "./pages/navigation/navigation.component";
import {MenuService} from "./services/menu.service";
import {AuthService} from "./services/auth.service";
import {LogoutService} from "./services/logout.service";
import {HttpClient} from "@angular/common/http";
import {LoginService} from "./services/login.service";
import {UserInterface} from "./models/user.interface";
import {environment} from "../environments/environment";

@Component({
  selector: 'my-app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  menuService = inject(MenuService);
  authService = inject(AuthService);
  loginService = inject(LoginService);
  logoutService = inject(LogoutService);
  http = inject(HttpClient)

  ngOnInit(): void {
    console.log("Check called")
    this.initialLogin();
    this.menuService.setMenu()
  }

  private initialLogin() {
    if (!localStorage.getItem("token"))
      return;
    this.http.get<UserInterface>(environment.apiUrl + "/refresh-token")
      .subscribe({
        next: (user) => {
          this.loginService.login(user);
        },
        error: () => {
          this.logoutService.logout();
        }
      })
  }

  logout() {
    this.logoutService.logout();
  }
}
