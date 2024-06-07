import {inject, Injectable} from "@angular/core";
import {AuthService} from "./auth.service";
import {MenuService} from "./menu.service";
import {Router} from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class LogoutService {
  authService = inject(AuthService)
  menuService = inject(MenuService)
  router = inject(Router)

  logout() {
    this.authService.currentUser.set(null);
    localStorage.removeItem("token")
    this.menuService.setMenu();
    this.router.navigate(["/auth/login"]).then();
  }
}

