import { DataService } from './../services/data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  games = [];

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.dataService.getGames().subscribe((data: any[])=>{
      console.log(data);
      this.games = data;
    });
  }

}
