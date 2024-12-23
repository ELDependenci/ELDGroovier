package com.ericlam.mc.groovier.bungee

import com.ericlam.mc.eld.bungee.ELDLifeCycle
import com.ericlam.mc.groovier.GroovierAPI
import com.ericlam.mc.groovier.GroovierCore
import com.ericlam.mc.groovier.ScriptLoadingException
import com.ericlam.mc.groovier.ScriptPlugin
import com.google.inject.Injector
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.plugin.Command
import net.md_5.bungee.api.plugin.Plugin

import javax.inject.Inject

class BungeeLifeCycle implements ELDLifeCycle {

    @Inject
    private GroovierAPI core

    @Inject
    private Injector injector

    @Override
    void onEnable(Plugin plugin) {
        (core as GroovierCore).onEnable(injector)
        var groovierCommand = new Command("groovier", "groovier.use") {
            @Override
            void execute(CommandSender sender, String[] args) {
                if (hasPermission(sender)) {
                    sender.sendMessage(TextComponent.fromLegacy("${ChatColor.RED}no permission."))
                }
                if (args.length == 0) {
                    sender.sendMessage(TextComponent.fromLegacy("Usage: /groovier reload | version"))
                    return
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    (core as GroovierCore).reloadAllScripts().whenComplete((v, ex) -> {
                        if (ex != null) {
                            if (ex instanceof ScriptLoadingException) {
                                sender.sendMessage(TextComponent.fromLegacy("${ChatColor.GOLD}Script is still loading, please wait until complete."))
                            } else {
                                sender.sendMessage(TextComponent.fromLegacy("${ChatColor.RED}Failed to reload scripts: " + ex.getMessage()))
                                ex.printStackTrace()
                            }
                        } else {
                            sender.sendMessage(TextComponent.fromLegacy("${ChatColor.GREEN}Successfully reloaded scripts"))
                        }
                    })

                    return
                }
                if (args[0].equalsIgnoreCase("version")) {
                    sender.sendMessage(TextComponent.fromLegacy("Groovier v${plugin.description.version} by ${plugin.description.author}  [ELD Edited]"))
                    return
                }
                sender.sendMessage(TextComponent.fromLegacy("Usage: /groovier reload | version"))
            }
        }

        plugin.proxy.pluginManager.registerCommand(plugin, groovierCommand)
    }

    @Override
    void onDisable(Plugin plugin) {
        (core as GroovierCore).onDisable()
    }
}
