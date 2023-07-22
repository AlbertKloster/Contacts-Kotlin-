package contacts

enum class Genders(val string: String) {
    M("M"), F("F");

    companion object {
        fun getGender(input: String): Genders {
            for (gender in Genders.values()) {
                if (gender.string == input.uppercase()) return gender
            }
            throw RuntimeException("Bad gender!")
        }
    }
}