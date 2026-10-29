import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reparacion } from '../models/reparacion';

@Injectable({
  providedIn: 'root',
})
export class Reparaciones {
  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/api/reparaciones';

  listar(): Observable<Reparacion[]> {
    return this.http.get<Reparacion[]>(this.apiUrl);
  }

  actualizarEstado(id: number, estado: string): Observable<Reparacion> {
    return this.http.put<Reparacion>(`${this.apiUrl}/${id}`, estado, {
      headers: { 'Content-Type': 'text/plain' },
    });
  }

  crear(reparacion: Omit<Reparacion, 'id'>): Observable<Reparacion> {
    return this.http.post<Reparacion>(this.apiUrl, reparacion);
  }
  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  actualizarDispositivo(id: number, dispositivo: string): Observable<Reparacion> {
    return this.http.put<Reparacion>(`${this.apiUrl}/${id}/dispositivo`, dispositivo, {
      headers: { 'Content-Type': 'text/plain' },
    });
  }

  actualizarAveria(id: number, averia: string): Observable<Reparacion> {
    return this.http.put<Reparacion>(`${this.apiUrl}/${id}/averia`, averia, {
      headers: { 'Content-Type': 'text/plain' },
    });
  }
}
