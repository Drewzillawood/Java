package hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import hw1.Cab;

public class CabTests
{
  // Cab instance to use for most of the tests 
  private Cab cab;
  
  private final double BASE = 3.0;
  private final double PER_MILE = 5.0;
  private static final double ERR = 10e-7;  // margin of error
  
  @Before
  public void setup()
  {
    cab = new Cab(BASE, PER_MILE);
  }
  
  @Test 
  public void testInitial()
  {
    String msg = "For a new cab, getTotalMiles should be zero. ";
    assertEquals(msg, 0.0, cab.getTotalMiles(), ERR);
  }

  @Test 
  public void testInitial2()
  {
    String msg = "For a new cab, getTotalCash should be zero. ";
    assertEquals(msg, 0.0, cab.getTotalCash(), ERR);
  }
  
  @Test 
  public void testInitial3()
  {
    String msg = "For a new cab, getCurrentRate should be zero. ";
    assertEquals(msg, 0.0, cab.getCurrentRate(), ERR);
  }

  @Test 
  public void testInitial4()
  {
    String msg = "For a new cab, meter should be zero. ";
    assertEquals(msg, 0.0, cab.getMeter(), ERR);
  }

  @Test 
  public void testInitialDrive()
  {
    String msg = "For a new cab, after driving 10 miles, meter should be zero. ";
    cab.drive(10);
    assertEquals(msg, 0.0, cab.getMeter(), ERR);
  }

  @Test 
  public void testInitialDrive2()
  {
    String msg = "For a new cab, after driving 10 miles, total miles should be 10.0. ";
    cab.drive(10);
    assertEquals(msg, 10.0, cab.getTotalMiles(), ERR);
  }

  @Test 
  public void testInitialDrive3()
  {
    String msg = "For a new cab, after drive(10) and drive(2), total miles should be 12.0. ";
    cab.drive(10);
    cab.drive(2);
    assertEquals(msg, 12.0, cab.getTotalMiles(), ERR);
  }

  @Test 
  public void testRateAfterStart()
  {
    String msg = "For a new Cab(3,5), after pickUp, getCurrentRate should be 5.0. ";
    cab.pickUp();
    assertEquals(msg, 5.0, cab.getCurrentRate(), ERR);
  }
 
  @Test 
  public void testHasPassengerAfterStart()
  {
    String msg = "For a new Cab(3,5), after pickUp, hasPassenger should be true. ";
    cab.pickUp();
    assertEquals(msg, true, cab.hasPassenger());
  }
  
  @Test 
  public void testMeterAfterStart()
  {
    String msg = "For a new Cab(3,5), after pickUp, meter should be 3.0. ";
    cab.pickUp();
    assertEquals(msg, 3.0, cab.getMeter(), ERR);
  }
  
  @Test 
  public void testMeterAfterStartAndDrive()
  {
    String msg = "For a new Cab(3,5), after pickUp and drive(10), meter should be 53.0. ";
    cab.pickUp();
    cab.drive(10);
    assertEquals(msg, 53.0, cab.getMeter(), ERR);
  }

