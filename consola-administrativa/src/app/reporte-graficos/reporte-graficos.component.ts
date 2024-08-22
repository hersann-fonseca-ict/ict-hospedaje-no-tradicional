import { Component, OnInit } from "@angular/core";
import {
  AccumulationChart, AccumulationDataLabel,
  AccumulationLegend, ChartComponent, PieSeries
} from "@syncfusion/ej2-angular-charts";
import { CommonService } from "app/services/common/common.service";

import { Router } from "@angular/router";
import { MsalService } from "@azure/msal-angular";
import { ReporteGraficosModel } from "app/services/Modelos/reporte-graficos-model";
import { OAuthHelperService } from "app/services/OAuthHerlper/oauth-helper.service";
import { ReportesService } from "app/services/Reportes/reportes.service";
import { Subject } from "rxjs";
AccumulationChart.Inject(
  AccumulationChart,
  AccumulationLegend,
  PieSeries,
  AccumulationDataLabel
);

@Component({
  selector: "app-reporte-graficos",
  templateUrl: "./reporte-graficos.component.html",
  styleUrls: ["./reporte-graficos.component.css"],
})
export class ReporteGraficosComponent implements OnInit {
  public piedata: Object[];
  public datalabel: Object;
  public piedataProvincias: Object[];
  public piedataAlojamiento: Object[];
  public piedataPerfiles: Object[];
  public map: Object = "fill";
  public legendSettings: Object;
  public cargandoGraficos: boolean = true;
  public tooltip: Object;
  public title: String;
  public chartProvincias: ChartComponent;
  list_Header: any = [];
  public enableBorderOnMouseMove: boolean;
  public datosProvincia: ReporteGraficosModel[];

  public titleStyle: Object;
  public CantidadPerfiles: number = 0;
  public CantidadPerfilesInmuebles: number;
  public fecha: any;
  dtTrigger: Subject<any> = new Subject();
  dtOptions: DataTables.Settings = {};
  constructor(
    private authService: MsalService,
    private oauth: OAuthHelperService,
    private reporteService: ReportesService,
    private common: CommonService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.common._setLoading(true);
    this.fecha = new Date();
    this.cargandoGraficos = true;
    this.obtenerReporteAlojamiento();
    this.obtenerReportePerfiles();
    this.loadDataProvincias();
    setTimeout(() => {
      this.cargandoGraficos = false;
      this.generarGraficoProvincias();
      this.generarGraficoAlojamiento();
      this.generarGraficoPerfiles();
    }, 18000);

    this.obtenerCantidadPerfiles();
    this.obtenerPerfilesConInmuebles();
  }

  generarGraficoPerfiles() {
    let chart: AccumulationChart = new AccumulationChart(
      {
        height: "400",
        title: "Cantidad de usuarios por tipo de servicio",
        tooltip: { enable: true, format: "${point.tooltip}" },

        //Initializing Series
        series: [
          {
            dataSource: this.piedataPerfiles,
            xName: "nombre",
            name: "nombre",
            yName: "valor",
            dataLabel: {
              visible: true,
              position: "Outside",
              name: "text",
              font: { fontWeight: "600" },
            },
            border: { color: "white", width: 2 },
          },
        ],

        legendSettings: {
          visible: true,
          position: "Bottom",
        },
        enableSmartLabels: true,
        enableAnimation: true,
      },
      "#Chart3"
    );
    this.common._setLoading(false);
  }

  generarGraficoAlojamiento() {
    let chart: AccumulationChart = new AccumulationChart(
      {
        height: "500",
        title: "Distribución de inmuebles por tipo de hospedaje",
        tooltip: { enable: true, format: "${point.tooltip}" },
        //Initializing Series
        series: [
          {
            dataSource: this.piedataAlojamiento,
            xName: "nombre",
            name: "nombre",
            yName: "valor",
            dataLabel: {
              visible: true,
              position: "Outside",
              name: "text",
              font: { fontWeight: "600" },
            },
            // pointColorMapping: 'fill',
            border: { color: "white", width: 2 },
          },
        ],
        legendSettings: {
          visible: true,
          position: "Bottom",
          height: "150",
        },
        enableSmartLabels: true,
        enableAnimation: true,
      },
      "#Chart2"
    );
  }

