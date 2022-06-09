import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./security/authentication.service";
import {Router} from "@angular/router";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Hardware App';
  currentLanguage: string = '';

  constructor(
    public authenticationService: AuthenticationService,
    private router: Router,
    private translate: TranslateService
  ) {
    translate.setDefaultLang('hr');
    translate.use('hr');
  }

  ngOnInit(): void {
    this.setLanguage();
  }

  setLanguage() {
    if (this.translate.currentLang === 'hr') {
      this.translate.get('language.croatian')
        .subscribe(language => this.currentLanguage = language);
    } else if (this.translate.currentLang === 'en') {
      this.translate.get('language.english')
        .subscribe(language => this.currentLanguage = language);
    } else if (this.translate.currentLang === 'sl') {
      this.translate.get('language.slovenian')
        .subscribe(language => this.currentLanguage = language);
    } else {
      throw Error('Language not recognized!');
    }
  }

  onLanguageChange(lang: string) {
    this.translate.use(lang)
      .subscribe(langChanged => this.setLanguage())
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']).then();
  }

}
