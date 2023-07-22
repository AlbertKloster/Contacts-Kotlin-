package contacts

import java.time.LocalDate
import java.time.LocalDateTime

class RecordPerson(
    var name: String = "",
    var surname: String = "",
    var birth: LocalDate? = null,
    var gender: Genders? = null,
    number: String = "",
    timeCreated: LocalDateTime? = null,
    timeLastEdit: LocalDateTime? = null
): Record(number, timeCreated, timeLastEdit) {
    override fun toString(): String {
        return "RecordPerson${0.toChar()}$name${0.toChar()}$surname${0.toChar()}$birth${0.toChar()}$gender${0.toChar()}$number${0.toChar()}$timeCreated${0.toChar()}$timeLastEdit"
    }
}
