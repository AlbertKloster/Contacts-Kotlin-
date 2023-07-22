package contacts

import java.time.LocalDateTime

class RecordOrganization(
    var organizationName: String = "",
    var address: String = "",
    number: String = "",
    timeCreated: LocalDateTime? = null,
    timeLastEdit: LocalDateTime? = null
): Record(number, timeCreated, timeLastEdit)