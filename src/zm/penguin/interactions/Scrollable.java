package zm.penguin.interactions;
import zm.penguin.components.Component;
import zm.penguin.containers.Container;
import zm.penguin.utils.Layout;

public abstract class Scrollable extends Container<Component> {

    Layout layout = Layout.VERTICAL;
    ScrollBar scroll;

    int scrollAmount;

    int contentHeight = 0;
    int contentWidth = 0;

//    int contentSize = 0;

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