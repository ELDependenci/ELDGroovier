package com.ericlam.mc.groovier.spigot

import com.ericlam.mc.eld.bukkit.ELDLifeCycle
import com.ericlam.mc.groovier.GroovierAPI
import com.ericlam.mc.groovier.GroovierCore
import com.ericlam.mc.groovier.ScriptPlugin
import com.google.inject.Injector
import org.bukkit.plugin.java.JavaPlugin

import javax.inject.Inject

class SpigotLifeCycle implements ELDLifeCycle {

    @Inject
    private GroovierAPI core;

    @Inject
    private Injector injector;

    @Override
    void onEnable(JavaPlugin javaPlugin) {
        (core as GroovierCore).onEnable(injector);
    }

    @Override
    void onDisable(JavaPlugin javaPlugin) {
        (core as GroovierCore).onDisable();
    }
}
