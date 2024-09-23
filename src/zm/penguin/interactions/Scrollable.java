package zm.penguin.interactions;

import zm.penguin.components.Component;
import zm.penguin.containers.Container;
import zm.penguin.utils.Layout;

import static processing.core.PConstants.HAND;
import static processing.core.PConstants.TEXT;
import static zm.penguin.styles.Theme.*;

public abstract class Scrollable<T extends Component> extends Container<T> {

    protected Layout layout = Layout.VERTICAL;
    public ScrollBar scroll;
    protected int scrollAmount;

    public int contentHeight = 0;
    public int contentWidth = 0;

    public int scrollbar_bg = idle;
    public int scrollbar_f  = lightgrey;
//    public int startingPoint = 0;
//    int contentSize = 0;

    @Override
    public Container<T> add(T...args) {
        for (T c : args) {
            this.components.add(c);
            if (layout.isVertical()) {
                this.contentHeight += c.getHeight() + spacing;
            } else {
                this.contentWidth += c.getWidth() + spacing;
            }
        }
        return this;
    }

    @Override
    public Container<T> add(T c) {
        this.components.add(c);
        //this.contentHeight += c.getHeight() + spacing;
        if (layout.isVertical()) {
            this.contentHeight += c.getHeight() + spacing;
        } else {
            this.contentWidth += c.getWidth() + spacing;
        }
        return this;
    }

    //@Override
    public void add(int index, T c) {
        components.add(index, c);
    }

    @Override
    public void clear() {
        this.components.clear();
        this.contentHeight = 0;
    }

    public int getContentHeight() {
        return contentHeight;
    }

    public int getContentWidth() {
        this.contentWidth = 0;
        for (Component c : components) {
            contentWidth += c.getWidth();
        }
        return contentWidth;
    }

    public int getAverageComponentHeight() {
        return contentHeight / components.size();
    }

    public int getAverageComponentWidth() {
        return contentWidth / components.size();
    }



    public int getContentHeight(int index) {
        int contentheight = 0;
        for (int i = 0; i < index; i++) {
            contentheight += components.get(i).getHeight() + spacing;
        }
        return contentheight;
    }

    @Override
    public void click(int x, int y) {
        if (scrollAmount != -1 && scroll.mouseOver(x,y)) {
            scroll.locked = true;
        } else {
            super.click(x, y);
        }
    }

    @Override
    public void mouseDragged(int x, int y) {
        if (scroll.locked) {
            scrollbarUpdate(x, y);
        }

        super.mouseMoved(x,y);
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (scroll.mouseOver(x,y)) app.cursor(HAND);
        else app.cursor(TEXT);

        super.mouseMoved(x,y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        if (scroll.locked) scroll.locked = false;
    }

    public  void scrollWheel(float amount) {
        if (layout.isVertical()) {
            if (mouseOver(app.mouseX, app.mouseY)//app.mouseX >= l && app.mouseX <= r && app.mouseY >= t && app.mouseY <= b
                    && scrollAmount != -1) {
                scrollAmount += (int) (25 * amount);
                if (scrollAmount < 0) scrollAmount = 0;
                else if (scrollAmount > contentHeight - (app.height - (b-t))) scrollAmount = contentHeight - (app.height - (b-t));
            }
        } else {
            if (mouseOver(app.mouseX, app.mouseY) // app.mouseX >= l && app.mouseX <= r && app.mouseY <= b
                    && scrollAmount != -1) {
                scrollAmount += (int) (25 * amount);
                if (scrollAmount < 0) scrollAmount = 0;
                else if (scrollAmount > contentWidth - (app.width - (r-l))) scrollAmount = contentWidth - (app.width - (r-l));
            }
        }
    }
    public  void scrollbarUpdate(int x, int y) {
        if (scroll.active()) {
//            int previousScroll = scrollAmount;
            if (layout.isVertical()) {
                scrollAmount = scroll.move(x, y, scrollAmount, 0, contentHeight - (app.height - (b-t)));
                //if (previousScroll != scrollAmount) redrawUI = true;
            } else {
                scrollAmount = scroll.move(x, y, scrollAmount, 0, contentWidth - (app.width - w));
                //if (previousScroll != menuScroll) redrawContent = true; this.draw();
            }
        }
    }
}