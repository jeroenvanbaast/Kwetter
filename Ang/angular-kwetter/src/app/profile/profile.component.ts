import {Component, OnInit} from '@angular/core';
import {Profile} from '../models/profile'
import {User} from '../models/user';
import {ProfileService} from "../services/profileService";
import {KwetService} from "../services/kwetService";
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
  message : string;

  constructor(private route: ActivatedRoute, private profileSerivce : ProfileService, private kwetService : KwetService) {
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

  sendKwet(){
  this.kwetService.sendKwet(this.profile,this.message);
  }

}
