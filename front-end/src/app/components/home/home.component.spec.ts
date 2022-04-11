import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { NzTreeSelectModule } from 'ng-zorro-antd/tree-select';

import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        NzTreeSelectModule,
      ],
      declarations: [HomeComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('[name : check] - should check if name field is invalid',()=>{
    let name = component.form.controls['name'];
    expect(name.valid).toBeFalsy();
    expect(name.pristine).toBeTruthy();
    expect(name.hasError('required')).toBeTruthy();
    expect(name.hasError('minLength')).toBeFalsy();

    name.setValue("t");

    expect(name.valid).toBeFalsy();

  }
  )

  it('[name : check] - should check if name field is valid',()=>{
    let name = component.form.controls['name'];
    expect(name.valid).toBeFalsy();
    expect(name.pristine).toBeTruthy();
    expect(name.hasError('required')).toBeTruthy();
    expect(name.hasError('minLength')).toBeFalsy();

    name.setValue("Test Name");

    expect(name.valid).toBeTruthy();

  }
  )

  it('[sectors : check] - should check if sectors field is invalid',()=>{
    let sectors = component.form.controls['sectors'];
    expect(sectors.valid).toBeFalsy();
    expect(sectors.pristine).toBeTruthy();
    expect(sectors.hasError('required')).toBeTruthy();

    sectors.setValue([]);

    expect(sectors.valid).toBeFalsy();

  }
  )

  it('[agreeToTerms : check] - should check if agree to terms field is invalid',()=>{
    let agreeToTerms = component.form.controls['agreeToTerms'];
    expect(agreeToTerms.valid).toBeFalsy();
    expect(agreeToTerms.pristine).toBeTruthy();
    expect(agreeToTerms.hasError('required')).toBeTruthy();

    agreeToTerms.setValue(false);

    expect(agreeToTerms.valid).toBeFalsy();
  });

  it('[Form : check] - should check if Form   is invalid',()=>{
    expect(component.form.valid).toBeFalsy();
  });

  it('[Form : check] - should check if Form   is valid',()=>{

    component.form.controls['name'].setValue("Test");
    component.form.controls['sectors'].setValue([1,2]);
    component.form.controls['agreeToTerms'].setValue(true);

    expect(component.form.valid).toBeTruthy();

  });


  });
