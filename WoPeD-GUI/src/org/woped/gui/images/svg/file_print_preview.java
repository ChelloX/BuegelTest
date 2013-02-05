package org.woped.gui.images.svg;



import java.awt.*;
import java.awt.geom.*;

/**
 * This class has been automatically generated using <a
 * href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class file_print_preview implements
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
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_5 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_5
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 17.0, 19.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_5);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_11_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_11_6
paint = new Color(0, 0, 0, 75);
shape = new Rectangle2D.Double(14.0, 19.0, 19.0, 1.0);
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_11_6);
g.setTransform(defaultTransform__0_0_11);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12 = g.getTransform();
g.transform(new AffineTransform(0.7156779766082764f, 0.0f, 0.0f, 0.7156779766082764f, 4.0775299072265625f, 4.713890075683594f));
// _0_0_12
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_0 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12_0
paint = new Color(220, 220, 220, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(18.62757, 3.1435547);
((GeneralPath)shape).curveTo(10.488439, 3.1435547, 3.8827682, 9.749226, 3.8827682, 17.888355);
((GeneralPath)shape).curveTo(3.8827682, 26.027487, 10.488439, 32.63316, 18.62757, 32.63316);
((GeneralPath)shape).curveTo(22.107124, 32.63316, 25.17857, 31.248766, 27.701292, 29.23051);
((GeneralPath)shape).curveTo(27.495914, 30.237392, 27.623257, 31.265879, 28.457436, 31.990437);
((GeneralPath)shape).lineTo(39.42152, 41.517845);
((GeneralPath)shape).curveTo(40.654938, 42.589176, 42.508984, 42.448807, 43.58031, 41.21539);
((GeneralPath)shape).curveTo(44.651638, 39.98197, 44.51127, 38.127926, 43.27785, 37.0566);
((GeneralPath)shape).lineTo(32.31377, 27.529188);
((GeneralPath)shape).curveTo(31.642242, 26.94591, 30.82089, 26.773218, 30.00753, 26.886465);
((GeneralPath)shape).curveTo(31.99423, 24.374044, 33.37237, 21.337664, 33.37237, 17.888355);
((GeneralPath)shape).curveTo(33.37237, 9.749226, 26.766699, 3.1435547, 18.62757, 3.1435547);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(18.551954, 4.369738);
((GeneralPath)shape).curveTo(26.191414, 4.369738, 31.843729, 9.158689, 31.843729, 17.661512);
((GeneralPath)shape).curveTo(31.843729, 26.336626, 26.027039, 30.953287, 18.551954, 30.953287);
((GeneralPath)shape).curveTo(11.249005, 30.953287, 5.2601805, 25.475197, 5.2601805, 17.661512);
((GeneralPath)shape).curveTo(5.2601805, 9.677406, 11.084819, 4.369738, 18.551954, 4.369738);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new LinearGradientPaint(new Point2D.Double(27.36634063720703, 26.58029556274414), new Point2D.Double(31.33596420288086, 30.557771682739258), new float[] {0.0f,1.0f}, new Color[] {new Color(138, 138, 138, 255),new Color(72, 72, 72, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
stroke = new BasicStroke(2.7945554f,1,0,10.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(18.62757, 3.1435547);
((GeneralPath)shape).curveTo(10.488439, 3.1435547, 3.8827682, 9.749226, 3.8827682, 17.888355);
((GeneralPath)shape).curveTo(3.8827682, 26.027487, 10.488439, 32.63316, 18.62757, 32.63316);
((GeneralPath)shape).curveTo(22.107124, 32.63316, 25.17857, 31.248766, 27.701292, 29.23051);
((GeneralPath)shape).curveTo(27.495914, 30.237392, 27.623257, 31.265879, 28.457436, 31.990437);
((GeneralPath)shape).lineTo(39.42152, 41.517845);
((GeneralPath)shape).curveTo(40.654938, 42.589176, 42.508984, 42.448807, 43.58031, 41.21539);
((GeneralPath)shape).curveTo(44.651638, 39.98197, 44.51127, 38.127926, 43.27785, 37.0566);
((GeneralPath)shape).lineTo(32.31377, 27.529188);
((GeneralPath)shape).curveTo(31.642242, 26.94591, 30.82089, 26.773218, 30.00753, 26.886465);
((GeneralPath)shape).curveTo(31.99423, 24.374044, 33.37237, 21.337664, 33.37237, 17.888355);
((GeneralPath)shape).curveTo(33.37237, 9.749226, 26.766699, 3.1435547, 18.62757, 3.1435547);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(18.551954, 4.369738);
((GeneralPath)shape).curveTo(26.191414, 4.369738, 31.843729, 9.158689, 31.843729, 17.661512);
((GeneralPath)shape).curveTo(31.843729, 26.336626, 26.027039, 30.953287, 18.551954, 30.953287);
((GeneralPath)shape).curveTo(11.249005, 30.953287, 5.2601805, 25.475197, 5.2601805, 17.661512);
((GeneralPath)shape).curveTo(5.2601805, 9.677406, 11.084819, 4.369738, 18.551954, 4.369738);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_12_0);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_1 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12_1
paint = new Color(220, 220, 220, 255);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(18.602905, 3.0803552);
((GeneralPath)shape).curveTo(10.437465, 3.0803552, 3.8104408, 9.707379, 3.8104408, 17.87282);
((GeneralPath)shape).curveTo(3.8104408, 26.03826, 10.437465, 32.665283, 18.602905, 32.665283);
((GeneralPath)shape).curveTo(22.093708, 32.665283, 25.175081, 31.276417, 27.70596, 29.251638);
((GeneralPath)shape).curveTo(27.49992, 30.261774, 27.627672, 31.293585, 28.464546, 32.020485);
((GeneralPath)shape).lineTo(39.464073, 41.57869);
((GeneralPath)shape).curveTo(40.701477, 42.653484, 42.561516, 42.51266, 43.636307, 41.275257);
((GeneralPath)shape).curveTo(44.711098, 40.037853, 44.570274, 38.177814, 43.33287, 37.103024);
((GeneralPath)shape).lineTo(32.333347, 27.544815);
((GeneralPath)shape).curveTo(31.659649, 26.959652, 30.835642, 26.786402, 30.019653, 26.900017);
((GeneralPath)shape).curveTo(32.012775, 24.379473, 33.39537, 21.333277, 33.39537, 17.87282);
((GeneralPath)shape).curveTo(33.39537, 9.707379, 26.768345, 3.0803552, 18.602905, 3.0803552);
((GeneralPath)shape).closePath();
((GeneralPath)shape).moveTo(18.527046, 6.266424);
((GeneralPath)shape).curveTo(24.808153, 6.2664247, 29.905865, 11.364135, 29.905865, 17.645243);
((GeneralPath)shape).curveTo(29.905865, 23.926352, 24.808153, 29.024061, 18.527046, 29.024061);
((GeneralPath)shape).curveTo(12.245938, 29.024061, 7.1482277, 23.926352, 7.1482277, 17.645243);
((GeneralPath)shape).curveTo(7.1482277, 11.364135, 12.245938, 6.266424, 18.527046, 6.266424);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_12_1);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_2 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12_2
paint = new LinearGradientPaint(new Point2D.Double(30.65625, 34.0), new Point2D.Double(33.21875, 31.0625), new float[] {0.0f,0.5f,1.0f}, new Color[] {new Color(125, 125, 125, 255),new Color(177, 177, 177, 255),new Color(104, 104, 104, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.334589958190918f, 0.0f, 0.0f, 1.291290044784546f, -6.973840236663818f, -7.460659980773926f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(39.507004, 41.57769);
((GeneralPath)shape).curveTo(39.02833, 39.304504, 40.904335, 36.76627, 43.091057, 36.789314);
((GeneralPath)shape).curveTo(43.091057, 36.789314, 32.33069, 27.531204, 32.33069, 27.531204);
((GeneralPath)shape).curveTo(29.385899, 27.474499, 28.061188, 29.80382, 28.553877, 32.131126);
((GeneralPath)shape).lineTo(39.507004, 41.57769);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_12_2);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_3 = g.getTransform();
g.transform(new AffineTransform(1.2457400560379028f, 0.0f, 0.0f, 1.2457400560379028f, -3.4253499507904053f, -6.177030086517334f));
// _0_0_12_3
paint = new LinearGradientPaint(new Point2D.Double(18.292673110961914, 13.602121353149414), new Point2D.Double(17.500892639160156, 25.74346923828125), new float[] {0.0f,0.5f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 56),new Color(255, 255, 255, 255)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
stroke = new BasicStroke(1.1216413f,1,0,10.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(28.549437, 18.920233);
((GeneralPath)shape).curveTo(28.549437, 25.022175, 23.602835, 29.968777, 17.500893, 29.968777);
((GeneralPath)shape).curveTo(11.398951, 29.968777, 6.4523487, 25.022175, 6.4523487, 18.920233);
((GeneralPath)shape).curveTo(6.4523487, 12.818291, 11.398951, 7.871689, 17.500893, 7.871689);
((GeneralPath)shape).curveTo(23.602835, 7.871689, 28.549437, 12.818291, 28.549437, 18.920233);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_12_3);
g.setComposite(AlphaComposite.getInstance(3, 0.4331551f * origAlpha));
AffineTransform defaultTransform__0_0_12_4 = g.getTransform();
g.transform(new AffineTransform(0.7529860138893127f, 0.658037006855011f, -0.6489019989967346f, 0.7608720064163208f, 0.0f, 0.0f));
// _0_0_12_4
paint = new Color(255, 255, 255, 255);
stroke = new BasicStroke(1.3973206f,1,0,10.0f,null,0.0f);
shape = new RoundRectangle2D.Double(40.373348236083984, 0.14086054265499115, 19.048444747924805, 4.440478324890137, 5.971015930175781, 4.440478324890137);
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_12_4);
g.setComposite(AlphaComposite.getInstance(3, 1.0f * origAlpha));
AffineTransform defaultTransform__0_0_12_5 = g.getTransform();
g.transform(new AffineTransform(1.39860999584198f, 0.0f, 0.0f, 1.39860999584198f, -6.224339962005615f, -8.298959732055664f));
// _0_0_12_5
paint = new RadialGradientPaint(new Point2D.Double(18.240928649902344, 21.8179874420166), 8.308505f, new Point2D.Double(18.240928649902344, 21.8179874420166), new float[] {0.0f,1.0f}, new Color[] {new Color(114, 159, 207, 53),new Color(114, 159, 207, 172)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(25.897785, 18.478292);
((GeneralPath)shape).curveTo(25.89879, 21.447306, 24.315418, 24.191217, 21.744347, 25.676014);
((GeneralPath)shape).curveTo(19.173273, 27.160812, 16.00529, 27.160812, 13.434216, 25.676014);
((GeneralPath)shape).curveTo(10.863142, 24.191217, 9.27977, 21.447306, 9.280776, 18.478292);
((GeneralPath)shape).curveTo(9.27977, 15.509279, 10.863142, 12.7653675, 13.434216, 11.28057);
((GeneralPath)shape).curveTo(16.00529, 9.7957735, 19.173273, 9.7957735, 21.744347, 11.28057);
((GeneralPath)shape).curveTo(24.315418, 12.7653675, 25.89879, 15.509279, 25.897785, 18.478292);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
paint = new Color(48, 99, 163, 255);
stroke = new BasicStroke(0.99904466f,1,0,10.0f,null,0.0f);
shape = new GeneralPath();
((GeneralPath)shape).moveTo(25.897785, 18.478292);
((GeneralPath)shape).curveTo(25.89879, 21.447306, 24.315418, 24.191217, 21.744347, 25.676014);
((GeneralPath)shape).curveTo(19.173273, 27.160812, 16.00529, 27.160812, 13.434216, 25.676014);
((GeneralPath)shape).curveTo(10.863142, 24.191217, 9.27977, 21.447306, 9.280776, 18.478292);
((GeneralPath)shape).curveTo(9.27977, 15.509279, 10.863142, 12.7653675, 13.434216, 11.28057);
((GeneralPath)shape).curveTo(16.00529, 9.7957735, 19.173273, 9.7957735, 21.744347, 11.28057);
((GeneralPath)shape).curveTo(24.315418, 12.7653675, 25.89879, 15.509279, 25.897785, 18.478292);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.setStroke(stroke);
g.draw(shape);
g.setTransform(defaultTransform__0_0_12_5);
g.setComposite(AlphaComposite.getInstance(3, 0.8342246f * origAlpha));
AffineTransform defaultTransform__0_0_12_6 = g.getTransform();
g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
// _0_0_12_6
paint = new RadialGradientPaint(new Point2D.Double(15.4143705368042, 13.078408241271973), 6.65625f, new Point2D.Double(15.4143705368042, 13.078408241271973), new float[] {0.0f,1.0f}, new Color[] {new Color(255, 255, 255, 255),new Color(255, 255, 255, 63)}, MultipleGradientPaint.CycleMethod.NO_CYCLE, MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(2.5929598808288574f, -7.746899656918026E-24f, -5.714440160952708E-24f, 2.2520999908447266f, -25.05970001220703f, -18.94099998474121f));
shape = new GeneralPath();
((GeneralPath)shape).moveTo(18.156916, 7.3966937);
((GeneralPath)shape).curveTo(12.949325, 7.3966937, 8.732368, 11.613651, 8.732368, 16.821241);
((GeneralPath)shape).curveTo(8.732368, 18.325216, 9.152676, 19.709015, 9.77954, 20.971144);
((GeneralPath)shape).curveTo(11.03192, 21.432756, 12.362297, 21.746826, 13.774307, 21.746826);
((GeneralPath)shape).curveTo(19.945263, 21.746826, 24.873589, 16.88519, 25.254414, 10.809698);
((GeneralPath)shape).curveTo(23.523449, 8.764167, 21.044374, 7.3966937, 18.156916, 7.3966937);
((GeneralPath)shape).closePath();
g.setPaint(paint);
g.fill(shape);
g.setTransform(defaultTransform__0_0_12_6);
g.setTransform(defaultTransform__0_0_12);
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
	public file_print_preview() {
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

