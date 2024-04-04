import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'minutesSeconds',
  standalone: true
})
export class MinuteSecondsPipe implements PipeTransform {
  transform(value: number): string {
    if (value === undefined || value === null || isNaN(value)) {
      return '';
    }

    const minutes: number = Math.floor(value / 60);
    const seconds: number = value - minutes * 60;
    var minutesString = minutes.toString();
    var secondsString = seconds.toString();

    if (minutes < 10) {
      minutesString = '0' + minutesString;
    }
    if (seconds < 10) {
      secondsString = '0' + secondsString;
    }

    return `${minutesString}:${secondsString}`;
  }
}