  @Test 
  public void testMeterAfterStartAndDrive2()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10) and drive(2.2), meter should be 64.0. ";
    cab.pickUp();
    cab.drive(10);
    cab.drive(2.2);
    assertEquals(msg, 64.0, cab.getMeter(), ERR);
  }

  @Test 
  public void testMeterAfterStartAndDrive2a()
  {
    String msg = "For a new Cab(2.4, 6.8), after pickUp, drive(.5) and drive(1.5), meter should be 16.0. ";
    cab = new Cab(2.4, 6.8);
    cab.pickUp();
    cab.drive(0.5);
    cab.drive(1.5);
    assertEquals(msg, 16.0, cab.getMeter(), ERR);
  }

  @Test 
  public void testCashAfterStartAndDrive()
  {
    String msg = "For a new Cab(3,5), after pickUp and drive(10), total cash should be zero. ";
    cab.pickUp();
    cab.drive(10);
    assertEquals(msg, 0, cab.getTotalCash(), ERR);
  }

  @Test 
  public void testCashAfterStartAndDriveAndStop()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and dropOff, total cash should be 53.0. ";
    cab.pickUp();
    cab.drive(10);
    cab.dropOff();
    assertEquals(msg, 53.0, cab.getTotalCash(), ERR);
  }

  @Test 
  public void testMeterAfterStartAndDriveAndStop()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and dropOff, meter should be zero. ";
    cab.pickUp();
    cab.drive(10);
    cab.dropOff();
    assertEquals(msg, 0, cab.getMeter(), ERR);
  }

  @Test 
  public void testRateAfterStartAndDriveAndStop()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and dropOff, currentRate should be zero. ";
    cab.pickUp();
    cab.drive(10);
    cab.dropOff();
    assertEquals(msg, 0, cab.getCurrentRate(), ERR);
  }
  
  @Test 
  public void testPassengerAfterStartAndDriveAndStop()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and dropOff, hasPassenger should be false. ";
    cab.pickUp();
    cab.drive(10);
    cab.dropOff();
    assertEquals(msg, false, cab.hasPassenger());
  }
  
  @Test 
  public void testCashAfterRestart()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and pickUp, total cash should be zero. ";
    cab.pickUp();
    cab.drive(10);
    cab.pickUp();
    assertEquals(msg, 0, cab.getTotalCash(), ERR);
  }

  @Test 
  public void testMeterAfterRestart()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and pickUp, meter should be 3.0. ";
    cab.pickUp();
    cab.drive(10);
    cab.pickUp();
    assertEquals(msg, 3.0, cab.getMeter(), ERR);
  }
 
  @Test 
  public void testCashAfterStartAndDriveAndStopMultiple()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(1), and dropOff 10 times, total cash should be 80.0. ";
    for (int i = 0; i < 10; ++i)
    {
      cab.pickUp();
      cab.drive(1);
      cab.dropOff();
    }
    assertEquals(msg, 80.0, cab.getTotalCash(), ERR);
  }

  @Test 
  public void testCashAfterStartAndDriveAndStopMultiple2()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(1), and dropOff 10 times, total miles should be 10.0. ";
    for (int i = 0; i < 10; ++i)
    {
      cab.pickUp();
      cab.drive(1);
      cab.dropOff();
    }
    assertEquals(msg, 10.0, cab.getTotalMiles(), ERR);
  }
 
  @Test 
  public void testMeterAfterMultiple()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(1), and dropOff 10 times, meter should be 0.0. ";
    for (int i = 0; i < 10; ++i)
    {
      cab.pickUp();
      cab.drive(1);
      cab.dropOff();
    }
    assertEquals(msg, 0.0, cab.getMeter(), ERR);
  }
 
  @Test 
  public void testInitialAverage()
  {
    String msg = "For a new cab, after driving 10 miles, average income per mile should be zero. ";
    cab.drive(10);
    assertEquals(msg, 0.0, cab.getAverageIncomePerMile(), ERR);
  }

  @Test 
  public void testAverageAfterStartAndDriveAndStop()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), and dropOff, average income per mile should be 5.3. ";
    cab.pickUp();
    cab.drive(10);
    cab.dropOff();
    assertEquals(msg, 5.3, cab.getAverageIncomePerMile(), ERR);
  }

  @Test 
  public void testAverageAfterStartAndDriveAndStop2()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(10), dropOff, drive(10), average income per mile should be 2.65. ";
    cab.pickUp();
    cab.drive(10);
    cab.dropOff();
    cab.drive(10);
    assertEquals(msg, 2.65, cab.getAverageIncomePerMile(), ERR);
  }

  @Test 
  public void testAverageAfterMultiple()
  {
    String msg = "For a new Cab(3,5), after pickUp, drive(2), and dropOff 10 times, average income per mile should be 6.5. ";
    for (int i = 0; i < 10; ++i)
    {
      cab.pickUp();
      cab.drive(2);
      cab.dropOff();
    }
    assertEquals(msg, 6.5, cab.getAverageIncomePerMile(), ERR);
  }
  
  @Test 
  public void testStartStop()
  {
    String msg = "For a new Cab(42, 17), after pickUp and dropOff, total cash should be 42.0. ";
    cab = new Cab(42, 17);
    cab.pickUp();
    cab.dropOff();
    assertEquals(msg, 42.0, cab.getTotalCash(), ERR);
  }
  
  @Test 
  public void testMultiple()
  {
    String msg = "For Cab(3,5), after 1.5/pickUp/2.5/pickUp/dropOff/0.5/pickUp/0.2/dropOff, total miles should be 4.7. ";
    //t = new Cab(3.5, 0.7);
    cab.drive(1.5);
    cab.pickUp();
    cab.drive(2.5);
    cab.pickUp();
    cab.dropOff();
    cab.drive(0.5);
    cab.pickUp();
    cab.drive(0.2);
    cab.dropOff();

    assertEquals(msg, 4.7, cab.getTotalMiles(), ERR);
  }

  @Test 
  public void testMultiple2()
  {
    String msg = "For Cab(3,5), after 1.5/pickUp/2.5/pickUp/dropOff/0.5/pickUp/0.2/dropOff, total cash should be 7.0. ";
    //t = new Cab(3.5, 0.7);
    cab.drive(1.5);
    cab.pickUp();
    cab.drive(2.5);
    cab.pickUp();
    cab.dropOff();
    cab.drive(0.5);
    cab.pickUp();
    cab.drive(0.2);
    cab.dropOff();

    assertEquals(msg, 7.0, cab.getTotalCash(), ERR);
  }

  @Test 
  public void testMultipleCallsToPickUp()
  {
    String msg = "For Cab(3,5), after pickUp, drive(10), and pickUp again, meter should be 3.0. ";
    cab.pickUp();
    cab.drive(10);
    cab.pickUp();
    assertEquals(msg, 3.0, cab.getMeter(), ERR);
  }
  
  @Test 
  public void testMultipleCallsToPickUp2()
  {
    String msg = "For Cab(3,5), after pickUp, drive(10), and pickUp again, total cash should be 0. ";
    cab.pickUp();
    cab.drive(10);
    cab.pickUp();
    assertEquals(msg, 0, cab.getTotalCash(), ERR);
  }
  
  @Test 
  public void testStatic()
  {
    String msg = "Calling drive() on one Cab should not affect any other Cab instance. ";
    Cab t2 = new Cab(42, 17);
    t2.drive(100);
    String msg2 = "Correct previous errors: after drive(100), total mileage should be 100. ";
    assertEquals(msg2, 100.0, t2.getTotalMiles(), ERR);
    assertEquals(msg, 0.0, cab.getTotalMiles(), ERR);
  }
  
  @Test 
  public void testStatic2()
  {
    String msg = "Calling pickUp() on one Cab should not affect any other Cab instance. ";
    Cab t2 = new Cab(42, 17);
    t2.pickUp();
    String msg2 = "Correct previous errors: after pickUp on Cab(42, 17), meter should be 42.0. ";
    assertEquals(msg2, 42.0, t2.getMeter(), ERR);
    assertEquals(msg, 0.0, cab.getMeter(), ERR);
  }
  
  

}