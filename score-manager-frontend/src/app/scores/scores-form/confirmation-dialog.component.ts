import {Component} from "@angular/core";
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import {MatButton} from "@angular/material/button";

@Component({
  selector: 'confirmation-dialog',
  standalone: true,
  imports: [
    MatDialogContent,
    MatDialogActions,
    MatDialogClose,
    MatDialogTitle,
    MatButton
  ],
  template: `
    <h2 mat-dialog-title>Are you sure ?</h2>
    <mat-dialog-content>Please confirm.</mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button mat-dialog-close>Cancel</button>
      <!-- The mat-dialog-close directive optionally accepts a value as a result for the dialog. -->
      <button mat-button [mat-dialog-close]="true" color="warn">Confirm</button>
    </mat-dialog-actions>
  `
})
export class ConfirmationDialogComponent {
}
