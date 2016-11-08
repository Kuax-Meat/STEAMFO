import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollBar extends BasicScrollBarUI {
	@Override
	protected void configureScrollBarColors()
	{
		LookAndFeel.installColors(scrollbar, "ScrollBar.background",
		"ScrollBar.foreground");
		thumbHighlightColor = new Color(0, 0, 0);
		thumbLightShadowColor = new Color(0, 0, 0);
		thumbDarkShadowColor = new Color(0, 0, 0);
		thumbColor = new Color(0, 0, 0);
		trackColor = new Color(0, 0, 0);
		trackHighlightColor = new Color(0, 0, 0);
	}
	
	@Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(trackBounds.x - 17, trackBounds.y, trackBounds.width + 34, trackBounds.height);
        if (trackHighlight == DECREASE_HIGHLIGHT) {
        	paintDecreaseHighlight(g);
        }
        else if (trackHighlight == INCREASE_HIGHLIGHT) {
        	paintIncreaseHighlight(g);
        }

    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
    	if(thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
    		return;
    	}
		int w = thumbBounds.width;
		int h = thumbBounds.height;

		g.translate(thumbBounds.x, thumbBounds.y);

		g.setColor(new Color(51, 51, 51));
		g.drawRoundRect(0, 0, w-6, h-10, 7, 7);
		g.setColor(new Color(51, 51, 51));
		g.fillRoundRect(0, 0, w-6, h-10, 7, 7);
		
		// Delete Scrollbar button(both sides...)
		scrollbar.remove(incrButton);
		scrollbar.remove(decrButton);
    }
}
