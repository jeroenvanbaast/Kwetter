import {Component, OnInit} from '@angular/core';
import {Profile} from '../models/profile'

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: Profile = {
    id: 1,
    name: 'Windstorm',
    profilePicture: '',
    bio: '',
    locatie: '',
    website: ''
  };
  title = 'Profile';
  hero = 'Windstorm';

  constructor() {
  }

  ngOnInit() {
  }

}
