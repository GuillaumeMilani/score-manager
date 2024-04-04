import {ControlValueAccessor, FormControl, NG_VALUE_ACCESSOR, ReactiveFormsModule} from "@angular/forms";
import {Component} from "@angular/core";
import {MatFormField, MatInput, MatLabel} from "@angular/material/input";
import {merge} from "rxjs";

@Component({
  selector: 'duration-seconds',
  template: `
    <!--      <mat-label>Duration</mat-label>-->
    <mat-form-field>
      <input type="number" matInput [formControl]="minutes" min="0" max="59">
    </mat-form-field>
    <span>:</span>
    <mat-form-field>
      <input type="number" matInput [formControl]="seconds" min="0" max="59">
    </mat-form-field>
  `,
  standalone: true,
  imports: [
    MatInput,
    ReactiveFormsModule,
    MatLabel,
    MatFormField
  ],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: DurationSecondsComponent
    }
  ]
})
export class DurationSecondsComponent implements ControlValueAccessor {
  minutes = new FormControl<number | null>(null);
  seconds = new FormControl<number | null>(null);

  durationToSeconds(minutes: number | null, seconds: number | null): number | null {
    if (minutes === null && seconds === null) {
      return null;
    }

    return (minutes || 0) * 60 + (seconds || 0);
  }

  secondsToDuration(seconds: number | null): { minutes: number | null, seconds: number | null } {
    if (seconds === null) {
      return {minutes: null, seconds: null};
    }

    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return {minutes, seconds: remainingSeconds};
  }

  writeValue(value: number): void {
    var {minutes, seconds} = this.secondsToDuration(value);
    this.minutes.patchValue(minutes);
    this.seconds.patchValue(seconds);
  }

  registerOnChange(fn: any): void {
    // Register when minutes or seconds change
    merge(
      this.minutes.valueChanges,
      this.seconds.valueChanges
    )
      .subscribe(() => {
        fn(this.durationToSeconds(this.minutes.value, this.seconds.value));
      });
  }

  registerOnTouched(fn: any): void {
  }

  setDisabledState?(isDisabled: boolean): void {
    if (isDisabled) {
      this.minutes.disable();
      this.seconds.disable()
    } else {
      this.minutes.enable();
      this.seconds.enable();
    }
  }
}
