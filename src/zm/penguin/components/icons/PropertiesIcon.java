package zm.penguin.components.icons;

import zm.penguin.components.Icon;

public class PropertiesIcon extends Icon {
    @Override
    public void draw() {
        app.noStroke();
        app.fill(s);

        app.rect(l + 5, t + d(h, 3) - 1.5f, 15, 1.5f);
        app.circle(l + 24, t + d(h, 3) - 0.75f, 3);
        app.rect(l + 17, t + h * 3f / 6 - 1, 8, 1.5f);
        app.circle(l + 14, t + h * 3f / 6, 3);
        app.rect(l + 5, t + h * 3f / 6 - 1, 5, 1.5f);
        app.rect(l + 5, t + h * 4f / 6, 15, 1.5f);
        app.circle(l + 24, t + h * 4f / 6 + 0.75f, 3);
    }
}
