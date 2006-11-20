/*
 * 
 * Copyright (C) 2004-2005, see @author in JavaDoc for the author 
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * For contact information please visit http://woped.ba-karlsruhe.de
 *
 */
package org.woped.core.model;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.PortView;
import org.woped.core.Constants;
import org.woped.core.config.ConfigurationManager;
import org.woped.core.utilities.LoggerManager;

/**
 * @author <a href="mailto:slandes@kybeidos.de">Simon Landes </a> <br>
 * 
 * 
 * 19.04.2003
 */
public class ArcModel extends DefaultEdge implements Serializable
{

    private String         id;

    private boolean        activated        = false;

    private Vector         unknownToolSpecs = new Vector();

    private ElementContext elementContext   = null;

    /**
     * Constructor for ArcModel.
     */
    public ArcModel()
    {
        this(null);
    }

    /**
     * Constructor for ArcModel.
     * 
     * @param userObject
     */
    public ArcModel(Object userObject)
    {
        super(userObject);
        this.elementContext = new ElementContext();
        initAttributes();
    }

    public void initAttributes()
    {
        AttributeMap map = getAttributes();
        GraphConstants.setEditable(map, false);
        GraphConstants.setBendable(map, true);
        GraphConstants.setLineStyle(map, ConfigurationManager.getConfiguration().isRoundRouting() ? GraphConstants.STYLE_BEZIER : GraphConstants.STYLE_ORTHOGONAL);
        GraphConstants.setEndFill(map, ConfigurationManager.getConfiguration().isFillArrowHead());
        GraphConstants.setEndSize(map, ConfigurationManager.getConfiguration().getArrowheadSize() == 0 ? ConfigurationManager.getStandardConfiguration().getArrowheadSize() : ConfigurationManager
                .getConfiguration().getArrowheadSize());
        GraphConstants.setLineWidth(map, ConfigurationManager.getConfiguration().getArrowWidth() == 0 ? ConfigurationManager.getStandardConfiguration().getArrowWidth() : ConfigurationManager
                .getConfiguration().getArrowWidth());
        GraphConstants.setDisconnectable(map, false);
        // GraphConstants.setRouting(map, Edge.)
        getAttributes().applyMap(map);
    }

    public int getWeight()
    {
        return Integer.parseInt((String) getUserObject());
    }

    public void setWeight(int weight)
    {
        setUserObject(String.valueOf(weight));
    }

    /**
     * Returns the inscriptionValue.
     * 
     * @return String
     */
    public int getInscriptionValue()
    {
        int i = 1;
        try
        {
            i = Integer.parseInt((String) getUserObject());
        } catch (Exception e)
        {
            i = 1;
        }
        return i == -1 ? 1 : i;
    }

    /**
     * Returns the sourceId.
     * 
     * @return Object
     */
    public String getSourceId()
    {
        DefaultPort port = ((DefaultPort) getSource());
        AbstractElementModel pnme = ((AbstractElementModel) port.getParent());
        return pnme.getId();
    }

    /**
     * Returns the id.
     * 
     * @return Object
     */
    public String getId()
    {
        return id;
    }

    /**
     * Sets the id.
     * 
     * @param id
     *            The id to set
     */
    public void setId(String id)
    {
        this.id = id;
    }

    public String getTargetId()
    {
        return ((AbstractElementModel) ((DefaultPort) getTarget()).getParent()).getId();
    }

    /**
     * Sets the inscriptionValue.
     * 
     * @param inscriptionValue
     *            The inscriptionValue to set
     */
    public void setInscriptionValue(String inscriptionValue)
    {
        setUserObject(inscriptionValue);
    }

    /**
     * TODO: Documentation
     * 
     * @param route
     */
    public void setRoute(boolean route)
    {
        AttributeMap map = this.getAttributes();
        if (route)
        {
            getAttributes().applyValue(GraphConstants.ROUTING, GraphConstants.ROUTING_SIMPLE);
            getAttributes().remove(GraphConstants.POINTS);
        } else
        {
            getAttributes().remove(GraphConstants.ROUTING);
            getAttributes().remove(GraphConstants.POINTS);
        }
    }

    public boolean isRoute()
    {
        return (!GraphConstants.ROUTING_DEFAULT.equals(GraphConstants.getRouting(getAttributes())));
        
    }

