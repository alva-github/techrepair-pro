import { Component, computed, input } from '@angular/core';
import { Reparacion } from '../../models/reparacion';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard {
  // Recibimos las reparaciones desde App
  reparaciones = input.required<Reparacion[]>();

  pendientes = computed(() => this.reparaciones().filter((r) => r.estado === 'PENDIENTE').length);

  enProceso = computed(() => this.reparaciones().filter((r) => r.estado === 'EN_PROCESO').length);

  finalizadas = computed(() => this.reparaciones().filter((r) => r.estado === 'FINALIZADA').length);
}
