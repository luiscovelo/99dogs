(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["passeio-meus-passeios-meus-passeios-module"],{

/***/ "./node_modules/raw-loader/dist/cjs.js!./src/app/passeio/meus-passeios/meus-passeios.page.html":
/*!*****************************************************************************************************!*\
  !*** ./node_modules/raw-loader/dist/cjs.js!./src/app/passeio/meus-passeios/meus-passeios.page.html ***!
  \*****************************************************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("<ion-header>\n  <ion-toolbar>\n    <ion-title>meus-passeios</ion-title>\n  </ion-toolbar>\n</ion-header>\n\n<ion-content>\n  <ion-list>\n    <ion-item *ngFor=\"let passeio of passeios\">\n      <ion-label>\n        <h2># {{ passeio.id }} | {{ passeio.cliente.pessoa.nome }}</h2>\n        <p> {{ passeio.cliente.pessoa.email }} | {{ passeio.cliente.pessoa.telefone }} </p>\n        <ion-button href=\"/passeio/detalhes/{{passeio.id}}\">Detalhes</ion-button>\n      </ion-label>\n    </ion-item>\n  </ion-list>\n\n</ion-content>");

/***/ }),

/***/ "./src/app/passeio/meus-passeios/meus-passeios-routing.module.ts":
/*!***********************************************************************!*\
  !*** ./src/app/passeio/meus-passeios/meus-passeios-routing.module.ts ***!
  \***********************************************************************/
/*! exports provided: MeusPasseiosPageRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MeusPasseiosPageRoutingModule", function() { return MeusPasseiosPageRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/__ivy_ngcc__/fesm2015/router.js");
/* harmony import */ var _meus_passeios_page__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./meus-passeios.page */ "./src/app/passeio/meus-passeios/meus-passeios.page.ts");




const routes = [
    {
        path: '',
        component: _meus_passeios_page__WEBPACK_IMPORTED_MODULE_3__["MeusPasseiosPage"]
    }
];
let MeusPasseiosPageRoutingModule = class MeusPasseiosPageRoutingModule {
};
MeusPasseiosPageRoutingModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
        exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]],
    })
], MeusPasseiosPageRoutingModule);



/***/ }),

/***/ "./src/app/passeio/meus-passeios/meus-passeios.module.ts":
/*!***************************************************************!*\
  !*** ./src/app/passeio/meus-passeios/meus-passeios.module.ts ***!
  \***************************************************************/
/*! exports provided: MeusPasseiosPageModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MeusPasseiosPageModule", function() { return MeusPasseiosPageModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/__ivy_ngcc__/fesm2015/common.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/__ivy_ngcc__/fesm2015/forms.js");
/* harmony import */ var _ionic_angular__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @ionic/angular */ "./node_modules/@ionic/angular/__ivy_ngcc__/fesm2015/ionic-angular.js");
/* harmony import */ var _meus_passeios_routing_module__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./meus-passeios-routing.module */ "./src/app/passeio/meus-passeios/meus-passeios-routing.module.ts");
/* harmony import */ var _meus_passeios_page__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./meus-passeios.page */ "./src/app/passeio/meus-passeios/meus-passeios.page.ts");







let MeusPasseiosPageModule = class MeusPasseiosPageModule {
};
MeusPasseiosPageModule = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
        imports: [
            _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
            _angular_forms__WEBPACK_IMPORTED_MODULE_3__["FormsModule"],
            _ionic_angular__WEBPACK_IMPORTED_MODULE_4__["IonicModule"],
            _meus_passeios_routing_module__WEBPACK_IMPORTED_MODULE_5__["MeusPasseiosPageRoutingModule"]
        ],
        declarations: [_meus_passeios_page__WEBPACK_IMPORTED_MODULE_6__["MeusPasseiosPage"]]
    })
], MeusPasseiosPageModule);



/***/ }),

/***/ "./src/app/passeio/meus-passeios/meus-passeios.page.scss":
/*!***************************************************************!*\
  !*** ./src/app/passeio/meus-passeios/meus-passeios.page.scss ***!
  \***************************************************************/
/*! exports provided: default */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony default export */ __webpack_exports__["default"] = ("\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3Bhc3NlaW8vbWV1cy1wYXNzZWlvcy9tZXVzLXBhc3NlaW9zLnBhZ2Uuc2NzcyJ9 */");

/***/ }),

/***/ "./src/app/passeio/meus-passeios/meus-passeios.page.ts":
/*!*************************************************************!*\
  !*** ./src/app/passeio/meus-passeios/meus-passeios.page.ts ***!
  \*************************************************************/
/*! exports provided: MeusPasseiosPage */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MeusPasseiosPage", function() { return MeusPasseiosPage; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/__ivy_ngcc__/fesm2015/core.js");
/* harmony import */ var _services_passeio_service__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! ../../services/passeio.service */ "./src/app/services/passeio.service.ts");



let MeusPasseiosPage = class MeusPasseiosPage {
    constructor(passeioService) {
        this.passeioService = passeioService;
        this.passeios = [];
    }
    detalhes(id) {
        console.log(id);
    }
    ngOnInit() {
        try {
            this.passeioService.getMeusPasseios().subscribe(response => {
                this.passeios = response;
            });
        }
        catch (error) {
            console.log(error);
        }
    }
};
MeusPasseiosPage.ctorParameters = () => [
    { type: _services_passeio_service__WEBPACK_IMPORTED_MODULE_2__["PasseioService"] }
];
MeusPasseiosPage = Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"])([
    Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
        selector: 'app-meus-passeios',
        template: Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"])(__webpack_require__(/*! raw-loader!./meus-passeios.page.html */ "./node_modules/raw-loader/dist/cjs.js!./src/app/passeio/meus-passeios/meus-passeios.page.html")).default,
        styles: [Object(tslib__WEBPACK_IMPORTED_MODULE_0__["__importDefault"])(__webpack_require__(/*! ./meus-passeios.page.scss */ "./src/app/passeio/meus-passeios/meus-passeios.page.scss")).default]
    })
], MeusPasseiosPage);



/***/ })

}]);
//# sourceMappingURL=passeio-meus-passeios-meus-passeios-module-es2015.js.map