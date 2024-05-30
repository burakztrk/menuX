package com.menu.x.menux

class CommandBuilder {
    private var command: String = ""
    private var subCommand: String = ""
    private val options = mutableListOf<String>()
    private val arguments = mutableListOf<String>()

    fun raw(cmd: String) = apply { command = cmd }

    fun command(cmd: String) = apply { command = cmd }

    fun subCommand(subCmd: String) = apply { subCommand = subCmd }

    fun option(shortOption: String) = apply { options.add("-$shortOption") }

    fun optionWithArg(shortOption: String, value: String) = apply { options.add("-$shortOption $value") }

    fun longOption(longOption: String, value: String? = null) = apply {
        if (value != null) {
            options.add("--$longOption=$value")
        } else {
            options.add("--$longOption")
        }
    }

    fun longOptionWithArg(longOption: String, value: String) = apply {
        options.add("--$longOption=$value")
    }

    fun argument(arg: String) = apply { arguments.add(arg) }

    fun build(): String = buildString {
        append(command)
        if (subCommand.isNotEmpty()) {
            append(subCommand)
        }
        if (options.isNotEmpty()) {
            append(options.joinToString(prefix = SPACE, separator = SPACE))
        }
        if (arguments.isNotEmpty()) {
            append(arguments.joinToString(prefix = SPACE, separator = SPACE))
        }
    }

    companion object {
        private const val SPACE = " "
    }
}


