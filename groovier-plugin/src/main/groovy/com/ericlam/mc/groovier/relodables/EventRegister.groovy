package com.ericlam.mc.groovier.relodables

import com.ericlam.mc.groovier.ScriptValidator

interface EventRegister extends ScriptValidator {

    void register(Set<Class<?>> eventScripts);

    void unregister(Set<Class<?>> eventScripts);

}
