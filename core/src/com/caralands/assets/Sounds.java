package com.caralands.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public static Music equip_metal, equip_cloth;
    public static Music impact_metal_soft;

    public static Music impact_wood_hard, impact_wood_break;

    public Sounds(){

    }

    public static void loadSounds(){
        equip_metal = Gdx.audio.newMusic(Gdx.files.internal("sounds/equip-metal.mp3"));
        equip_cloth = Gdx.audio.newMusic(Gdx.files.internal("sounds/equip-cloth.mp3"));

        impact_metal_soft = Gdx.audio.newMusic(Gdx.files.internal("sounds/impact-metal-soft.mp3"));
        impact_wood_hard = Gdx.audio.newMusic(Gdx.files.internal("sounds/impact-wood-hard.mp3"));
        impact_wood_break = Gdx.audio.newMusic(Gdx.files.internal("sounds/impact-wood-break.mp3"));
    }

}
