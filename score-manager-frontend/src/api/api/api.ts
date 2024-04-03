export * from './instruments.service';
import { InstrumentsService } from './instruments.service';
export * from './scores.service';
import { ScoresService } from './scores.service';
export const APIS = [InstrumentsService, ScoresService];
