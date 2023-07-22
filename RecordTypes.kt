package contacts

enum class RecordTypes(val string: String) {
    PERSON("person"), ORGANIZATION("organization");

    companion object {
        fun getType(input: String): RecordTypes {
            for (type in RecordTypes.values()) {
                if (type.string == input.lowercase()) return type
            }
            throw RuntimeException("Bad type!")
        }
    }
}