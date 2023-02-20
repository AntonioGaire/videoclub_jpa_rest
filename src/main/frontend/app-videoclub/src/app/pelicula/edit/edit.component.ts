import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PeliculaService} from "../pelicula.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Pelicula} from "../pelicula";

@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {

  id: number = 0;
  pelicula: Pelicula = { idPelicula: 0, titulo: "VOID", descripcion: "VOID", ultimaActualizacion: "1970-01-01"};
  form: FormGroup =   new FormGroup({
    pelicula:  new FormControl('', [ Validators.required, Validators.pattern('^[a-zA-ZÁáÀàÉéÈèÍíÌìÓóÒòÚúÙùÑñüÜ \-\']+') ])
  });

  constructor(
    public peliculaService: PeliculaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['idPelicula'];
    this.peliculaService.find(this.id).subscribe((data: Pelicula)=>{
      this.pelicula = data;

      this.form.get('nombre')?.setValue(this.pelicula.titulo);


    });
  }

  get f(){
    return this.form.controls;
  }

  submit(){
    console.log(this.form.value);
    this.peliculaService.update(this.id, this.form.value).subscribe(res => {
      console.log('Categroría actualizada satisfactoriamente!');
      this.router.navigateByUrl('pelicula/index').then();
    })
  }

}
