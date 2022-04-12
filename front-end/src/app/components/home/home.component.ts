import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import Entry from 'src/app/model/Entry';
import Sector from 'src/app/model/Sector';
import { EntryService } from 'src/app/services/entry.service';
import { SectorService } from 'src/app/services/sector.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  
  submitted: boolean = false;
  title: string = 'Create An Entry';
  buttonMessage: string = 'Save';
  nodes: any = [];
  form!: FormGroup;
  sectorsList:Sector[] =[];


  constructor(
     private formBuilder: FormBuilder,
     private sectorService: SectorService,
     private entryService: EntryService
  ) {}

  ngOnInit(): void {

    this.getAllSectors();

    //initialising our form with validators
    this.form = this.formBuilder.group({
      id: [''],
      name: ['', [Validators.required, Validators.minLength(2)]],
      agreeToTerms: [false, Validators.requiredTrue],
      sectors: [[], Validators.required],
    });

  }

  get formControl() {
    return this.form.controls;
  }


  getAllSectors(){
    return this.sectorService.getAllSectors().subscribe(
      data => {
        this.nodes = this.constructTreeData(data);
      },
      error => {
        //process error here
        console.log('Could Not fullfill the request');
        console.log(error.message);

      }
    )
  }

  createEntry(data:any){
    return this.entryService.createEntry(data).subscribe(
      response => {
        if(response.status == 201){
            this.setFormValues(response.body)
            this.title= 'Update an Entry';
            this.buttonMessage ='Update'
        }      
        console.log(data);
      },
      error =>{
        //process error here
        console.log('Could Not fullfill the request');
        console.log(error.message);
      }
    )
  }

  updateEntry(data:any){
    return this.entryService.updateEntry(data).subscribe(
      response => {
        if(response.status == 200){
            this.setFormValues(response.body)
        }      
        console.log(data);
      },
      error =>{
        //process error here
          console.log('Could Not fullfill the request');
          console.log(error);
      }
    )
  }

  onSubmit() {
    this.submitted = true;
    if (this.form.valid) {
      if(this.form.get('id')?.value)
       this.updateEntry(this.form.value);
      else
        this.createEntry(this.form.value);

    }
    this.onReset();
  }

  onReset() {
    this.submitted = false;
    this.form.reset();
    this.title= 'Create an Entry';
  }

  setFormValues(entry: Entry){
    console.log(this.constructSectorsId(entry));
    this.form.patchValue(
      {
        id: entry.id,
        name:entry.name,
        agreeToTerms: entry.agreeToTerms,
        sectors: this.constructSectorsId(entry)
      }
    )
  }

  //utility method use to convert Sectors object into nodes structure
  constructTreeData(data: Sector[]): any {
    return data.map((sector) => {
      let node = sector.subSectors?.length
        ? {
            title: sector.name,
            key: sector.id,
            children: this.constructTreeData(sector.subSectors),
          }
        : {
            title: sector.name,
            key: sector.id,
            isLeaf: true,
          };
      return node;
    });
  }
  //utility method use to convert Sectors object to array of ids
  constructSectorsId(data: Entry): any {
    let ids = data?.sectors.map(sector => sector.id);
    return ids;
  }
}