    /**
     * 
     * Adds point c to the arc at the position <code>index</code>.
     * 
     * @param c
     * @param index
     */
    public void addPoint(Point2D c, int index)
    {
        List points = GraphConstants.getPoints(getAttributes());
        if (points == null) {
            points=new Vector();
        }
        points.add(index, c);
        HashMap map = new HashMap();
        GraphConstants.setPoints(map, points);
        getAttributes().applyMap(map);
        LoggerManager.debug(Constants.CORE_LOGGER, "Point added " + c.toString());
    }

    /**
     * Adds point c to the arc. Calculates the right position
     * 
     * @param c
     */
    public void addPoint(Point2D c)
    {
        AttributeMap map = getAttributes();
        List points = GraphConstants.getPoints(map);
        if (points==null){
            points=new Vector();
            Point2D[] currentPoints = getPoints();
            for (int i=0;i<currentPoints.length;i++){
                points.add(currentPoints[i]);
            }
        }

        int index = 0;
        double min = Double.MAX_VALUE, dist = 0;
        for (int i = 0; i < points.size() - 1; i++)
        {
            Point2D p=null;
            Point2D p1=null;
            if  (points.get(i) instanceof Point2D){
                p= (Point2D)points.get(i);
            } else if (points.get(i) instanceof PortView){
                p=((PortView)points.get(i)).getLocation();
            }
            if  (points.get(i+1) instanceof Point2D){
                p1= (Point2D)points.get(i+1);
            } else if (points.get(i+1) instanceof PortView){
                p1=((PortView)points.get(i+1)).getLocation();
            }
            dist = new Line2D.Double(p, p1).ptLineDistSq(c);
            if (dist < min)
            {
                min = dist;
                index = i + 1;
            }
        }
        addPoint(c, index);
    }

    public Point2D[] getPoints()
    {
        AttributeMap map = getAttributes();
        List points = GraphConstants.getPoints(map);
        Point2D[] result = new Point2D[] {};
        if (points != null)
        {
            result = new Point2D[points.size()];
            for (int i = 0; i < points.size(); i++)
            {
                if (points.get(i) instanceof PortView){
                    result[i] = ((PortView) points.get(i)).getLocation();
                } else {
                    result[i] = (Point2D) points.get(i);
                }
            }
        }
        return result;
    }

    public void setPoints(Point2D[] points)
    {
        AttributeMap map = getAttributes();
        List pointList = GraphConstants.getPoints(map);
        if (pointList != null)
        {
            while (pointList.size() > 2)
                pointList.remove(1);
            for (int i = points.length - 1; i >= 0; i--)
            {
                pointList.add(1, points[i]);
            }
            GraphConstants.setPoints(map, pointList);
        }
    }

    /**
     * @param l
     */
    public void removePoint(Point2D l)
    {
        int pos = getPointPosition(l, 10);
        AttributeMap map = getAttributes();
        List points = GraphConstants.getPoints(map);
        points.remove(pos);
        GraphConstants.setPoints(map, points);
        getAttributes().applyMap(map);
        LoggerManager.debug(Constants.CORE_LOGGER, "Point removed");

    }

    public boolean hasPoint(Point2D p, int tolerance)
    {
        return (getPointPosition(p, tolerance)) != -1;
    }

    private int getPointPosition(Point2D p, int tolerance)
    {
        List points = GraphConstants.getPoints(getAttributes());
        int pos = -1;
        double dist = Double.MAX_VALUE;
        for (int i = 1; i < points.size() - 1; i++)
        {
            Point2D a = (Point2D) points.get(i);

            double tp = Point2D.distance(a.getX(), a.getY(), p.getX(), p.getY());
            if (tp < dist)
            {
                dist = tp;
                pos = i;
            }
        }
        if (dist < tolerance)
        {
            return pos;
        }
        return -1;
    }

    public static double getHeightC(Point2D A, Point2D B, Point2D C)
    {
        double distAC = Point2D.distance(A.getX(), A.getY(), C.getX(), C.getY());
        double thetaAB = Math.atan2(B.getY() - A.getY(), B.getX() - A.getX());
        double thetaAC = Math.atan2(C.getY() - A.getY(), C.getX() - A.getX());
        double height = Math.sin(thetaAC - thetaAB) * distAC;

        return height;
    }

    /**
     * @return Returns the activated.
     */
    public boolean isActivated()
    {
        return activated;
    }

