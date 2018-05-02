import {Component, OnInit} from '@angular/core';
import {Profile} from '../models/profile'
import {User} from '../models/user';
import {ProfileService} from "../services/profileService";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userName : string;
  profile : Profile;
  user : User;

  constructor(private route: ActivatedRoute, private profileSerivce : ProfileService) {
    window.console.log('FROM constructor()');
  }

  ngOnInit() {
    this.route.params.subscribe(params => { this.userName = params['username']; });
    this.profileSerivce.getPorfile(this.userName).subscribe(data => {
      if (data != null) {
        this.profile = data;
      }
    });
    window.console.log('FROM ngOnInit()');
  }

}
