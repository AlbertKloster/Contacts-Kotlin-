package contacts

import java.time.LocalDateTime

open class Record(var number: String = "", var timeCreated: LocalDateTime? = null, var timeLastEdit: LocalDateTime? = null)