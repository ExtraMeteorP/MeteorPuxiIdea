package com.meteor.meteorpuxiidea.common.handler;

import com.meteor.meteorpuxiidea.common.lib.LibMisc;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class PuxiSounds {

    private static final List<SoundEvent> EVENTS = new ArrayList<>();

    public static final SoundEvent SPACE_WALK = makeSoundEvent("music.spacewalk");
    public static final SoundEvent WILD_FIRE = makeSoundEvent("music.wildfire");

    private static SoundEvent makeSoundEvent(String name) {
        SoundEvent event = SoundEvent.createVariableRangeEvent(new ResourceLocation(LibMisc.MOD_ID, name));
        EVENTS.add(event);
        return event;
    }

    public static void init(BiConsumer<SoundEvent, ResourceLocation> r) {
        for (SoundEvent event : EVENTS) {
            r.accept(event, event.getLocation());
        }
    }

    private PuxiSounds() {}
}
