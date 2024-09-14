export interface BaseModel {
  id: string;
  content?: Map<string, BaseModel>;
}
