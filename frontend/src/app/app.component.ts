import { Component, HostListener, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {CardComponent, Carte} from "./composants/card/card.component";
import {TableauCardComponent} from "./composants/tableau-card/tableau-card.component";
import {ConsommationComponent} from "./composants/consommation/consommation.component";
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-root',
    imports: [CommonModule, RouterOutlet, CardComponent, TableauCardComponent, ConsommationComponent, HttpClientModule],
    standalone: true,
  templateUrl: './app.component.html',
    animations: [
        trigger('cardAnimation', [
            state('right', style({
                transform: 'translateX(0%)',
            })),
            state('left', style({
                transform: 'translateX(-100%)',
            })),
            transition('* => right', animate('2000ms ease-out')),
            transition('right => left', animate('2000ms ease-in')),
        ]),
    ],
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    constructor(private http: HttpClient) {
    }

    title = 'FrontEndEcoSocks';
    opacityValue = 0.4;

    carteGauche: { name: string, id: number, imageURL: string, emissionCO2: number } = {
        name: 'Carte Gauche',
        id: 404,
        imageURL: 'https://http.dog/404.jpg',
        emissionCO2: 404
    };
    carteDroite: { name: string, id: number, imageURL: string, emissionCO2: number } = {
        name: 'Carte Droite',
        id: 404,
        imageURL: 'https://http.dog/404.jpg',
        emissionCO2: 404
    };

    animationState = 'right';

    ngOnInit() {
        // Appel de la fonction pour récupérer les cartes au chargement du composant
        this.getCardsDroite();
        this.getCardsGauche();
    }

    getCardsGauche(): void {
        this.http.get<Carte>('http://www.connectés.fr:8080/cartes/1').subscribe((data) => {
            this.carteGauche = data;
        });
    }

    getCardsDroite(): void {
        this.http.get<Carte>('http://www.connectés.fr:8080/cartes/2').subscribe((data) => {
            this.carteDroite = data;
        });
    }

    getCardsAlea(): void {
        this.http.get<Carte>('http://www.connectés.fr:8080/cartes/').subscribe((data) => {
            this.carteDroite = data;
        });
    }

    onCardClick($event: Carte) {
        // Déplacez les cartes vers la droite
        this.animationState = 'right';
        // Ajoutez une nouvelle carte à la fin
        if (this.carteGauche && this.carteDroite) {
            this.carteGauche = this.carteDroite;
            this.getCardsAlea()//{ name: 'Nouvelle Carte', id: 3, emissionCO2: 67, imageURL: 'https://lh3.googleusercontent.com/pw/ADCreHeUNJlQkSf0j2Lfx0F6IMN_AGoOJc6mO_gMAKufm642Ep5bbCOKfJnQJTVXqGMPCfo3SkrsF_6S8X9UJkOGu-pw3-w7cZjke6VN_471nvDwRddVVwXXFx8iXEugQ8YO_hO7xrkzLdljE3spueiYruPI=w284-h177-s-no-gm?authuser=0' };
        }
        // Déplacez les cartes vers la gauche après l'ajout de la nouvelle carte
        setTimeout(() => {
            this.animationState = 'left';
        }, 0);
    }

    changeOpacity() {
        // Diminuer l'opacité, vous pouvez ajuster la valeur selon vos besoins
        if (this.opacityValue == 0.4) {
            this.opacityValue = 1;
        }
    }
}
