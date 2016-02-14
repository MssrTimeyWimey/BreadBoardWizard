import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private final int projectileSpeed = 20;
	private final int waveSpeed = 40;
	private final int enemySpeed = 10;
	
	double w;
	double h;
	
	Image cannonImage;
	Image bg;
	
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
	
	private class WaveInfo {
		public double radius;
		public WaveInfo(double radius) {
			this.radius = radius;
		}
	}
	
	private class EnemyInfo {
		public double x;
		public double y;
		public EnemyInfo(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private LinkedBlockingDeque<ProjectileInfo> projectiles = new LinkedBlockingDeque<>();
	private LinkedBlockingDeque<WaveInfo> waves = new LinkedBlockingDeque<>();
	private LinkedBlockingDeque<EnemyInfo> enemies = new LinkedBlockingDeque<>();
	
	public Board() {
		super(true);
	    w = 800;
	    h = 600;
	    
	    ImageIcon ii = new ImageIcon("cannon.png");
        cannonImage = ii.getImage();

	    ii = new ImageIcon("bg.jpg");
        bg = ii.getImage();
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
		projectiles.add(new ProjectileInfo((w/2), ((h*3)/4), getAngle()));
	}
	
	public void scream() {
		waves.add(new WaveInfo(0));
	}
	
	public void makeEnemy() {
		enemies.add(new EnemyInfo(Math.random()*w, -50));
	}

    private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);
        
        Ellipse2D projShape = new Ellipse2D.Double(-25, -25, 50, 50);
        
        g2d.drawImage(bg, 0, 0, (int)w, (int)h, null);
        

        for (ProjectileInfo projectile : projectiles) {
        	AffineTransform at2 = AffineTransform.getTranslateInstance(projectile.x, projectile.y);
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.red);
    		g2d.draw(at2.createTransformedShape(projShape));
        }
        
        synchronized (waves) {
        	for (WaveInfo wave : waves) {
        		Ellipse2D waveShape = new Ellipse2D.Double(w/2-wave.radius, (h*3)/4-wave.radius, wave.radius*2, wave.radius*2);
        		g2d.setStroke(new BasicStroke(1));
        		g2d.setColor(Color.blue);
        		g2d.draw(waveShape);
        	}
        }
        
        Rectangle2D enemyShape = new Rectangle2D.Double(-12, -12, 25, 25);
        
        for (EnemyInfo enemy : enemies) {
        	AffineTransform at3 = AffineTransform.getTranslateInstance(enemy.x, enemy.y);
        	g2d.setStroke(new BasicStroke(3));
    		g2d.setColor(Color.pink);
    		g2d.draw(at3.createTransformedShape(enemyShape));
        }
        
        AffineTransform at = AffineTransform.getTranslateInstance(w/2-76, (h*3)/4-116);
		at.rotate(Math.toRadians(getAngle()), 76, 116);
        /*
		g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);
		g2d.draw(at.createTransformedShape(e));
        */
        g2d.drawImage(cannonImage, at, null);
    }
    
    private void cycle() {
    	{
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
        synchronized (waves) {
	    	Iterator<WaveInfo> i = waves.iterator();
	    	while(i.hasNext()) {
	    		WaveInfo wave = i.next();
	    		wave.radius = wave.radius + waveSpeed;
	    		if (wave.radius > 500) {
	    			i.remove();
	    		}
	    	}
    	}
        {
        	Iterator<EnemyInfo> i = enemies.iterator();
	    	while(i.hasNext()) {
	    		EnemyInfo enemy = i.next();
	    		enemy.y = enemy.y + enemySpeed;
	    		if (enemy.y > h) {
	    			i.remove();
	    		} else {
		    		for (ProjectileInfo proj : projectiles) {
		    			double dx = enemy.x - proj.x;
		    			double dy = enemy.y - proj.y;
		    			double dist = Math.sqrt(dx*dx+dy*dy);
		    			if (dist < 35) {
		    				i.remove();
		    			}
		    		}
	    		}
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