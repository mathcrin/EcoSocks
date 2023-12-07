import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {CardComponent} from "./composants/card/card.component";
import {TableauCardComponent} from "./composants/tableau-card/tableau-card.component";
import {ConsommationComponent} from "./composants/consommation/consommation.component";

@Component({
  selector: 'app-root',
  standalone: true,
    imports: [CommonModule, RouterOutlet, CardComponent, TableauCardComponent, ConsommationComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'FrontEndEcoSocks';
}
