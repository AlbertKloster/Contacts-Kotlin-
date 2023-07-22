package contacts

enum class Actions(val string: String) {
    ADD("add"),
    REMOVE("remove"),
    EDIT("edit"),
    COUNT("count"),
    INFO("info"),
    EXIT("exit"),
    ;

    companion object {
        fun getAction(input: String): Actions {
            for (action in Actions.values()) {
                if (action.string == input.trim().lowercase()) return action
            }
            throw RuntimeException("Wrong action $input")
        }
    }
}