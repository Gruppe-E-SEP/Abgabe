import { Component } from '@angular/core';

@Component({
  selector: 'app-add-liga-form',
  templateUrl: './add-liga-form.component.html',
  styleUrls: ['./add-liga-form.component.scss']
})
export class AddLigaFormComponent {

  onSubmit(value: any){
  console.log("Die Liga wurde erstellt");
}

}
