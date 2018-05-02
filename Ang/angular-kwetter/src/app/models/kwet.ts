import {Profile} from './profile';
import {Hashtag} from './hashtag';

export class Kwet {
  id: number;
  profileName: string;
  placedDate: Date;
  message: string;
  flaged: boolean;
  hashTags: Hashtag[];
  tagged: Profile[];

  constructor(id: number, profileName: string, message: string) {
    this.id = id;
    this.profileName = profileName;
    this.message = message;
  }

}
