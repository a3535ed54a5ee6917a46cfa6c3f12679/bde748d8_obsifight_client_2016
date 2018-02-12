package fr.thisismac.injector.customclass;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class HoverButton extends JButton {
   float alpha = 0.5F;

   public HoverButton(String text) {
      super(text);
      this.setFocusPainted(false);
      this.setBorderPainted(false);
      this.setContentAreaFilled(false);
      this.addMouseListener(new HoverButton.ML());
   }

   public float getAlpha() {
      return this.alpha;
   }

   public void setAlpha(float alpha) {
      this.alpha = alpha;
      this.repaint();
   }

   public void paintComponent(Graphics g) {
      Graphics2D g2 = (Graphics2D)g;
      g2.setComposite(AlphaComposite.getInstance(3, this.alpha));
      super.paintComponent(g2);
   }

   public class ML extends MouseAdapter {
      public void mouseExited(MouseEvent me) {
         (new Thread(new Runnable() {
            public void run() {
               for(float i = 1.0F; i >= 0.5F; i -= 0.03F) {
                  HoverButton.this.setAlpha(i);

                  try {
                     Thread.sleep(10L);
                  } catch (Exception var3) {
                     ;
                  }
               }
            }
         })).start();
      }

      public void mouseEntered(MouseEvent me) {
         (new Thread(new Runnable() {
            public void run() {
               for(float i = 0.5F; i <= 1.0F; i += 0.03F) {
                  HoverButton.this.setAlpha(i);

                  try {
                     Thread.sleep(10L);
                  } catch (Exception var3) {
                     ;
                  }
               }
            }
         })).start();
      }

      public void mousePressed(MouseEvent me) {
         (new Thread(new Runnable() {
            public void run() {
               for(float i = 1.0F; i >= 0.6F; i -= 0.1F) {
                  HoverButton.this.setAlpha(i);

                  try {
                     Thread.sleep(1L);
                  } catch (Exception var3) {
                     ;
                  }
               }
            }
         })).start();
      }
   }
}
