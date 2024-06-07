import {inject, Injectable} from "@angular/core";
import {UserInterface} from "../models/user.interface";
import {AuthService} from "./auth.service";
import {Router} from "@angular/router";
import {MenuService} from "./menu.service";

@Injectable({
  providedIn: "root"
})
export class LoginService {
  authService = inject(AuthService)
  router = inject(Router)
  menuService = inject(MenuService)

  login(user: UserInterface) {
    console.log(user)
    localStorage.setItem("token", user.token)
    this.authService.currentUser.set(user);
    this.router.navigateByUrl("/").then();
    this.menuService.setMenu()
  }
}

