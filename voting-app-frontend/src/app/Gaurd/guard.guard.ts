// import { DOCUMENT } from '@angular/common';
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const guardGuard: CanActivateFn = (route, state) => {

  // const document=inject(DOCUMENT)
  const router = inject(Router);

  // token: string = "";

  const token = localStorage.getItem("token");

  if (token !== null) {
    return true;
  }
  else
  {
    router.navigateByUrl("login")
    return false;
  }
 
};
