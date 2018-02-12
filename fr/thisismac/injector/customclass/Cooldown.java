package fr.thisismac.injector.customclass;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Cooldown {
   static int interval;
   static Timer timer;
   static int time;

   public static void start(int time) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input seconds => : ");
      String secs = sc.nextLine();
      short delay = 1000;
      short period = 1000;
      timer = new Timer();
      interval = Integer.parseInt(secs);
      System.out.println(secs);
      timer.scheduleAtFixedRate(new TimerTask() {
         public void run() {
            System.out.println(Cooldown.setInterval());
         }
      }, (long)delay, (long)period);
   }

   private static final int setInterval() {
      if(interval == 1) {
         timer.cancel();
      }

      return --interval;
   }
}
