import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Beneficio } from 'app/services/Beneficios/beneficios';
import { BeneficiosService } from 'app/services/Beneficios/beneficios.service';

@Component({
  selector: 'app-gestionar-beneficios',
  templateUrl: './gestionar-beneficios.component.html',
  styleUrls: ['./gestionar-beneficios.component.css']
})
export class GestionarBeneficiosComponent implements OnInit {
  activoText: string= 'Activo';
  tituloText: string= 'Nuevo ';

  nombreBeneficio: string = '';
  idBeneficio: number;

  nuevoBeneficio: Beneficio = new Beneficio();
  beneficio: Beneficio = new Beneficio();

  constructor(private beneficioService: BeneficiosService,private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams
    .subscribe(params => {
      this.idBeneficio = params['id'];
    });
    if(this.idBeneficio !=null){
    this.beneficioService.obtenerPorId(this.idBeneficio).then( (respuesta: Beneficio) => {
       this.beneficio= respuesta;
       if(this.beneficio.id != null){
        this.modificar(this.beneficio);
      }
      
    }).catch( (err) => alert(err.status == '500' ? 'Ha ocurrido un error, contacte con el administrador de sitio': 'Error inesperado'));
  }
  }
  cancelarCrearBeneficio(){
   
    this.router.navigateByUrl('/beneficios');
  }
  guardarBeneficio(){
    this.nuevoBeneficio.nombreBeneficio = this.nombreBeneficio;
    this.nuevoBeneficio.fechaCreacion = new Date();
    this.nuevoBeneficio.activo = true;
    this.beneficioService.guardarBeneficio(this.nuevoBeneficio).then( (respuesta: Beneficio) => {
    this.nombreBeneficio = '';
    this.nuevoBeneficio = respuesta;
    this.router.navigateByUrl('/beneficios');
    alert( 'Se guardó el beneficio con éxito');
     })
    .catch( (err) => alert(err.status == '409' ? 'Ya existe un beneficio con ese nombre': 'Ha ocurrido un error, contacte con el administrador de sitio'));
  }
  modificar(beneficio: Beneficio){
    this.nuevoBeneficio = beneficio;
    this.nombreBeneficio = beneficio.nombreBeneficio;
    this.activoText = beneficio.activo ? 'Activo' : 'Inactivo';
    this.tituloText= 'Modificar ';

  }

}
