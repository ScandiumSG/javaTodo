import { TestBed } from '@angular/core/testing';

import { ModifierServiceService } from './task-manager-service.service';

describe('ModifierServiceService', () => {
  let service: ModifierServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModifierServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
