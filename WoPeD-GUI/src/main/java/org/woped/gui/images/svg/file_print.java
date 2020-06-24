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
public class file_print implements
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
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -0.3119190037250519f, -2.006730079650879f));
// _0_0_0
paint = new RadialGradientPaint(new Point2D.Double(24.0, 41.875), 19.125f, new Point2D.Double(24.0, 41.875), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 0.33333298563957214f, 0.0f, 27.91670036315918f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(43.125, 41.875);
((GeneralPath)shape).curveTo(43.125, 45.395817, 34.562447, 48.25, 24.0, 48.25);
((GeneralPath)shape).curveTo(13.437554, 48.25, 4.875, 45.395817, 4.875, 41.875);
((GeneralPath)shape).curveTo(4.875, 38.354183, 13.437554, 35.5, 24.0, 35.5);
((GeneralPath)shape).curveTo(34.562447, 35.5, 43.125, 38.354183, 43.125, 41.875);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_1
paint = new LinearGradientPaint(new Point2D.Double(1.8456430435180664, 88.29493713378906), new Point2D.Double(18.972126007080078, 88.29493713378906), new float[] {0.0f,0.27586207f,1.0f}, new Color[] {new Color(142, 141, 135, 255),new Color(203, 201, 193, 255),new Color(142, 141, 135, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(2.302720069885254f, 0.0f, 0.0f, 0.4342690110206604f, 0.0f, 0.875f));
shape = new RoundRectangle2D.Double(4.75, 36.0, 38.4375, 6.4375, 3.4230966567993164, 3.4230966567993164);
g.setPaint(paint);
g.fill(shape);
paint = new Color(89, 89, 89, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new RoundRectangle2D.Double(4.75, 36.0, 38.4375, 6.4375, 3.4230966567993164, 3.4230966567993164);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_2
paint = new LinearGradientPaint(new Point2D.Double(1.8456430435180664, 88.29492950439453), new Point2D.Double(18.972126007080078, 88.29492950439453), new float[] {0.0f,1.0f}, new Color[] {new Color(220, 220, 218, 255),new Color(186, 185, 183, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(2.302720069885254f, 0.0f, 0.0f, 0.4342690110206604f, 0.0f, 2.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(7.075825, 21.5);
((GeneralPath)shape).lineTo(40.975952, 21.5);
((GeneralPath)shape).curveTo(41.362827, 21.5, 41.857155, 21.788155, 42.162, 22.223919);
((GeneralPath)shape).curveTo(42.466843, 22.659683, 43.906723, 24.83394, 44.230183, 25.297964);
((GeneralPath)shape).curveTo(44.553642, 25.761988, 44.625, 26.201853, 44.625, 26.77405);
((GeneralPath)shape).lineTo(44.625, 38.850952);
((GeneralPath)shape).curveTo(44.625, 39.764523, 43.889523, 40.5, 42.975952, 40.5);
((GeneralPath)shape).lineTo(5.075825, 40.5);
((GeneralPath)shape).curveTo(4.1622524, 40.5, 3.4267766, 39.764523, 3.4267766, 38.850952);
((GeneralPath)shape).lineTo(3.4267766, 26.77405);
((GeneralPath)shape).curveTo(3.4267766, 26.280031, 3.5284235, 25.571642, 3.8753054, 25.120718);
((GeneralPath)shape).curveTo(4.313023, 24.551714, 5.487279, 22.57277, 5.7970057, 22.153118);
((GeneralPath)shape).curveTo(6.1067324, 21.733467, 6.675481, 21.5, 7.075825, 21.5);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(103, 103, 103, 255);
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(7.075825, 21.5);
((GeneralPath)shape).lineTo(40.975952, 21.5);
((GeneralPath)shape).curveTo(41.362827, 21.5, 41.857155, 21.788155, 42.162, 22.223919);
((GeneralPath)shape).curveTo(42.466843, 22.659683, 43.906723, 24.83394, 44.230183, 25.297964);
((GeneralPath)shape).curveTo(44.553642, 25.761988, 44.625, 26.201853, 44.625, 26.77405);
((GeneralPath)shape).lineTo(44.625, 38.850952);
((GeneralPath)shape).curveTo(44.625, 39.764523, 43.889523, 40.5, 42.975952, 40.5);
((GeneralPath)shape).lineTo(5.075825, 40.5);
((GeneralPath)shape).curveTo(4.1622524, 40.5, 3.4267766, 39.764523, 3.4267766, 38.850952);
((GeneralPath)shape).lineTo(3.4267766, 26.77405);
((GeneralPath)shape).curveTo(3.4267766, 26.280031, 3.5284235, 25.571642, 3.8753054, 25.120718);
((GeneralPath)shape).curveTo(4.313023, 24.551714, 5.487279, 22.57277, 5.7970057, 22.153118);
((GeneralPath)shape).curveTo(6.1067324, 21.733467, 6.675481, 21.5, 7.075825, 21.5);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_3
paint = new Color(251, 251, 251, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(7.424621, 21.975533);
((GeneralPath)shape).curveTo(6.921893, 21.975533, 6.2754774, 22.107307, 6.010408, 22.511225);
((GeneralPath)shape).lineTo(4.1542525, 25.339651);
((GeneralPath)shape).curveTo(3.8554516, 25.794966, 4.1881986, 26.868141, 5.087311, 26.868141);
((GeneralPath)shape).lineTo(42.730785, 26.868141);
((GeneralPath)shape).curveTo(43.946983, 26.868141, 43.950535, 25.858072, 43.663845, 25.42804);
((GeneralPath)shape).lineTo(41.896076, 22.776388);
((GeneralPath)shape).curveTo(41.575542, 22.29559, 41.459198, 21.975533, 40.65864, 21.975533);
((GeneralPath)shape).lineTo(7.424621, 21.975533);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_4
paint = new LinearGradientPaint(new Point2D.Double(15.387969017028809, 32.53923797607422), new Point2D.Double(15.487822532653809, 58.83126449584961), new float[] {0.0f,0.10344828f,1.0f}, new Color[] {new Color(255, 255, 255, 32),new Color(255, 255, 255, 255),new Color(255, 255, 255, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.4953500032424927f, 0.0f, 0.0f, 0.66874098777771f, 0.0f, 2.0f));
stroke = new BasicStroke(0.946967f,0,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(7.537484, 22.445757);
((GeneralPath)shape).lineTo(40.425903, 22.445757);
((GeneralPath)shape).curveTo(40.792263, 22.445757, 41.260372, 22.71863, 41.54905, 23.131283);
((GeneralPath)shape).curveTo(41.837727, 23.543938, 42.847694, 25.160946, 43.154, 25.60036);
((GeneralPath)shape).curveTo(43.460304, 26.039776, 43.59038, 26.456312, 43.59038, 26.998163);
((GeneralPath)shape).lineTo(43.59038, 38.279263);
((GeneralPath)shape).curveTo(43.59038, 39.144386, 43.45641, 39.528355, 42.591286, 39.528355);
((GeneralPath)shape).lineTo(5.4604917, 39.528355);
((GeneralPath)shape).curveTo(4.5953684, 39.528355, 4.398897, 39.144386, 4.398897, 38.279263);
((GeneralPath)shape).lineTo(4.398897, 26.998163);
((GeneralPath)shape).curveTo(4.398897, 26.530346, 4.6201534, 25.859524, 4.948639, 25.432514);
((GeneralPath)shape).curveTo(5.363143, 24.893684, 6.033183, 23.461634, 6.326484, 23.064238);
((GeneralPath)shape).curveTo(6.619785, 22.666842, 7.158371, 22.445757, 7.537484, 22.445757);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_4);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_5
paint = new LinearGradientPaint(new Point2D.Double(25.056711196899414, 3.6785457134246826), new Point2D.Double(24.78970718383789, 25.247310638427734), new float[] {0.0f,0.4054697f,0.5344828f,1.0f}, new Color[] {new Color(224, 224, 224, 255),new Color(255, 255, 255, 255),new Color(205, 205, 205, 255),new Color(73, 73, 73, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(0.9489499926567078f, 0.0f, 0.0f, 1.0809799432754517f, -0.09235560148954391f, 4.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.570264, 4.406405);
((GeneralPath)shape).lineTo(36.30689, 4.406405);
((GeneralPath)shape).curveTo(36.95988, 4.406405, 37.485577, 4.9188805, 37.485577, 5.5554533);
((GeneralPath)shape).lineTo(37.485577, 24.345886);
((GeneralPath)shape).lineTo(10.391575, 24.345886);
((GeneralPath)shape).lineTo(10.391575, 5.5554533);
((GeneralPath)shape).curveTo(10.391575, 4.9188805, 10.91727, 4.406405, 11.570264, 4.406405);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(137, 137, 137, 255);
stroke = new BasicStroke(1.0f,1,0,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.570264, 4.406405);
((GeneralPath)shape).lineTo(36.30689, 4.406405);
((GeneralPath)shape).curveTo(36.95988, 4.406405, 37.485577, 4.9188805, 37.485577, 5.5554533);
((GeneralPath)shape).lineTo(37.485577, 24.345886);
((GeneralPath)shape).lineTo(10.391575, 24.345886);
((GeneralPath)shape).lineTo(10.391575, 5.5554533);
((GeneralPath)shape).curveTo(10.391575, 4.9188805, 10.91727, 4.406405, 11.570264, 4.406405);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_5);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_6
paint = new LinearGradientPaint(new Point2D.Double(20.771228790283203, 25.1402530670166), new Point2D.Double(20.71780014038086, 19.33746337890625), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 0),new Color(248, 248, 248, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.2023600339889526f, 0.0f, 0.0f, 0.8548570275306702f, -0.3023799955844879f, 2.0f));
stroke = new BasicStroke(0.99999994f,1,1,4.0f,null,0.0f);
shape = new RoundRectangle2D.Double(11.374062538146973, 5.469976425170898, 25.075401306152344, 18.864879608154297, 0.3535533547401428, 0.3535533845424652);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_6);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_7 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_7
paint = new LinearGradientPaint(new Point2D.Double(10.33823299407959, 64.65225982666016), new Point2D.Double(10.33823299407959, 54.136138916015625), new float[] {0.0f,1.0f}, new Color[] {new Color(247, 246, 245, 255),new Color(247, 246, 245, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(2.369839906692505f, 0.0f, 0.0f, 0.4219689965248108f, 0.0f, 2.0f));
shape = new RoundRectangle2D.Double(6.875, 27.375, 33.75, 5.1875, 3.4230966567993164, 3.4230966567993164);
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(9.731653213500977, 70.7249755859375), new Point2D.Double(9.705278396606445, 62.282466888427734), new float[] {0.0f,1.0f}, new Color[] {new Color(102, 102, 102, 255),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(2.369839906692505f, 0.0f, 0.0f, 0.4219689965248108f, 0.0f, 2.0f));
stroke = new BasicStroke(1.0f,0,0,4.0f,null,0.0f);
shape = new RoundRectangle2D.Double(6.875, 27.375, 33.75, 5.1875, 3.4230966567993164, 3.4230966567993164);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_7);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_8 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 2.0f));
// _0_0_8
paint = new RadialGradientPaint(new Point2D.Double(9.129549026489258, 26.925594329833984), 2.1227016f, new Point2D.Double(9.129549026489258, 26.925594329833984), new float[] {0.0f,0.5f,1.0f}, new Color[] {new Color(255, 255, 253, 255),new Color(187, 187, 185, 255),new Color(0, 0, 0, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(10.871767, 27.626486);
((GeneralPath)shape).curveTo(10.871767, 28.33431, 10.297961, 28.908117, 9.590136, 28.908117);
((GeneralPath)shape).curveTo(8.882311, 28.908117, 8.308505, 28.33431, 8.308505, 27.626486);
((GeneralPath)shape).curveTo(8.308505, 26.918661, 8.882311, 26.344854, 9.590136, 26.344854);
((GeneralPath)shape).curveTo(10.297961, 26.344854, 10.871767, 26.918661, 10.871767, 27.626486);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_8);
g.setComposite(AlphaComposite.getInstance(3, 0.36571428f * origAlpha));
AffineTransform defaultTransform__0_0_9 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_9
paint = new LinearGradientPaint(new Point2D.Double(9.869808197021484, 57.2276496887207), new Point2D.Double(9.912813186645508, 72.06431579589844), new float[] {0.0f,1.0f}, new Color[] {new Color(0, 0, 0, 60),new Color(0, 0, 0, 0)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(2.772089958190918f, 0.0f, 0.0f, 0.36073899269104004f, 0.6187180280685425f, 2.8838798999786377f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(11.743718, 25.416054);
((GeneralPath)shape).lineTo(37.306217, 25.478554);
((GeneralPath)shape).curveTo(37.993717, 25.480234, 38.294037, 25.107557, 38.243717, 24.478554);
((GeneralPath)shape).lineTo(38.118717, 22.916054);
((GeneralPath)shape).lineTo(39.984837, 22.916054);
((GeneralPath)shape).curveTo(40.797337, 22.916054, 40.975037, 23.108616, 41.172337, 23.478554);
((GeneralPath)shape).lineTo(41.672337, 24.416054);
((GeneralPath)shape).curveTo(42.19913, 25.403793, 43.48351, 26.390165, 42.170494, 26.390165);
((GeneralPath)shape).curveTo(37.667786, 26.390165, 13.993718, 26.041054, 11.743718, 25.416054);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_9);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_10 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_10
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(1.0f,1,1,4.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(42.9375, 26.75);
((GeneralPath)shape).lineTo(4.8125, 26.75);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_10);
g.setComposite(AlphaComposite.getInstance(3, 0.43575415f * origAlpha));
AffineTransform defaultTransform__0_0_11 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 2.0f));
// _0_0_11
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_0
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 5.0, 19.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_1
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 7.0, 19.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_2
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 9.0, 19.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_3 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_3
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 11.0, 19.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_3);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_4 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_4
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 13.0, 11.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_4);
g.setTransform(defaultTransform__0_0_11);
g.setTransform(defaultTransform__0_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_1_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_1_0
paint = new Color(167, 167, 167, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(21.02159, 20.989431);
((GeneralPath)shape).lineTo(27.989391, 20.989431);
((GeneralPath)shape).lineTo(27.989391, 16.064983);
((GeneralPath)shape).lineTo(31.0, 16.064983);
((GeneralPath)shape).lineTo(24.553757, 8.0);
((GeneralPath)shape).lineTo(17.435621, 15.986875);
((GeneralPath)shape).lineTo(21.023684, 15.986875);
((GeneralPath)shape).lineTo(21.02159, 20.989431);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_1_0);
g.setTransform(defaultTransform__0_1);
g.setTransform(defaultTransform__0);
g.setTransform(defaultTransform_);

	}

    /**
     * Returns the X of the bounding box of the original SVG image.
     * 
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 3;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     * 
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 4;
    }

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 43;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 43;
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
	public file_print() {
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

