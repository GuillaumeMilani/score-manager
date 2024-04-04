import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {InstrumentsService} from "../../../api";
import {InstrumentsDatasource} from "./instruments-datasource";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {FormControl} from "@angular/forms";
import {debounceTime} from "rxjs";

@Component({
  selector: 'app-instruments-list',
  templateUrl: './instruments-list.component.html',
  styleUrls: ['./instruments-list.component.scss']
})
export class InstrumentsListComponent implements AfterViewInit {
  dataSource: InstrumentsDatasource;
  displayedColumns = ['name', 'actions'];

  @ViewChild(MatSort) sort!: MatSort;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  searchForm = new FormControl<string>('');

  constructor(instrumentsService: InstrumentsService) {
    this.dataSource = new InstrumentsDatasource(instrumentsService);
    this.searchForm.valueChanges
      .pipe(debounceTime(300))
      .subscribe(search => this.search(search || ''));
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.dataSource.refresh();
  }

  search(search: string) {
    this.dataSource.filter = search;
  }
}
