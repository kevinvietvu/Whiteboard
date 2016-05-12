import java.awt.*;

import java.awt.geom.Point2D;

public class DText extends DShape {
	static GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static Font[] fonts = ge.getAllFonts();
    double fontSize = 1.0;
    String text = ControlPanel.textDisplay.getText();
    String fontType =  ((Font) ControlPanel.fonts.getSelectedItem()).getName();
    Font selectedFont;
    FontMetrics metrics;
    int fontHeight;
    int fontDescent;
    
	public DText()
	{
		super();
	}
	
	public Font computeFont(Graphics g)
	{
		while (info.getHeight() > fontHeight)
		{	
			fontSize = (fontSize * 1.10) + 1;
			selectedFont = new Font(fontType, Font.PLAIN, (int) fontSize);
			metrics = g.getFontMetrics(selectedFont);
			fontHeight = metrics.getHeight();
			fontDescent = metrics.getDescent();
			fontSize = (fontSize * 1.10) + 1;
		}
		
		return selectedFont;
		
	}
	
	//RenderingHints makes the text smoother and less edgy I think...
	public void draw(Graphics g)
	{	
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

	    rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

	    ((Graphics2D) g).setRenderingHints(rh);

	    selectedFont = computeFont(g);
		
	    g.setFont(selectedFont);
	           
	    g.setColor(info.getC());
	    
	    g.drawRect(info.getX(),(int) (info.getY()),info.getWidth(),info.getHeight());
	    
	    g.setClip(info.getX(),(int) (info.getY()),info.getWidth(),info.getHeight() * 2);
	    
	    g.drawString(text, info.getX() , info.getY() + info.getHeight() - fontDescent  );

	}
	
	public boolean contains(Point2D p)
	{
		if (p.getX() < this.info.getWidth() +  this.info.getX() - 1 && p.getX() >  this.info.getX() + 1 && p.getY() <  this.info.getHeight() +  this.info.getY() - 1
		&& p.getY() >  this.info.getY() + 1)
		{
			return true;
		}
		return false;
	}
	
	public String getName()
	{
		return "DText";
	}
	
}