import {Kwet} from './kwet';

export class Profile{
  id: number;
  name : string;
  profilePicture : string;
  bio : string;
  locatie : string;
  website : string;

  kwets : Kwet[];
  heartedKwets : Kwet[];
  following : Profile[];

}