  generarGraficoProvincias() {
    let chart: AccumulationChart = new AccumulationChart(
      {
        height: "400",
        title: "Distribución de inmuebles por provincia",
        tooltip: { enable: true, format: "${point.tooltip}" },
        //Initializing Series
        series: [
          {
            dataSource: this.piedataProvincias,
            xName: "nombre",
            name: "nombre",
            yName: "valor",
            dataLabel: {
              visible: true,
              position: "Outside",
              name: "text",
              font: { fontWeight: "600" },
            },
            // pointColorMapping: 'fill',
            border: { color: "white", width: 2 },
          },
        ],
        legendSettings: {
          visible: true,
          position: "Bottom",
        },
        enableSmartLabels: true,
        enableAnimation: true,
      },
      "#Chart"
    );
  }

  loadDataProvincias() {
    this.reporteService
      .obtenerReporteProvincias()
      .then(async (datos) => {
        datos.forEach((element) => {
          let data = {
            valor: element.valor,
            nombre: element.nombre,
          };

          this.list_Header.push(data);
        });
        this.piedataProvincias = await this.list_Header;
        this.list_Header = [];
      })
      .catch((err) => {
        this.common._setLoading(false);
        alert(
          "Ocurrió un error con los servicios. Contacte con el administrador"
        );
      });
  }

  obtenerReporteAlojamiento() {
    this.reporteService
      .obtenerReporteAlojamiento().then(async (datos) => {
        console.log('Ricardoo...');
        datos.forEach((element) => {
          let data = {
            valor: element.valor,
            nombre: element.nombre,
          };

          this.list_Header.push(data);
        });

        this.piedataAlojamiento = await this.list_Header;

        this.list_Header = [];
      })
      .catch((err) => {
        this.common._setLoading(false);
        alert("Ocurrió un error con los servicios. Contacte con el administrador");
      });
  }

  obtenerReportePerfiles() {
    this.reporteService
      .obtenerReportePerfiles()
      .then(async (datos) => {
        datos.forEach((element) => {
          let data = {
            valor: element.valor,
            nombre: element.nombre,
          };

          this.list_Header.push(data);
        });

        this.piedataPerfiles = await this.list_Header;

        this.list_Header = [];
      })
      .catch((err) => {
        this.common._setLoading(false);
        alert("Ocurrió un error con los servicios. Contacte con el administrador");
      });
  }

  obtenerCantidadPerfiles() {
    this.reporteService
      .obtenerCantidadPerfiles()
      .then((datos) => {
        this.CantidadPerfiles = Number(datos);
      })
      .catch((err) => {
        this.common._setLoading(false);
        alert("Ocurrió un error con los servicios. Contacte con el administrador");
      });
  }

  obtenerPerfilesConInmuebles() {
    this.reporteService
      .obtenerPerfilesConInmuebles().then((datos) => {
        this.CantidadPerfilesInmuebles = Number(datos);
      })
      .catch((err) => {
        this.common._setLoading(false);
        alert("Ocurrió un error con los servicios. Contacte con el administrador");
      });
  }

  PDFExport() {
    var docHead = document.head.outerHTML;
    var printContents = document.getElementById("documento").outerHTML;
    var winAttr = "location=yes, statusbar=no, menubar=no, titlebar=no, toolbar=no,dependent=no, width=900, height=600, resizable=yes, screenX=200, screenY=200, personalbar=no, scrollbars=yes";
    var newWin = window.open("", "_blank", winAttr);
    var writeDoc = newWin.document;
    writeDoc.write(
      "<!doctype html><html>" +
        docHead +
        '<body onLoad="window.print()">' +
        printContents +
        "</body></html>"
    );
    writeDoc.close();
    newWin.focus();
  }
}
