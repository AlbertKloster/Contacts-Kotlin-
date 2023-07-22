package contacts

import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime

class FileHandler() {
    private val filename = "phonebook.db"

    fun save(phoneBook: PhoneBook) {
        val file = File(filename)

        if (file.exists()) file.delete()

        phoneBook.records.forEach {
            file.appendText(it.toString() + "\n")
        }
    }

    fun load(): PhoneBook {
        val phoneBook = PhoneBook()
        val file = File(filename)

        if (!file.exists()) return phoneBook

        file.readLines().forEach { line ->
            val parts = line.split(0.toChar())
            val record = when (parts[0]) {
                "RecordPerson" -> parsePerson(parts)
                "RecordOrganization" -> parseOrganization(parts)
                else -> null
            }
            if (record != null) phoneBook.records.add(record)
        }
        return phoneBook
    }

    private fun parsePerson(parts: List<String>): RecordPerson {
        val person = RecordPerson()
        person.name = parts[1]
        person.surname = parts[2]
        person.birth = if (parts[3] == "null") null else LocalDate.parse(parts[3])
        person.gender = if (parts[4] == "null") null else Genders.getGender(parts[4])
        person.number = parts[5]
        person.timeCreated = LocalDateTime.parse(parts[6])
        person.timeLastEdit = LocalDateTime.parse(parts[7])
        return person
    }

    private fun parseOrganization(parts: List<String>): RecordOrganization {
        val organization = RecordOrganization()
        organization.organizationName = parts[1]
        organization.address = parts[2]
        organization.number = parts[3]
        organization.timeCreated = LocalDateTime.parse(parts[4])
        organization.timeLastEdit = LocalDateTime.parse(parts[5])
        return organization
    }

}