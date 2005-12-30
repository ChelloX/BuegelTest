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
package org.woped.editor.view.petrinet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexRenderer;
import org.woped.core.config.ConfigurationManager;
import org.woped.core.config.DefaultStaticConfiguration;

/**
 * @author <a href="mailto:slandes@kybeidos.de">Simon Landes </a> <br>
 *         <br>
 * 
 * 
 * 29.04.2003
 */
public class TransXOrSplitView extends TransSimpleView
{

    private TransXOrSplitRenderer renderer = new TransXOrSplitRenderer();

    /**
     * Constructor for OrSplitView.
     * 
     * @param cell
     * @param graph
     * @param mapper
     */
    public TransXOrSplitView(Object cell)
    {
        super(cell);
    }

    public Point2D getPerimeterPoint(Point2D source, Point2D p)
    {

        return super.getPerimeterPoint(source, p);

    }

    public CellViewRenderer getRenderer()
    {

        return renderer;

    }

    /**
     * @author <a href="mailto:slandes@kybeidos.de">Simon Landes </a> <br>
     * 
     * this inner class contains the Renderer information of an transition
     * 
     * 28.03.2003
     */
    private class TransXOrSplitRenderer extends VertexRenderer
    {

        public void paint(Graphics g)
        {

            /* Trigger hinzuf�gen */
            int b = borderWidth;
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            boolean tmp = selected;
            if (super.isOpaque())
            {
                //g.setColor(super.getBackground());
                g.fillRect(b - 1, b - 1, d.width - b, d.height - b);
            }
            try
            {
                setBorder(null);
                setOpaque(false);
                selected = false;
                super.paint(g);
            } finally
            {
                selected = tmp;
            }
            if (bordercolor != null)
            {
                g.setColor(bordercolor);
                g2.setStroke(new BasicStroke(b));
                g.drawRect(b, b, d.width - b - 1, d.height - b - 1);
            }
            if (selected)
            {
                //				g2.setStroke(GraphConstants.SELECTION_STROKE);
                g.setColor(ConfigurationManager.getConfiguration().getSelectionColor());
                g.drawRect(b, b, d.width - b - 1, d.height - b - 1);
            }
            // XOR Split Lines
            g.drawLine(d.width * 2 / 3, b, d.width * 2 / 3, d.height - b);
            g.drawLine(d.width * 2 / 3, b, d.width - b, d.height / 2);
            g.drawLine(d.width - b, d.height / 2, d.width * 2 / 3, d.height - b);
            if (isFireing())
            {
                g2.setColor(Color.RED);
                g2.setFont(DefaultStaticConfiguration.DEFAULT_TOKENGAME_FONT);
                g2.drawString("choose", 3, 20);
                g2.drawString("arc", 3, 27);
                g.setColor(Color.LIGHT_GRAY);
                g.drawLine(d.width * 2 / 3, b, d.width * 2 / 3, d.height - b);
                g.drawLine(d.width * 2 / 3, b, d.width - b, d.height / 2);
                g.drawLine(d.width - b, d.height / 2, d.width * 2 / 3, d.height - b);
            }
        }

        /**
         * @return
         */
        public boolean isActive()
        {
            return TransXOrSplitView.this.isActivated();
        }

        /**
         * @return
         */
        public boolean isFireing()
        {
            return TransXOrSplitView.this.isFireing();
        }
    }

    /**
     * @see org.woped.editor.core.view.AbstractElementView#paint()
     */
    public void paint()
    {}

    /**
     * @see org.woped.editor.core.view.AbstractElementView#refresh()
     */
    public void refresh()
    {}

}