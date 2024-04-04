import {AfterViewInit, Component, ElementRef, OnDestroy, ViewChild} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ScoreForm, ScoresService} from "../../../api";
import {BehaviorSubject, filter, map, Subscription, switchMap} from "rxjs";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmationDialogComponent} from "./confirmation-dialog.component";

@Component({
  selector: 'app-scores-form',
  templateUrl: './scores-form.component.html',
  styleUrls: ['./scores-form.component.scss']
})
export class ScoresFormComponent implements OnDestroy, AfterViewInit {
  form = this.fb.group({
    id: new FormControl<number | undefined>(undefined, {nonNullable: true}),
    title: new FormControl('', {validators: [Validators.required], nonNullable: true}),
    composer: new FormControl('', {nonNullable: true}),
    duration: new FormControl<number | undefined>(undefined, {nonNullable: true}),
    year: new FormControl<number | undefined>(undefined, {nonNullable: true}),
  })

  isEdit$ = new BehaviorSubject(false);
  private routeSubscription: Subscription;

  @ViewChild('title') title!: ElementRef;

  constructor(private fb: FormBuilder, private scoresService: ScoresService, private router: Router, private route: ActivatedRoute, private dialog: MatDialog) {
    // Load existing score if id in path
    this.routeSubscription = this.route.params
      .pipe(
        map(params => {
            const id = params['id'];
            this.isEdit$.next(id !== undefined);
            return id;
          }
        ),
        filter(id => id !== undefined),
        switchMap(id => this.scoresService.getScoreById(Number(id)))
      )
      .subscribe(score => {
        this.form.patchValue(score);
      });
  }

  ngAfterViewInit(): void {
    this.title.nativeElement.focus();
  }

  ngOnDestroy() {
    this.routeSubscription.unsubscribe();
  }

  save() {
    if (!this.form.valid) {
      return;
    }
    const score: ScoreForm = this.form.getRawValue();

    if (this.isEdit$.value) {
      this.scoresService.updateScoreById(Number(this.form.value.id), score).subscribe(() => {
        this.router.navigate(['/scores']);
      });
    } else {
      this.scoresService.addScore(score).subscribe(() => {
        this.router.navigate(['/scores']);
      });
    }
  }

  delete() {
    if (!this.isEdit$.value) {
      return;
    }

    this.dialog.open(ConfirmationDialogComponent).afterClosed().subscribe(result => {
      if (result) {
        this.scoresService.deleteScoreById(Number(this.form.value.id)).subscribe(() => {
          this.router.navigate(['/scores']);
        });
      }
    });
  }
}
