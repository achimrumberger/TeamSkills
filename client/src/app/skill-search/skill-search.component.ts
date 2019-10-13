import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { UserService } from '../shared/user/user.service';

@Component({
  selector: 'app-skill-search',
  templateUrl: './skill-search.component.html',
  styleUrls: ['./skill-search.component.css']
})
export class SkillSearchComponent implements OnInit {

  users: Array<any>;
  sub: Subscription;
  skillLevel: any;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private userService: UserService) { }

  ngOnInit() {
    this.userService.getUsersWithskillLevel({}).subscribe(data => {
      this.users=data;
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
    this.gotoList();
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }

  save(form: NgForm) {
    this.userService.getUsersWithskillLevel(form).subscribe(data => {
      this.users=data;
    }, error => console.error(error));
  }

}
