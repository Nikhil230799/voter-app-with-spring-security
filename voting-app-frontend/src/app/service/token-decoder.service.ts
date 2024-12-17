import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class TokenDecoderService {

  decodeToken(token: any): any {
    try {
      return jwtDecode(token)
    }
    catch (error) {
      console.error('Error decoding JWT:', error);
      return null;
    }
  }
  constructor() { }
}
