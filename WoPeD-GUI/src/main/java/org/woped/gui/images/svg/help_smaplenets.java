package org.woped.gui.images.svg;



import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * This class has been automatically generated using <a
 * href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class help_smaplenets implements
		org.pushingpixels.flamingo.api.common.icon.ResizableIcon {
	/**
	 * Paints the transcoded SVG image on the specified graphics context. You
	 * can install a custom transformation on the graphics context to scale the
	 * image.
	 * 
	 * @param g
	 *            Graphics context.
	 */
	public static void paint(Graphics2D g) {
        Shape shape = null;
        Paint paint = null;
        Stroke stroke = null;
         
        float origAlpha = 1.0f;
        Composite origComposite = ((Graphics2D)g).getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = 
                (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }
        
	    AffineTransform defaultTransform_ = g.getTransform();
// 
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0
g.setComposite(AlphaComposite.getInstance(3, 0.47368422f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.4723299741744995f, 0.0f, 0.0f, 0.2663849890232086f, -9.159070014953613f, 37.481998443603516f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(22.571428298950195, 30.85714340209961), 15.571428f, new Point2D.Double(22.571428298950195, 30.85714340209961), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.6513760089874268f, 4.6386501512455085E-15f, 10.757499694824219f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(38.142857, 30.857143);
((GeneralPath)shape).curveTo(38.142857, 36.45889, 31.171291, 41.0, 22.571428, 41.0);
((GeneralPath)shape).curveTo(13.971566, 41.0, 7.0, 36.45889, 7.0, 30.857143);
((GeneralPath)shape).curveTo(7.0, 25.255398, 13.971566, 20.714287, 22.571428, 20.714287);
((GeneralPath)shape).curveTo(31.171291, 20.714287, 38.142857, 25.255398, 38.142857, 30.857143);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new LinearGradientPaint(new Point2D.Double(20.794008255004883, 18.378812789916992), new Point2D.Double(35.59600067138672, 39.600460052490234), new float[] {0.0f,0.59928656f,1.0f}, new Color[] {new Color(248, 248, 247, 255),new Color(232, 232, 232, 255),new Color(226, 226, 222, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.3427000045776367f, 0.0f, 0.0f, 1.2353800535202026f, -8.219610214233398f, -6.577189922332764f));
shape = new RoundRectangle2D.Double(4.501601696014404, 1.4968987703323364, 38.99679183959961, 45.00310134887695, 1.133015751838684, 1.1330164670944214);
g.setPaint(paint);
g.fill(shape);
paint = new Color(136, 138, 133, 255);
stroke = new BasicStroke(0.99999976f,0,0,4.0f,null,0.0f);
shape = new RoundRectangle2D.Double(4.501601696014404, 1.4968987703323364, 38.99679183959961, 45.00310134887695, 1.133015751838684, 1.1330164670944214);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
paint = new Color(153, 153, 153, 255);
shape = new Rectangle2D.Double(-39.0, 10.0, 30.0, 2.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new Color(153, 153, 153, 255);
shape = new Rectangle2D.Double(-39.0, 16.0, 30.0, 2.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new Color(153, 153, 153, 255);
shape = new Rectangle2D.Double(-39.0, 22.0, 30.0, 2.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_4);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_5 = g.getTransform();
g.transform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_5
paint = new Color(153, 153, 153, 255);
shape = new Rectangle2D.Double(-39.0, 28.0, 30.0, 2.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_5);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_6 = g.getTransform();
g.transform(new AffineTransform(-1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_6
paint = new Color(153, 153, 153, 255);
shape = new Rectangle2D.Double(-39.0, 34.0, 30.0, 2.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_6);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_7
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(0.9999998f,0,0,4.0f,null,0.0f);
shape = new Rectangle2D.Double(5.4997124671936035, 2.4997177124023438, 37.02556610107422, 43.022315979003906);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_7);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_8
paint = new RadialGradientPaint(new Point2D.Double(30.4375, 30.09375), 10.612f, new Point2D.Double(30.4375, 30.09375), new float[] {0.0f,0.56296295f,1.0f}, new Color[] {new Color(255, 160, 0, 255),new Color(255, 142, 0, 255),new Color(255, 108, 0, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.1089568138122559f, 0.0f, -3.2789199352264404f));
shape = new Rectangle2D.Double(20.0625, 18.5625, 20.75, 23.0625);
g.setPaint(paint);
g.fill(shape);
paint = new Color(255, 255, 255, 92);
stroke = new BasicStroke(0.6f,1,1,4.0f,null,0.0f);
shape = new Rectangle2D.Double(20.0625, 18.5625, 20.75, 23.0625);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_8);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9
paint = new Color(242, 247, 255, 246);
stroke = new BasicStroke(1.6f,1,1,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.125, 21.9375);
((GeneralPath)shape).curveTo(27.3125, 35.6875, 27.375, 35.6875, 27.375, 35.6875);
((GeneralPath)shape).lineTo(30.625, 27.4375);
((GeneralPath)shape).lineTo(33.5, 35.9375);
((GeneralPath)shape).lineTo(37.1875, 22.0);
((GeneralPath)shape).lineTo(33.4375, 36.8125);
((GeneralPath)shape).lineTo(30.75, 28.9375);
((GeneralPath)shape).lineTo(27.375, 36.625);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_9);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(0.5f,1,1,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(24.15625, 22.0625);
((GeneralPath)shape).curveTo(27.34375, 35.8125, 27.40625, 35.8125, 27.40625, 35.8125);
((GeneralPath)shape).lineTo(30.65625, 27.5625);
((GeneralPath)shape).lineTo(33.53125, 36.0625);
((GeneralPath)shape).lineTo(37.21875, 22.125);
((GeneralPath)shape).lineTo(33.46875, 36.9375);
((GeneralPath)shape).lineTo(30.71875, 28.6875);
((GeneralPath)shape).lineTo(27.40625, 36.75);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_10);
g.setTransform(defaultTransform__0_0);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 2;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 1;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 46;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 48;
	}

	/**
	 * The current width of this resizable icon.
	 */
	int width;

	/**
	 * The current height of this resizable icon.
	 */
	int height;

	/**
	 * Creates a new transcoded SVG image.
	 */
	public void help_samplenets() {
        this.width = getOrigWidth();
        this.height = getOrigHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconHeight()
	 */
    @Override
	public int getIconHeight() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconWidth()
	 */
    @Override
	public int getIconWidth() {
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jvnet.flamingo.common.icon.ResizableIcon#setDimension(java.awt.Dimension
	 * )
	 */
	@Override
	public void setDimension(Dimension newDimension) {
		this.width = newDimension.width;
		this.height = newDimension.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics,
	 * int, int)
	 */
    @Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(x, y);

		double coef1 = (double) this.width / (double) getOrigWidth();
		double coef2 = (double) this.height / (double) getOrigHeight();
		double coef = Math.min(coef1, coef2);
		g2d.scale(coef, coef);
		paint(g2d);
		g2d.dispose();
	}
}

