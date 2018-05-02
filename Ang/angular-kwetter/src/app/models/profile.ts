import {Kwet} from './kwet';

export class Profile {
  id: number;
  name: string;
  profilePicture: string;
  bio: string;
  locatie: string;
  website: string;

  kwets: Kwet[];
  heartedKwets: Kwet[];
  following: Profile[];

  construct(name: string, bio: string, id: number) {
    this.bio = bio;
    this.name = name;
    this.id = id;

  }

}
