import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {ScoresService} from "../../../api";
import {ScoresDatasource} from "./scores-datasource";
import {MatSort} from "@angular/material/sort";
import {MatPaginator} from "@angular/material/paginator";
import {FormControl} from "@angular/forms";
import {debounceTime} from "rxjs";

@Component({
  selector: 'app-scores-list',
  templateUrl: './scores-list.component.html',
  styleUrls: ['./scores-list.component.scss']
})
export class ScoresListComponent implements AfterViewInit {
  dataSource: ScoresDatasource;
  displayedColumns = ['title', 'composer', 'durationSeconds', 'year'];

  @ViewChild(MatSort) sort!: MatSort;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  searchForm = new FormControl<string>('');

  constructor(scoresService: ScoresService) {
    this.dataSource = new ScoresDatasource(scoresService);
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
