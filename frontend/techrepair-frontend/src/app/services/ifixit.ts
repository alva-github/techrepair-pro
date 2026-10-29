import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GuiaIFixit } from '../models/guia-ifixit';

@Injectable({
  providedIn: 'root',
})
export class Ifixit {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/ifixit/buscar';

  buscar(dispositivo: string): Observable<GuiaIFixit[]> {
    const params = new HttpParams().set('dispositivo', dispositivo);

    return this.http.get<GuiaIFixit[]>(this.apiUrl, { params });
  }
}
