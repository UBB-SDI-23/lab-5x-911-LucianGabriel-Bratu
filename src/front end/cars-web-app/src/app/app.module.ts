import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './common/home/home.component';
import { OverviewComponent } from './features/dealerships/components/overview/overview.component';
import { StatisticsoverviewComponent } from './features/statistics/components/statisticsoverview/statisticsoverview.component';
import { HttpClientModule } from '@angular/common/http';
import { AddComponent } from './features/dealerships/add/add.component';
import { FormsModule } from '@angular/forms';
import { UpdateComponent } from './features/dealerships/update/update.component';
import { DeleteComponent } from './features/dealerships/delete/delete.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    OverviewComponent,
    StatisticsoverviewComponent,
    AddComponent,
    UpdateComponent,
    DeleteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
