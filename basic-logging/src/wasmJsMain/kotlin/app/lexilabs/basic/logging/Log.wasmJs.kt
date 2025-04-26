package app.lexilabs.basic.logging

public actual object Log : Logger {
    actual override fun v(tag: String, message: String) {
        js("console.debug('[$tag] $message')")
    }

    actual override fun d(tag: String, message: String) {
        js("console.debug('[$tag] $message')")
    }

    actual override fun i(tag: String, message: String) {
        js("console.log('[$tag] $message')")
    }

    actual override fun w(tag: String, message: String) {
        js("console.warn('[$tag] $message')")
    }

    actual override fun e(tag: String, message: String) {
        js("console.error('[$tag] $message')")
    }
    actual override fun wtf(tag: String, message: String) {
        js("console.error('WHAT-THE-F***: [$tag] $message')")
    }
}