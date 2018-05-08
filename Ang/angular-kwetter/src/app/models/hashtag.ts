import {Kwet} from './kwet';

export class Hashtag {
  id: number;
  hashtagText: string;
  kwets: Kwet[];

  constructor(id: number, hashtagText: string) {
    this.id = id;
    this.hashtagText = hashtagText;
  }
}
