import { Component, HostListener, OnInit } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import {CardComponent, Carte} from "./composants/card/card.component";
import {TableauCardComponent} from "./composants/tableau-card/tableau-card.component";
import {ConsommationComponent} from "./composants/consommation/consommation.component";
import {animate, state, style, transition, trigger} from "@angular/animations";
import { CookieService } from 'ngx-cookie-service';

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
export class AppComponent implements OnInit{
    constructor(private http: HttpClient, private cookieService: CookieService) {
    }



    title = 'FrontEndEcoSocks';
    opacityValue = 0.4;
    opacityValueInfos = 1;
    points = 0;
    randomId = 0;
    showScoreTable = false;
    Scores = null;

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
        if(this.getValueFromCookie() != null){
            this.http.get<number>('http://localhost:8080/jeux/score/502').subscribe((data) => {
                this.points = data;
            });
            //Normalement nous devriosn récupérer l'id partie depusi les cookies
            this.http.get<Carte>('http://localhost:8080/cartes/start/1').subscribe((data) => {
                this.carteGauche = data;
            });
            this.http.get<Carte>('http://localhost:8080/cartes/502').subscribe((data) => {
                this.carteDroite = data;
            });
        } else {
            this.generateRandomId();
            this.http.get<Carte>('http://localhost:8080/cartes/start/1').subscribe((data) => {
                this.carteGauche = data;
            });
            this.http.get<Carte>('http://localhost:8080/cartes/start/2').subscribe((data) => {
                this.carteDroite = data;
            });
        }

        this.http.get<Carte>('http://localhost:8080/jeux/scores/502').subscribe((data) => {
            // @ts-ignore
            this.Scores = data;
        });
    }

    triggerKonamiAction() {
        window.open("https://operfield.github.io/RabbidsLeak/", "_blank");
    }

    generateRandomId() {
        // Générer un ID aléatoire en utilisant la méthode Math.random()
        this.http.get<number>('http://localhost:8080/jeux/generate/502').subscribe((data) => {
            this.randomId = data;
        });

        // Stocker l'ID dans un cookie avec le nom "userId"
        this.cookieService.set('userId', String(this.randomId));

        console.log('ID aléatoire généré et stocké dans le cookie :', this.randomId);
    }

    getValueFromCookie() {
        const value = this.cookieService.get('userId');
        console.log('Valeur du cookie :', value);
        return value;
    }

    // Fonction pour supprimer le cookie
    deleteCookie() {
        // Supprimer le cookie avec le nom "userId"
        this.cookieService.delete('userId');

        console.log('Cookie supprimé avec succès.');
    }



    onCardClick($event: Carte) {
        // Déplacez les cartes vers la droite
        this.animationState = 'right';
        // Ajoutez une nouvelle carte à la fin
        if (this.carteGauche && this.carteDroite) {
            this.carteGauche = this.carteDroite;
            this.http.get<Carte>('http://localhost:8080/cartes/502').subscribe((data) => {
                this.carteDroite = data;
            });
        }
        // Déplacez les cartes vers la gauche après l'ajout de la nouvelle carte
        setTimeout(() => {
            this.animationState = 'left';
        }, 0);
    }

    onDroite($event: Boolean){
        if(this.carteDroite.emissionCO2 < this.carteGauche.emissionCO2){
            this.points = this.points + 1;
        }
    }

    onGauche($event: Boolean){
        if(this.carteGauche.emissionCO2 < this.carteDroite.emissionCO2){
            this.points = 0;
            this.deleteCookie();
        }
    }

    changeOpacity() {
        // Diminuer l'opacité, vous pouvez ajuster la valeur selon vos besoins
        if (this.opacityValue == 0.4) {
            this.opacityValue = 1;
            this.opacityValueInfos = 0.4;
            this.showScoreTable = true;
        }
    }

    changeOpacity2() {
        if (this.opacityValueInfos == 0.4) {
            this.opacityValueInfos = 1;
            this.opacityValue = 0.4;
            this.showScoreTable = false;
        }
    }

    scrollToTop() {
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }
}
