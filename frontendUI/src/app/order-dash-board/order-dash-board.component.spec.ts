import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderDashBoardComponent } from './order-dash-board.component';

describe('OrderDashBoardComponent', () => {
  let component: OrderDashBoardComponent;
  let fixture: ComponentFixture<OrderDashBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderDashBoardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