    /**
     * @param activated
     *            The activated to set.
     */
    public void setActivated(boolean activated)
    {
        this.activated = activated;
    }

    public Vector getUnknownToolSpecs()
    {
        return unknownToolSpecs;
    }

    public void setUnknownToolSpecs(Vector unknownToolSpecs)
    {
        this.unknownToolSpecs = unknownToolSpecs;
    }

    public void addUnknownToolSpecs(Object unknownToolSpecs)
    {
        getUnknownToolSpecs().add(unknownToolSpecs);
    }

    public CreationMap getCreationMap()
    {
        CreationMap map = CreationMap.createMap();
        map.setArcId(getId());
        map.setArcRoute(isRoute());
        map.setArcSourceId(getSourceId());
        map.setArcTargetId(getTargetId());
        List points = GraphConstants.getPoints(getAttributes());
        Vector newPoints = new Vector();
        for (int i = 1; i < points.size() - 1; i++)
        {
            newPoints.add(new IntPair((int) ((Point2D) points.get(i)).getX(), (int) ((Point2D) points.get(i)).getY()));
        }
        map.setArcPoints(newPoints);
        return map;
    }

    public ElementContext getElementContext()
    {
        return elementContext;
    }

    public void setElementContext(ElementContext elementContext)
    {
        this.elementContext = elementContext;
    }
    // /**
    // * Generic edge router that will create bezier control points to separate
    // * overlapping edges between two cells.
    // *
    // * @author Dean Mao
    // * @created Sep 10, 2004
    // */
    // public static class EdgeRouter implements Edge.Routing
    // {
    // /**
    // * The distance between the control point and the middle line. A larger
    // * number will lead to a more "bubbly" appearance of the bezier edges.
    // */
    // private static final double EDGE_SEPARATION = 25;
    //
    // public void route(EdgeView edge, java.util.List points)
    // {
    // Object[] edges =
    // JGraphUtilities.getEdges(UserInterface.getInstance().getActiveEditor().getGraph().getModel());
    //            
    // //edge.getGraph(), edge.getSource().getParentView().getCell(),
    // // edge.getTarget().getParentView().getCell());
    //
    // // Find the position of the current edge that we are currently
    // // routing
    // int numEdges = edges.length;
    // int position = 0;
    // for (int i = 0; i < edges.length; i++)
    // {
    // Object e = edges[i];
    // if (e == edge.getCell())
    // {
    // position = i;
    // }
    // }
    //
    // // If there is only 1 edge between the two vertices, we don't need
    // // this
    // // special routing
    // if (edges.length < 2)
    // {
    // if (points.size() > 2)
    // {
    // points.remove(1);
    // }
    // return;
    // }
    //
    // // Find the end point positions
    // int n = points.size();
    // Point2D from = ((PortView) edge.getSource()).getLocation(null);
    // Point2D to = ((PortView) edge.getTarget()).getLocation(null);
    //
    // if (from != null && to != null)
    // {
    // double dy = from.getY() - to.getY();
    // double dx = from.getX() - to.getX();
    //
    // // calculate mid-point of the main edge
    // double midX = Math.min(from.getX(), to.getX()) + Math.abs((from.getX() -
    // to.getX()) / 2);
    // double midY = Math.min(from.getY(), to.getY()) + Math.abs((from.getY() -
    // to.getY()) / 2);
    //
    // // compute the normal slope. The normal of a slope is the
    // // negative
    // // inverse of the original slope.
    // double m = (from.getY() - to.getY()) / (from.getX() - to.getX());
    // double theta = Math.atan(-1 / m);
    //
    // // modify the location of the control point along the axis of
    // // the
    // // normal using the edge position
    // double r = EDGE_SEPARATION * (Math.floor(position / 2) + 1);
    // if ((position % 2) == 0)
    // {
    // r = -r;
    // }
    //
    // // convert polar coordinates to cartesian and translate axis to
    // // the mid-point
    // double ex = r * Math.cos(theta) + midX;
    // double ey = r * Math.sin(theta) + midY;
    // Point2D controlPoint = new Point2D.Double(ex, ey);
    //
    // // add the control point to the points list
    // if (points.size() == 2)
    // {
    // points.add(1, controlPoint);
    // } else
    // {
    // points.set(1, controlPoint);
    // }
    // }
    // }
    // }

}