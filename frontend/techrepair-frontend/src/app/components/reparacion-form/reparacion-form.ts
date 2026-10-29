import { Component, output, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Reparacion } from '../../models/reparacion';
import { inject } from '@angular/core';
import { Reparaciones } from '../../services/reparaciones';

@Component({
  selector: 'app-reparacion-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './reparacion-form.html',
  styleUrl: './reparacion-form.scss',
})
export class ReparacionForm {
  private reparacionesService = inject(Reparaciones);
  reparacionCreada = output<Reparacion>();
  errorMensaje = signal('');
  guardando = signal(false);

  nuevaReparacion = {
    dispositivo: '',
    averia: '',
    estado: 'PENDIENTE',
  };

  crearReparacion(): void {
    if (this.guardando()) return;

    const dispositivo = this.nuevaReparacion.dispositivo.trim();
    const averia = this.nuevaReparacion.averia.trim();

    if (!dispositivo || !averia) {
      this.errorMensaje.set('Debes indicar el dispositivo y la avería.');
      return;
    }

    this.errorMensaje.set('');
    this.guardando.set(true);

    this.reparacionesService
      .crear({
        dispositivo,
        averia,
        estado: 'PENDIENTE',
      })
      .subscribe({
        next: (creada) => {
          this.reparacionCreada.emit(creada);
          this.nuevaReparacion = {
            dispositivo: '',
            averia: '',
            estado: 'PENDIENTE',
          };
          this.guardando.set(false);
        },
        error: (error) => {
          console.error('Error al crear reparación:', error);
          this.errorMensaje.set(
            'No se ha podido crear la reparación. Comprueba la conexión con el servidor.',
          );
          this.guardando.set(false);
        },
      });
  }
}
