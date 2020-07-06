import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { MemorytripSharedModule } from 'app/shared/shared.module';
import { MemorytripCoreModule } from 'app/core/core.module';
import { MemorytripAppRoutingModule } from './app-routing.module';
import { MemorytripHomeModule } from './home/home.module';
import { MemorytripEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    MemorytripSharedModule,
    MemorytripCoreModule,
    MemorytripHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    MemorytripEntityModule,
    MemorytripAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class MemorytripAppModule {}
