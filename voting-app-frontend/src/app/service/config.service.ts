import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  private config: Record<string, any> = {};

  constructor(private http: HttpClient) {}

  loadConfig(): Observable<Record<string, any>> {
    return this.http.get<Record<string, any>>('/assets/config.json');
  }

  setConfig(data: Record<string, any>): void {
    this.config = data;
  }

  getConfig(key: string): any {
    return this.config[key];
  }
}
