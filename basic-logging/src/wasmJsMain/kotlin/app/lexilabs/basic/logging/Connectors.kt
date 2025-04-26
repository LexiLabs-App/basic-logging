@file:JsModule("logger.js")

package app.lexilabs.basic.logging

@JsFun("(message) => console.debug(message)")
public external fun debug(message: String): Unit = definedExternally

@JsFun("(message) => console.log(message)")
public external fun log(message: String): Unit = definedExternally

@JsFun("(message) => console.warn(message)")
public external fun warn(message: String): Unit = definedExternally

@JsFun("(message) => console.error(message)")
public external fun error(message: String): Unit = definedExternally
