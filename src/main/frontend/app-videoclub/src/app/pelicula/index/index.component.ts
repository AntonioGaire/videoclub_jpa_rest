import {Component, OnInit} from '@angular/core';
import { Pelicula} from "../pelicula";
import {PeliculaService} from "../pelicula.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  peliculas: Pelicula[] = [];

  constructor(public peliculaService: PeliculaService) { }

  ngOnInit(): void {
    this.peliculaService.getAll().subscribe((data: Pelicula[])=>{
      this.peliculas= data;
      console.log("PELICULAS", data);
    })
  }

  deletePelicula(id: any){
    this.peliculaService.delete(id).subscribe(res => {
      this.peliculas = this.peliculas.filter(pel => pel.idPelicula !== id);
      console.log('Pelicula id =' + id + ' eliminada satisfactoriamente!');
    })
  }


}

