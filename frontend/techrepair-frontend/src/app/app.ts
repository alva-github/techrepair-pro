import { Component, inject, OnInit, signal, viewChild } from '@angular/core';
import { Reparaciones } from './services/reparaciones';
import { Reparacion } from './models/reparacion';
import { ReparacionForm } from './components/reparacion-form/reparacion-form';
import { ReparacionList } from './components/reparacion-list/reparacion-list';
import { IfixitSearch } from './components/ifixit-search/ifixit-search';
import { Dashboard } from './components/dashboard/dashboard';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [ReparacionForm, ReparacionList, IfixitSearch, Dashboard],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App implements OnInit {
  private reparacionesService = inject(Reparaciones);
  private listado = viewChild(ReparacionList);

  reparaciones = signal<Reparacion[]>([]);

  ngOnInit(): void {
    this.reparacionesService.listar().subscribe({
      next: (datos) => this.reparaciones.set(datos),
      error: (error) => console.error('Error al cargar reparaciones:', error),
    });
  }

  cambiarEstado(reparacion: Reparacion, nuevoEstado: string): void {
    if (reparacion.estado === nuevoEstado) return;

    this.reparacionesService.actualizarEstado(reparacion.id, nuevoEstado).subscribe({
      next: (actualizada) => {
        this.mostrarMensaje('Estado actualizado correctamente');
        this.reparaciones.update((lista) =>
          lista.map((r) => (r.id === actualizada.id ? actualizada : r)),
        );
      },
      error: (error) => {
        this.mostrarMensaje('Error al cambiar el estado', 'error');
        console.error('Error al actualizar:', error);
      },
    });
  }

  eliminarReparacion(reparacion: Reparacion): void {
    const confirmar = confirm(
      `¿Eliminar la reparación ${reparacion.id} de ${reparacion.dispositivo}?`,
    );

    if (!confirmar) return;

    this.reparacionesService.eliminar(reparacion.id).subscribe({
      next: () => {
        this.reparaciones.update((lista) => lista.filter((r) => r.id !== reparacion.id));
        this.mostrarMensaje('Reparación eliminada correctamente');
      },
      error: (error) => {
        console.error('Error al eliminar:', error);
        this.mostrarMensaje('Error al eliminar la reparación', 'error');
      },
    });
  }

  agregarReparacion(reparacion: Reparacion): void {
    this.reparaciones.update((lista) => [...lista, reparacion]);
    this.mostrarMensaje('Reparación creada correctamente');
  }

  alCambiarEstado(evento: { reparacion: Reparacion; estado: string }): void {
    this.cambiarEstado(evento.reparacion, evento.estado);
  }

  alEliminar(reparacion: Reparacion): void {
    this.eliminarReparacion(reparacion);
  }

  alEditar(evento: { reparacion: Reparacion; dispositivo: string; averia: string }): void {
    this.reparacionesService
      .actualizarDispositivo(evento.reparacion.id, evento.dispositivo)
      .subscribe({
        next: () => {
          this.reparacionesService.actualizarAveria(evento.reparacion.id, evento.averia).subscribe({
            next: (actualizada) => {
              this.reparaciones.update((lista) =>
                lista.map((r) => (r.id === actualizada.id ? actualizada : r)),
              );
              console.log('Reparación guardada correctamente');
              this.mostrarMensaje('Reparación actualizada correctamente');
              this.listado()?.finalizarEdicion();
            },
            error: (error) => {
              console.error('Error al actualizar avería:', error);
              this.mostrarMensaje('Error al actualizar la reparación', 'error');
            },
          });
        },
        error: (error) => {
          console.error('Error al actualizar dispositivo:', error);
          this.mostrarMensaje('Error al actualizar la reparación', 'error');
        },
      });
  }

  mensaje = signal('');
  tipoMensaje = signal<'exito' | 'error'>('exito');

  mostrarMensaje(texto: string, tipo: 'exito' | 'error' = 'exito'): void {
    this.mensaje.set(texto);
    this.tipoMensaje.set(tipo);
  }
}
