import {  HttpInterceptorFn } from '@angular/common/http';
import { Type } from '@angular/core';


export const interceptInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');

  const clonereq = req.clone({
    setHeaders: {
       'Authorization': `${token}`,
       'Content-Type':  'text/plain',
      //  'Access-Control-Allow-Headers': 'Content-Type',
      //  'Cache-Control': 'max-age=3600'
    }
  })
  //  clonereq.headers.append()

  return next(clonereq);
};
