import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { SectorService } from './sector.service';
import Sector from '../model/Sector';

describe('SectorService', () => {
  let service: SectorService;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(SectorService);
    httpController = TestBed.inject(HttpTestingController);
  });

  afterEach(()=>{

    httpController.verify();
    
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return all sectors when getAllSectors called', ()=>{

    const testSectors: Sector[] = [
      {
        id: 1,
        name: 'Test',
        subSectors: [
          {
            id: 2,
            name: 'Child',
            subSectors: [
              {
                id: 3,
                name: 'Child',
                subSectors: [],
              },
            ],
          },
        ],
      },
      {
        id: 8,
        name: 'Secodnd ',
        subSectors: [
          {
            id: 10,
            name: 'Second Child',
            subSectors: [
              {
                id: 20,
                name: 'Second Second Child',
                subSectors: [],
              },
            ],
          },
        ],
      },
    ];

      service.getAllSectors().subscribe((sectors)=> {
        expect(testSectors).toEqual(sectors, 'checked if returned data equals mocked data')
      })

      const request = httpController.expectOne(`${service.baseUrl}/sectors`)
      expect(request.cancelled).toBeFalsy();
      expect(request.request.responseType).toEqual('json');

      request.flush(testSectors);


  })
});
