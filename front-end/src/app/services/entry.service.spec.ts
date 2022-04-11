import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import Entry from '../model/Entry';

import { EntryService } from './entry.service';

describe('EntryService', () => {
  let service: EntryService;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(EntryService);
    httpController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return entry when getEntryById called', () => {
    const id = 1;

    const testEntry: Entry = {
      id: 1,
      name: 'Test Entry',
      agreeToTerms: true,
      sectors: [
        {
          id: 2,
          name: 'Test Sector',
        },
      ],
    };
    service.getEntryById(id).subscribe((data) => {
      expect(data).toEqual(testEntry);
    });

    const request = httpController.expectOne(
      service.baseUrl + '/entries/' + id
    );

    expect(request.cancelled).toBeFalsy();
    expect(request.request.responseType).toEqual('json');

    request.flush(testEntry);
  });

  it('should return 404 when Entry Not found', () => {
    const errorMessage = '404 Entry Not Found';

    const id = 1;
    service.getEntryById(id).subscribe(
      (data) => {
        fail('Failed with 404 Entry not found');
      },
      (error: HttpErrorResponse) => {
        expect(error.status).toEqual(404);
        expect(error.message).toContain(errorMessage);
      }
    );

    const request = httpController.expectOne(
      service.baseUrl + '/entries/' + id
    );

    request.flush(errorMessage, { status: 404, statusText: 'Entry Not Found' });
  });

  it('should return updated Entry when Entry update', () => {
    const sentEntry = {
      id: 1,
      name: 'Test Entry',
      agreeToTerms: true,
      sectors: [2],
    };

    const updatedEntry: Entry = {
      id: 1,
      name: 'Test Entry',
      agreeToTerms: true,
      sectors: [
        {
          id: 2,
          name: 'Test Sector',
        },
      ],
    };

    service.updateEntry(sentEntry).subscribe((data) => {
      expect(data.status).toEqual(200)
      expect(data.body).toEqual(updatedEntry);
    });

    const request = httpController.expectOne({
      method: 'PUT',
      url: `${service.baseUrl}/entries`,
    });

    request.flush(updatedEntry);
  });

  it('should return created Entry when createEntry called', () => {
    const sentEntry = {
      id: '',
      name: 'Test Entry',
      agreeToTerms: true,
      sectors: [2],
    };

    const savedEntry: Entry = {
      id: 1,
      name: 'Test Entry',
      agreeToTerms: true,
      sectors: [
        {
          id: 2,
          name: 'Test Sector',
        },
      ],
    };

    //check if server returns correct response when a new resource is created
    service
      .createEntry(sentEntry)
      .subscribe((response: HttpResponse<Entry>) => {
        expect(response.status).toBe(201);
        expect(response.body).toEqual(savedEntry);
      });

    const req = httpController.expectOne({
      method: 'POST',
      url: `${service.baseUrl}/entries`,
    });

    req.flush(savedEntry, { status: 201, statusText: 'Created' });
  });
});
