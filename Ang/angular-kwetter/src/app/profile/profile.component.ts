import {Component, OnInit} from '@angular/core';
import {Profile} from '../models/profile';
import {User} from '../models/user';
import {ProfileService} from '../services/profileService';
import {KwetService} from '../services/kwetService';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../services/userService';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userName: string;
  profile: Profile;
  followers: Profile[];
  user: User;
  userProfileId: string;
  editMode: boolean;
  message: string;
  following: boolean;

  constructor(private route: ActivatedRoute, private profileSerivce: ProfileService, private userService: UserService,
              private kwetService: KwetService, private router: Router) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.userName = params['username'];
    });
    if (this.userName == null) {
      this.userName = localStorage.getItem('profileName');
    }
    this.getinfo(this.userName);
    this.userProfileId = localStorage.getItem('userPorfileId');
  }

  getinfo(name: string) {
    let id: string;
    this.profileSerivce.getPorfile(name).subscribe(data => {
      if (data != null) {
        this.profile = data;
        id = String(data.id);
        this.profileSerivce.getFollowers(String(id)).subscribe((output => {
          if (output != null) {
            this.followers = output;
            for (const profile of output) {
              if (profile.id.toString() === this.userProfileId) {
              this.following = true;
            }}
          }
        }));
      }
    });
    this.userService.getUserByProfileName(name).subscribe(data => {
      if (data != null) {
        this.user = data;
      }
    });
  }

  sendKwet() {
    this.kwetService.sendKwet(this.profile, this.message).subscribe(data => {
      if (data != null) {
        this.profile.kwets.push(data);
      }
    });

  }

  follow() {
    this.profileSerivce.follow(this.userProfileId, String(this.profile.id)).subscribe(data => {
      if (data != null) {
        this.followers = data.following;
        this.following = true;
      }
    });
  }

  unfollow() {
    this.profileSerivce.unfollow(this.userProfileId, String(this.profile.id)).subscribe(data => {
      if (data != null) {
        this.followers = data.following;
        this.following = false;
      }
    });
  }

  likeKwet(kwetId: string) {
    this.profileSerivce.likeKwet(this.userProfileId, kwetId).subscribe();
  }

  editModeChange() {
    this.editMode = !this.editMode;
  }

  updateProfile() {
    this.profileSerivce.updateProfile(this.profile.name, this.profile.bio, this.profile.locatie, this.profile.website,
      this.profile.profilePicture).subscribe(data => {
      if (data != null) {
        this.profile = data;
      }
    });
    this.editMode = false;
  }

  goToProfile(name: string) {
    this.router.navigate(['/profile', name]);
    this.getinfo(name);
  }
}
