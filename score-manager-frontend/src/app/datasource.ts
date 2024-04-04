import {MatTableDataSource, MatTableDataSourcePaginator} from "@angular/material/table";
import {BehaviorSubject, catchError, finalize, Observable} from "rxjs";
import {MatSort} from "@angular/material/sort";

export interface Page<T> {
  content?: T[];
  totalElements?: number;
}

export type LoadFunction<T> = (page: number | undefined, size: number | undefined, sort: string | undefined, order: string | undefined, filter: string | undefined) => Observable<Page<T>>

export abstract class Datasource<T> extends MatTableDataSource<T> {
  private dataSubject = new BehaviorSubject<T[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);

  public loading$ = this.loadingSubject.asObservable();
  public total$ = new BehaviorSubject<number>(0);

  constructor(private load: LoadFunction<T>) {
    super();
  }

  override set sort(sort: MatSort | null) {
    super.sort = sort;
    super.sort?.sortChange.subscribe((sort) => this.loadData(super.sort, super.paginator, super.filter));
  }

  override set paginator(paginator: any) {
    super.paginator = paginator;
    super.paginator?.page.subscribe(() => this.loadData(super.sort, super.paginator, super.filter));
  }

  override set filter(filter: string) {
    super.filter = filter;
    this.loadData(super.sort, super.paginator, super.filter);
  }

  override connect(): BehaviorSubject<T[]> {
    return this.dataSubject;
  }

  override disconnect() {
    this.dataSubject.complete();
    this.loadingSubject.complete();
  }

  refresh() {
    this.loadData(super.sort, super.paginator, super.filter);
  }

  loadData(sort: MatSort | null, paginator: MatTableDataSourcePaginator | null, filter: string) {
    this.loadingSubject.next(true);
    this.load(paginator?.pageIndex, paginator?.pageSize, sort?.active, sort?.direction || 'asc', filter || undefined)
      .pipe(
        catchError(() => []),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(scores => {
        this.dataSubject.next(scores.content!);
        this.total$.next(scores.totalElements!);
      });
  }
}
