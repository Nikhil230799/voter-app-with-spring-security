// import { DOCUMENT } from '@angular/common';
import { DOCUMENT } from '@angular/common';
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const guardGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const document = inject(DOCUMENT);

  const token = document.defaultView?.localStorage.getItem("token");

  if (token !== null) {
    return true;
  }
  else {
    router.navigateByUrl("login")
    return false;
  }

};
