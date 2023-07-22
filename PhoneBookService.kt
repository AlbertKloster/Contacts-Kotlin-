package contacts

import java.time.LocalDate
import java.time.LocalDateTime


class PhoneBookService {
    private val fileHandler = FileHandler()
    private val phoneBook = PhoneBook()

    fun save() {
        fileHandler.save(phoneBook)
    }

    fun add() {
        print("Enter the type (person, organization): ")
        when (RecordTypes.getType(readln())) {
            RecordTypes.PERSON -> add(RecordPerson())
            RecordTypes.ORGANIZATION -> add(RecordOrganization())
        }
        println("The record added.")
    }

    private fun add(record: RecordPerson) {
        setName(record)
        setSurname(record)
        setBirthDate(record)
        setGender(record)
        setNumber(record)
        setTimeCreated(record)
        setTimeLastEdit(record)

        phoneBook.records.add(record)
    }

    private fun add(record: RecordOrganization) {
        setOrganizationName(record)
        setAddress(record)
        setNumber(record)
        setTimeCreated(record)
        setTimeLastEdit(record)

        phoneBook.records.add(record)
    }

    private fun String.isValidPhoneNumber(): Boolean {
        val firstGroupInBrackets = "\\+?\\([a-zA-Z0-9]+\\)[ -]?([a-zA-Z0-9]{2,}[ -]?)*".toRegex()
        val secondGroupInBrackets = "\\+?[a-zA-Z0-9]+[ -]?(\\([a-zA-Z0-9]{2,}\\))*[ -]?([a-zA-Z0-9]{2,}[ -]?)*".toRegex()
        val noBrackets = "\\+?[a-zA-Z0-9]+[ -]?([a-zA-Z0-9]{2,}[ -]?)+".toRegex()
        return this.matches(firstGroupInBrackets) ||
                this.matches(secondGroupInBrackets) ||
                this.matches(noBrackets)
    }

    fun search() {
        print("Enter search query: ")
        val query = readln()
        val resultList = phoneBook.records.filter { record ->
            if (record is RecordPerson) {
                record.name.lowercase().contains(query.lowercase()) ||
                        record.surname.lowercase().contains(query.lowercase()) ||
                        record.number.contains(query)
            } else {
                record as RecordOrganization
                record.organizationName.lowercase().contains(query.lowercase()) ||
                        record.number.contains(query)
            }
        }
        for (i in resultList.indices) {
            val record = resultList[i]
            if (record is RecordPerson) {
                println("${i + 1}. ${record.name} ${record.surname}")
            } else {
                val castedRecord = record as RecordOrganization
                println("${i + 1}. ${castedRecord.organizationName}")
            }
        }

        println("\n[search] Enter action ([number], back, again): ")
        when (val input = readln()) {
            "back" -> return
            "again" -> search()
            else -> {
                printRecord(phoneBook.records[input.toInt() - 1])
            }
        }
    }

    private fun printRecord(record: Record) {
        if (record is RecordPerson) printPerson(record)
        else printOrganization(record as RecordOrganization)

        println("\n[record] Enter action (edit, delete, menu): ")
        when (readln()) {
            "edit" -> edit(record)
            "delete" -> delete(record)
            "menu" -> return
        }
    }

    private fun delete(record: Record) {
        phoneBook.records.remove(record)
        println("The record deleted!")
        printRecord(record)
    }

    private fun edit(record: Record) {
        if (record is RecordPerson) {
            editRecordPerson(record)
        } else {
            editRecordOrganization(record)
        }

        println("Saved")

        printRecord(record)
    }

    private fun editRecordPerson(record: Record) {
        println("Select a field (name, surname, birth, gender, number): ")
        when (readln()) {
            "name" -> setName(record as RecordPerson)
            "surname" -> setSurname(record as RecordPerson)
            "birth" -> setBirthDate(record as RecordPerson)
            "gender" -> setGender(record as RecordPerson)
            "number" -> setNumber(record)
        }
    }

    private fun editRecordOrganization(record: Record) {
        println("Select a field (name, address, number): ")
        when (readln()) {
            "name" -> setOrganizationName(record as RecordOrganization)
            "address" -> setAddress(record as RecordOrganization)
            "number" -> setNumber(record)
        }
    }

    private fun setName(record: RecordPerson) {
        print("Enter name: ")
        record.name = readln()
    }

    private fun setSurname(record: RecordPerson) {
        print("Enter surname: ")
        record.surname = readln()
    }

    private fun setBirthDate(record: RecordPerson) {
        print("Enter the birth date: ")
        val birthDate = try {
            LocalDate.parse(readln())
        } catch (e: RuntimeException) {
            println("Bad birth date!")
            null
        }
        record.birth = birthDate
    }

    private fun setGender(record: RecordPerson) {
        print("Enter the gender (M, F): ")
        val gender = try {
            Genders.getGender(readln())
        } catch (e: RuntimeException) {
            println("Bad gender!")
            null
        }
        record.gender = gender
    }

    private fun setOrganizationName(record: RecordOrganization) {
        print("Enter the organization name: ")
        record.organizationName = readln()
    }

    private fun setAddress(record: RecordOrganization) {
        print("Enter the address: ")
        record.address = readln()
    }

    private fun setNumber(record: Record) {
        print("Enter number: ")
        val input = readln()
        val number = if (input.isValidPhoneNumber()) input else ""
        if (number.isEmpty())
            println("Wrong number format!")
        record.number = number
    }

    private fun setTimeCreated(record: Record) {
        record.timeCreated = LocalDateTime.now()
    }

    private fun setTimeLastEdit(record: Record) {
        record.timeLastEdit = LocalDateTime.now()
    }

    fun count() {
        println("The Phone Book has ${phoneBook.records.count()} records.")
    }

    fun list() {
        if (phoneBook.records.isEmpty()) {
            println("No records to list!")
            return
        }

        printList()

        print("\n[list] Enter action ([number], back): ")
        val input = readln()
        if (input == "back") {
            return
        } else {
            val record = phoneBook.records[input.toInt() - 1]
            printRecord(record)
        }

    }

    private fun printPerson(record: RecordPerson) {
        println("Name: ${record.name}")
        println("Surname: ${record.surname}")
        println("Birth date: ${if (record.birth == null) "[no data]" else record.birth}")
        println("Gender: ${if (record.gender == null) "[no data]" else record.gender?.string}")
        println("Number: ${record.number.ifEmpty { "[no data]" }}")
        println("Time created: ${record.timeCreated}")
        println("Time last edit: ${record.timeLastEdit}")
    }

    private fun printOrganization(record: RecordOrganization) {
        println("Organization name: ${record.organizationName}")
        println("Address: ${record.address}")
        println("Number: ${record.number.ifEmpty { "[no data]" }}")
        println("Time created: ${record.timeCreated}")
        println("Time last edit: ${record.timeLastEdit}")
    }

    private fun printList() {
        for (i in phoneBook.records.indices) {
            val record = phoneBook.records[i]
            if (record is RecordPerson) {
                println("${i + 1}. ${record.name} ${record.surname}")
            } else {
                val castedRecord = record as RecordOrganization
                println("${i + 1}. ${castedRecord.organizationName}")
            }
        }
    }

}