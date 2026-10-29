import { Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Ifixit } from '../../services/ifixit';
import { GuiaIFixit } from '../../models/guia-ifixit';

@Component({
  selector: 'app-ifixit-search',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './ifixit-search.html',
  styleUrl: './ifixit-search.scss',
})
export class IfixitSearch {
  private ifixitService = inject(Ifixit);

  dispositivo = '';
  guias = signal<GuiaIFixit[]>([]);
  buscando = signal(false);
  error = signal('');

  buscar(): void {
    const consulta = this.dispositivo.trim();

    if (!consulta || this.buscando()) return;

    this.buscando.set(true);
    this.error.set('');
    this.guias.set([]);

    this.ifixitService.buscar(consulta).subscribe({
      next: (resultados) => {
        this.guias.set(resultados);
        this.buscando.set(false);
      },
      error: (err) => {
        console.error('Error al buscar guías:', err);
        this.error.set('No se han podido obtener las guías.');
        this.buscando.set(false);
      },
    });
  }
}
