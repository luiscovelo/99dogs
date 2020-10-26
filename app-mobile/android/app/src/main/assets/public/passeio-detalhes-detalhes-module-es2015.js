(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["passeio-detalhes-detalhes-module"],{

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/passeio/detalhes/detalhes.page.html":
/*!*******************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/passeio/detalhes/detalhes.page.html ***!
  \*******************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<ion-header>\n  <ion-toolbar>\n    <ion-title>detalhes</ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content>\n  <ion-card>\n    <ion-card-header>\n      <ion-card-title>\n        {{cliente.nome}}\n      </ion-card-title>\n      <ion-card-subtitle>\n        Passeio #{{passeio.id}} dia {{passeio.datahora}}\n      </ion-card-subtitle>\n      <ion-card-subtitle>\n        Status: {{passeio.status}}\n      </ion-card-subtitle>\n    </ion-card-header>\n  \n    <ion-card-content *ngIf=\"!passeioFinalizado && passeio.status != 'Finalizado'\">\n      <ion-button *ngIf=\"!passeioAtivo\" color=\"success\" size=\"small\" (click)=\"iniciarPasseio()\">Iniciar Passeio</ion-button>\n      <ion-button *ngIf=\"passeioAtivo\" color=\"danger\" size=\"small\" (click)=\"finalizarPasseio()\">Finalizar Passeio</ion-button>\n    </ion-card-content>\n  </ion-card>\n\n</ion-content>\n");

/***/ }),

/***/ "./src/app/passeio/detalhes/detalhes-routing.module.ts":
/*!*************************************************************!*\
  !*** ./src/app/passeio/detalhes/detalhes-routing.module.ts ***!
  \*************************************************************/
/*! exports provided: DetalhesPageRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DetalhesPageRoutingModule", function() { return DetalhesPageRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _detalhes_page__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./detalhes.page */ "./src/app/passeio/detalhes/detalhes.page.ts");




const routes = [
    {
        path: '',
        component: _detalhes_page__WEBPACK_IMPORTED_MODULE_3__["DetalhesPage"]
    }
];
let DetalhesPageRoutingModule = class DetalhesPageRoutingModule {
};
DetalhesPageRoutingModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]],
    })
], DetalhesPageRoutingModule);



/***/ }),

/***/ "./src/app/passeio/detalhes/detalhes.module.ts":
/*!*****************************************************!*\
  !*** ./src/app/passeio/detalhes/detalhes.module.ts ***!
  \*****************************************************/
/*! exports provided: DetalhesPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DetalhesPageModule", function() { return DetalhesPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/__ivy_ngcc__/fesm2015/forms.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/__ivy_ngcc__/fesm2015/ionic-angular.js");
/* harmony import */ var _detalhes_routing_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./detalhes-routing.module */ "./src/app/passeio/detalhes/detalhes-routing.module.ts");
/* harmony import */ var _detalhes_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./detalhes.page */ "./src/app/passeio/detalhes/detalhes.page.ts");







let DetalhesPageModule = class DetalhesPageModule {
};
DetalhesPageModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [
            _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_4__["IonicModule"],
            _detalhes_routing_module__WEBPACK_IMPORTED_MODULE_5__["DetalhesPageRoutingModule"]
        ],
        declarations: [_detalhes_page__WEBPACK_IMPORTED_MODULE_6__["DetalhesPage"]]
    })
], DetalhesPageModule);



/***/ }),

/***/ "./src/app/passeio/detalhes/detalhes.page.scss":
/*!*****************************************************!*\
  !*** ./src/app/passeio/detalhes/detalhes.page.scss ***!
  \*****************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3Bhc3NlaW8vZGV0YWxoZXMvZGV0YWxoZXMucGFnZS5zY3NzIn0= */");

/***/ }),

/***/ "./src/app/passeio/detalhes/detalhes.page.ts":
/*!***************************************************!*\
  !*** ./src/app/passeio/detalhes/detalhes.page.ts ***!
  \***************************************************/
/*! exports provided: DetalhesPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "DetalhesPage", function() { return DetalhesPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _services_passeio_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/passeio.service */ "./src/app/services/passeio.service.ts");
/* harmony import */ var _ionic_native_geolocation_ngx__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ionic-native/geolocation/ngx */ "./node_modules/@ionic-native/geolocation/__ivy_ngcc__/ngx/index.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");





let DetalhesPage = class DetalhesPage {
    constructor(geolocation, activatedRoute, router, passeioService) {
        this.geolocation = geolocation;
        this.activatedRoute = activatedRoute;
        this.router = router;
        this.passeioService = passeioService;
        this.passeio = {};
        this.cliente = {};
        this.passeioAtivo = false;
        this.passeioFinalizado = false;
    }
    iniciarPasseio() {
        this.alterarStatusPasseio();
        let watch = this.geolocation.watchPosition();
        watch.subscribe((data) => {
            this.latitude = data["coords"]["latitude"];
            this.longitude = data["coords"]["longitude"];
            this.passeioService.postLocalizacaoPasseio(this.latitude, this.longitude, this.passeio["id"]).subscribe();
        });
    }
    finalizarPasseio() {
        this.alterarStatusPasseio();
        this.passeioFinalizado = !this.passeioFinalizado;
        this.passeioService.putAlterarStatus("Finalizado", this.passeioId).subscribe(response => {
            if (response) {
                location.reload();
            }
        });
    }
    alterarStatusPasseio() {
        this.passeioAtivo = !this.passeioAtivo;
    }
    ngOnInit() {
        this.activatedRoute.params.forEach(param => this.passeioId = param['id']);
        try {
            this.passeioService.getDetalhes(this.passeioId).subscribe(response => {
                this.passeio = response;
                this.cliente = response["cliente"]["pessoa"];
            });
        }
        catch (error) {
            console.log(error);
        }
    }
};
DetalhesPage.ctorParameters = () => [
    { type: _ionic_native_geolocation_ngx__WEBPACK_IMPORTED_MODULE_3__["Geolocation"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__["ActivatedRoute"] },
    { type: _angular_router__WEBPACK_IMPORTED_MODULE_4__["Router"] },
    { type: _services_passeio_service__WEBPACK_IMPORTED_MODULE_2__["PasseioService"] }
];
DetalhesPage = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-detalhes',
        template: Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"])(__webpack_require__(/*! raw-loader!./detalhes.page.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/passeio/detalhes/detalhes.page.html")).default,
        styles: [Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"])(__webpack_require__(/*! ./detalhes.page.scss */ "./src/app/passeio/detalhes/detalhes.page.scss")).default]
    })
], DetalhesPage);



/***/ })

}]);
//# sourceMappingURL=passeio-detalhes-detalhes-module-es2015.js.map