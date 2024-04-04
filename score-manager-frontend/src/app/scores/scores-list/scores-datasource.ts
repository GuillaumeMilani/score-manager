import {Score, ScoresService} from "../../../api";
import {Datasource} from "../../datasource";

export class ScoresDatasource extends Datasource<Score> {
  constructor(service: ScoresService) {
    super(
      (page, size, sort, order, filter) =>
        service.getAllScores(page, size, sort, order, filter)
    );
  }
}
