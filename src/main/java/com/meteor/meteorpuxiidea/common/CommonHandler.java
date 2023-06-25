package com.meteor.meteorpuxiidea.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class CommonHandler {

    public static void setup() {
        registerEvents();
    }

    private static void registerEvents() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }

}
