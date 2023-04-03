import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './common/home/home.component';
import { AddComponent } from './features/dealerships/add/add.component';
import { OverviewComponent } from './features/dealerships/components/overview/overview.component';
import { DeleteComponent } from './features/dealerships/delete/delete.component';
import { UpdateComponent } from './features/dealerships/update/update.component';
import { StatisticsoverviewComponent } from './features/statistics/components/statisticsoverview/statisticsoverview.component';
import { GetOneComponentComponent } from './features/dealerships/components/get-one-component/get-one-component.component';

const routes: Routes = [
  {
    path: "",
    component: HomeComponent
  },
  {
    path: "dealerships",
    component: OverviewComponent
  },
  {
    path: "statistics",
    component: StatisticsoverviewComponent
  },
  {
    path: "dealerships/add",
    component: AddComponent
  },
  {
    path: "dealerships/update",
    component: UpdateComponent
  },
  {
    path: "dealerships/delete",
    component: DeleteComponent
  },
  {
    path: "dealerships/:id",
    component: GetOneComponentComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
