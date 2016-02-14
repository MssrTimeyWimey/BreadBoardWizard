import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private final int projectileSpeed = 5;
	
	double w;
	double h;
	
	private double angle = 0;
	
	private Thread animator;
	
	private class ProjectileInfo {
		public double x;
		public double y;
		public double angle;
		public ProjectileInfo(double x, double y, double angle) {
			this.x = x;
			this.y = y;
			this.angle = angle;
		}
	}
	
	private LinkedList<ProjectileInfo> projectiles = new LinkedList<>();
	
	public Board() {
	    w = 800;
	    h = 300;
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDonut(g);
    }
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void shoot() {
		projectiles.add(new ProjectileInfo((w/2), (h/2), getAngle()));
	}

    private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Ellipse2D e = new Ellipse2D.Double(-50, -100, 100, 200);
        
        Ellipse2D projShape = new Ellipse2D.Double(-25, -25, 50, 50);
        
        AffineTransform at = AffineTransform.getTranslateInstance(w/2, h/2);
		at.rotate(Math.toRadians(getAngle()));
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);
		g2d.draw(at.createTransformedShape(e));

        for (ProjectileInfo projectile : projectiles) {
        	AffineTransform at2 = AffineTransform.getTranslateInstance(projectile.x, projectile.y);
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.red);
    		g2d.draw(at2.createTransformedShape(projShape));
        }
    }
    
    private void cycle() {
    	Iterator<ProjectileInfo> i = projectiles.iterator();
    	while(i.hasNext()) {
    		ProjectileInfo projectile = i.next();
    		projectile.x = projectile.x + projectileSpeed*Math.sin(projectile.angle*Math.PI/180);
    		projectile.y = projectile.y - projectileSpeed*Math.cos(projectile.angle*Math.PI/180);
    		if (projectile.y < -25) {
    			i.remove();
    		}
    	}
    }

    @Override
    public void addNotify() {
        super.addNotify();

        animator = new Thread(this);
        animator.start();
    }
    
    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        while (true) {

            cycle();
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = (1000 / 30) - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}