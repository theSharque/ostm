import {BaseModel} from "./base-model";

export interface ProjectModel extends BaseModel {
  name: string;
  owner: string;
  nextId: number;
}
