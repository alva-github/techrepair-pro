import { Component, input, output } from '@angular/core';
import { Reparacion } from '../../models/reparacion';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reparacion-list',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './reparacion-list.html',
  styleUrl: './reparacion-list.scss',
})
export class ReparacionList {
  // Reparaciones recibidas desde App
  reparaciones = input.required<Reparacion[]>();

  // Avisar cuando cambia un estado
  estadoCambiado = output<{
    reparacion: Reparacion;
    estado: string;
  }>();

  // Avisar cuando se elimina una reparación
  reparacionEliminada = output<Reparacion>();

  // Avisar cuando se edita una reparación
  reparacionEditada = output<{
    reparacion: Reparacion;
    dispositivo: string;
    averia: string;
  }>();

  editandoId: number | null = null;

  edicion = {
    dispositivo: '',
    averia: '',
  };

  editar(reparacion: Reparacion): void {
    this.editandoId = reparacion.id;

    this.edicion = {
      dispositivo: reparacion.dispositivo,
      averia: reparacion.averia,
    };
  }

  cancelarEdicion(): void {
    this.editandoId = null;
  }

  guardarEdicion(reparacion: Reparacion): void {
    const dispositivo = this.edicion.dispositivo.trim();
    const averia = this.edicion.averia.trim();

    if (!dispositivo || !averia) return;

    this.reparacionEditada.emit({
      reparacion,
      dispositivo,
      averia,
    });
  }

  cambiarEstado(reparacion: Reparacion, estado: string): void {
    this.estadoCambiado.emit({ reparacion, estado });
  }

  eliminarReparacion(reparacion: Reparacion): void {
    this.reparacionEliminada.emit(reparacion);
  }
  finalizarEdicion(): void {
    this.editandoId = null;
  }
}
