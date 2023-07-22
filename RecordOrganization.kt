package contacts

import java.time.LocalDateTime

class RecordOrganization(
    var organizationName: String = "",
    var address: String = "",
    number: String = "",
    timeCreated: LocalDateTime? = null,
    timeLastEdit: LocalDateTime? = null
): Record(number, timeCreated, timeLastEdit) {
    override fun toString(): String {
        return "RecordOrganization${0.toChar()}$organizationName${0.toChar()}$address${0.toChar()}$number${0.toChar()}$timeCreated${0.toChar()}$timeLastEdit"
    }
}