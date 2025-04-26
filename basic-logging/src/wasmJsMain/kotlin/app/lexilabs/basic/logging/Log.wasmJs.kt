package app.lexilabs.basic.logging

public actual object Log : Logger {

    actual override fun v(tag: String, message: String): Unit = debug("[$tag] $message")

    actual override fun d(tag: String, message: String): Unit = debug("[$tag] $message")

    actual override fun i(tag: String, message: String): Unit = log("[$tag] $message")

    actual override fun w(tag: String, message: String): Unit = warn("[$tag] $message")

    actual override fun e(tag: String, message: String): Unit = error("[$tag] $message")

    actual override fun wtf(tag: String, message: String): Unit = error("WHAT-THE-F***: [$tag] $message")
}