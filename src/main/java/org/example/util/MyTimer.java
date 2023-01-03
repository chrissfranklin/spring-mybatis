package org.example.util;

/*
 * Used to time how long (elapsed millis) for code to run, for example, a database call
 * Returns the number of elapsed milliseconds.
 * Usage:
 *   MyTimer t = new myTimer(); t.start();
 *   long elapsedMillis = t.stop();
 */
public class MyTimer
{
  long startMillis;
  long endMillis;
  long elapsedMillis;
  long totalElapsedMillis;
  long elapsedSeconds;
  long elapsedMinutes;

  // constructor
  public MyTimer() {}

  public void start()
  {
    this.startMillis = System.currentTimeMillis();
  }

  public long stop()
  {
    this.endMillis = System.currentTimeMillis();
    this.totalElapsedMillis = this.endMillis - this.startMillis;
    this.elapsedMillis  = this.elapsedMillis % 1000;
    this.elapsedSeconds = this.elapsedMillis / 1000;
    this.elapsedMinutes = this.elapsedSeconds / 60;
    return totalElapsedMillis;
  }
  //long elapsedTime = System.currentTimeMillis() - startTime;
  //long elapsedSeconds = elapsedTime / 1000;
  //long secondsDisplay = elapsedSeconds % 60;
  //long elapsedMinutes = elapsedSeconds / 60;
  //put here code to format and display the values
}
