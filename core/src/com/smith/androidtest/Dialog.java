package com.smith.androidtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;

public class Dialog extends Group {
    
    private Texture background = new Texture("dialog_background.png");
    private Text information;

    public Dialog(String information, boolean visible) {
        setVisible(visible);
        this.information = new Text.TextBuilder()
                .withFont(Text.generateFont(Gdx.files.internal("fonts/germanica/Shadowed Germanica.ttf"), 48), Color.BLACK)
                .alignBy(Align.center)
                .withText(information)
                .atPosition(getX(), getY() + background.getHeight() * 0.9f / 2)
                .build();
        
        addActor(this.information);
    }

    public void addButton(Button button) {
        addActor(button);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(background, getX() - background.getWidth() / 2, getY() - background.getHeight() / 2);
        super.draw(batch, parentAlpha);
    }

    public void setText(String text) {
        this.information.setValue(text);
    }
}
