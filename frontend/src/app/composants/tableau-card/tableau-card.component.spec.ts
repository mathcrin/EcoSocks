import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableauCardComponent } from './tableau-card.component';

describe('TableauCardComponent', () => {
  let component: TableauCardComponent;
  let fixture: ComponentFixture<TableauCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableauCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TableauCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
