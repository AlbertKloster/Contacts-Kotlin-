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
): Record(number, timeCreated, timeLastEdit)
