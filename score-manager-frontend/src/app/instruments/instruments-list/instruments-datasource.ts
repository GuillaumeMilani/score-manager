import {Instrument, InstrumentsService} from "../../../api";
import {Datasource} from "../../datasource";

export class InstrumentsDatasource extends Datasource<Instrument> {
  constructor(service: InstrumentsService) {
    super(
      (page, size, sort, order, filter) =>
        // service.getAllInstruments(page, size, sort, order, filter)
        service.getAllInstruments()
    );
  }
}
