import { Component } from '@angular/core';
import {CardComponent} from "../card/card.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-consommation',
  standalone: true,
    imports: [
        CardComponent,
        RouterOutlet
    ],
  templateUrl: './consommation.component.html',
  styleUrl: './consommation.component.css'
})
export class ConsommationComponent {

}
