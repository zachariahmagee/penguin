package zm.penguin.containers;

import zm.penguin.components.Button;
import zm.penguin.components.Component;
import zm.penguin.components.TextInput;
import zm.penguin.components.icons.CloseIcon;
import zm.penguin.interactions.KeyListenable;
import zm.penguin.interactions.Selectable;

import java.util.function.Consumer;

import static processing.core.PApplet.println;
import static processing.core.PConstants.*;
import static zm.penguin.styles.Style.button_h;
import static zm.penguin.styles.Style.button_w;
import static zm.penguin.styles.Theme.*;

public class PromptedInput extends Container<Component> implements KeyListenable, Selectable {
    public Boolean selected;
    public String prompt = "";
    public TextInput input;
    public Button enter, reset;
    public CloseIcon close;

    public Consumer<String> result;
    public Runnable resetAction = () -> {};

    int textColor = tab_text;

    public PromptedInput(int l, int t, int w, int h, String prompt) {
        super();
        setLocation(l,t,w,h);
        this.prompt = prompt;
        spacing = 10;
        enter = new Button("Done",r - button_w - spacing, b - button_h - spacing,  ()->{
            enter.flash = true;
            if (!this.input.isBlank()) {
                input.done();
                done();
            }
            setVisibility(false);
        });

        reset = new Button("Reset", l + spacing, b - button_h - spacing, ()->{
            reset.flash = true;
            input.reset();
            reset();
        });

        close = new CloseIcon(this.r,this.t,()->{
            this.input.reset();
            setVisibility(false);
        });

        input = new TextInput(l + spacing, t + h / 2 - button_h / 2, w - spacing * 2, button_h);

        add(enter, reset, close, input);

        this.s = outline;
        this.f = panel;
    }

    public PromptedInput(int l, int t, int w, int h, String prompt, Consumer<String> result) {
        this(l,t,w,h,prompt);
        this.result = result;
    }

    public PromptedInput(int l, int t, String prompt) {
        this(l, t,350,150, prompt);
    }

    public PromptedInput(int l, int t, String prompt, Consumer<String> result) {
        this(l, t,350,150, prompt, result);
    }

    @Override
    public void draw() {
        try {
            app.rectMode(CORNER);
            app.stroke(s);
            app.strokeWeight(1);
            app.fill(panel);
            app.rect(l, t, w, h, cornerRadius);

            app.textAlign(LEFT, CENTER);
            app.fill(textColor); //tab_text
            app.textSize(13);

            app.text(prompt, l + spacing, t + (float)h / 2 - button_h * 2, w - spacing * 2, button_h * 1.5f);

            for (Component c : this) c.draw();

        } catch (Exception e) {
            println(this, "(draw)", e);
        }

    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setResetAction(Runnable reset) {
        this.resetAction = reset;
    }

    public void setResult(Consumer<String> result) {
        this.result = result;
    }

    public void reset() {
        resetAction.run();
    }

    public void done() {
        result.accept(input.input);
    }


    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x,y);
        enter.setLocation(r - button_w - spacing, b - button_h - spacing);
        reset.setLocation(l + spacing, b - button_h - spacing);
        close.setLocation(r,t);
        input.setLocation(l + spacing, t + h / 2 - button_h * 2);
    }

    @Override
    public String toString() {
        return "PromptedInput:";
    }

    @Override
    public void keyPressed(char c, int keycode, boolean shift) {
        if (input.selected) input.keyPressed(c, keycode, shift);
    }

    @Override
    public void keyReleased(char c, int keycode) {
        if (input.selected) input.keyReleased(c,keycode);
    }

    @Override
    public boolean selected() {
        return input.selected;
    }

    @Override
    public void deselect() {
        input.deselect();
    }
}
