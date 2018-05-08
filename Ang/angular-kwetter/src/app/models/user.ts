import {Profile} from './profile';
import {AccountType} from './accounttype'

export class User {
  id: number;
  userName: string;
  following: Profile[];
  token : string;
  profile: Profile;
  accountType: AccountType;

  constructor(id: number, userName: string, profile: Profile, accountType: AccountType) {
    this.id = id;
    this.accountType = accountType;
    this.userName = userName;
    this.profile = profile;
  }
}
