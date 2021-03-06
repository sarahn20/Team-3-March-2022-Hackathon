import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.*;
import sun.audio.*;
import java.io.*;
import java.awt.*;

public class Driver {

	private static AudioStream theStream;
	
    public static void main(String[] args) {
        new Driver();
        try
        {
            theStream = new AudioStream(new FileInputStream("src//tetris.wav"));
            AudioPlayer.player.start(theStream);
            System.out.println("reached");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("reached (exception)");
        } 
    }

    public Driver() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private int xPos;
        private int yPos;
        private int x2Pos = 580;
        private int y2Pos= 580;
        Random rand = new Random();
        public TestPane() {
            Action left2Action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	x2Pos -= 2;
                    if (x2Pos < 0) {
                    	x2Pos = 0;
                    }
                    repaint();
                }
            };
            Action right2Action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	x2Pos += 2;
                    if (x2Pos + 10 > getWidth()) {
                    	x2Pos = getWidth() - 10;
                    }
                    repaint();
                }
            };
            Action down2Action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	y2Pos += 2;
                    if (y2Pos + 10 > getWidth()) {
                    	y2Pos = getWidth() - 10;
                    }
                    repaint();
                }
            };
            Action up2Action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	y2Pos -= 2;
                    if (y2Pos < 0) {
                    	y2Pos = 0;
                    }
                    repaint();
                }
            };
        	/////////////////////////////////////
            Action leftAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xPos -= 2;
                    if (xPos < 0) {
                        xPos = 0;
                    }
                    repaint();
                    if(yPos == y2Pos  && xPos==x2Pos)
                    {
                    	yPos = 0;
                    	xPos = 0;
                    }
                    if(xPos < x2Pos)
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        x2Pos -= int_random;
                    }
                    else
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        x2Pos += int_random;
                    }
                }
            };
            Action rightAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    xPos += 2;
                    if (xPos + 10 > getWidth()) {
                        xPos = getWidth() - 10;
                    }
                    repaint();
                    if(yPos == y2Pos  && xPos==x2Pos)
                    {
                    	yPos = 0;
                    	xPos = 0;
                    }
                    if(xPos > x2Pos)
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        x2Pos += int_random;
                    }
                    else
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        x2Pos -= int_random;
                    }

                }
            };
            Action downAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    yPos += 2;
                    if (yPos + 10 > getWidth()) {
                        yPos = getWidth() - 10;
                    }
                    repaint();
                    if(yPos == y2Pos  && xPos==x2Pos)
                    {
                    	yPos = 0;
                    	xPos = 0;
                    }
                    if(yPos > y2Pos)
                    {
                    	
                        int int_random = rand.nextInt(10 + 5) - 5;
                        y2Pos += int_random;
                    }
                    else
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        y2Pos -= int_random;	
                    }

                }
            };
            Action upAction = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    yPos -= 2;
                    if (yPos < 0) {
                        yPos = 0;
                    }
                    repaint();
                    if(yPos == y2Pos  && xPos==x2Pos)
                    {
                    	yPos = 0;
                    	xPos = 0;
                    }
                    //generate random values from 0-24
                    //random.nextInt(30 + 10) - 10;
                    if(yPos< y2Pos)
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        y2Pos -= int_random;                    	
                    }
                    else
                    {
                        int int_random = rand.nextInt(10 + 5) - 5;
                        y2Pos += int_random; 
                    }

                }
 
            };

            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), leftAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), rightAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.up", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), upAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), downAction);
            //////////////////////////////////////////////////////////////

            
            /*
            bindKeyStroke2(WHEN_IN_FOCUSED_WINDOW, "move.left2", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), left2Action);
            bindKeyStroke2(WHEN_IN_FOCUSED_WINDOW, "move.right2", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), right2Action);
            bindKeyStroke2(WHEN_IN_FOCUSED_WINDOW, "move.up2", KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), up2Action);
            bindKeyStroke2(WHEN_IN_FOCUSED_WINDOW, "move.down2", KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), down2Action);
            */
        }

        protected void bindKeyStroke(int condition, String name, KeyStroke keyStroke, Action action) {
            InputMap im = getInputMap(condition);
            ActionMap am = getActionMap();

            im.put(keyStroke, name);
            am.put(name, action);
        }
        protected void bindKeyStroke2(int condition, String name, KeyStroke keyStroke, Action action) {
            InputMap im = getInputMap(condition);
            ActionMap am = getActionMap();

            im.put(keyStroke, name);
            am.put(name, action);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(600, 600);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            Graphics2D g3d = (Graphics2D) g.create();
            g2d.setColor(Color.BLUE);
			g2d.fillRect(xPos,yPos, 20,20);
            g3d.setColor(Color.RED);
			g3d.fillRect(x2Pos, y2Pos, 20, 20);
			g3d.fillRect(y2Pos, x2Pos, 20, 20);
            g3d.drawRect(x2Pos, y2Pos, 20, 20);
            g3d.drawRect(y2Pos, x2Pos, 20, 20);
            g2d.drawRect(xPos, yPos, 20, 20);
            setBackground(Color.BLACK);
            g2d.dispose();
            g3d.dispose();
        }

    }

}