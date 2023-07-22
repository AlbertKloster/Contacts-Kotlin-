package contacts

import java.time.LocalDate
import java.time.LocalDateTime

class PhoneBookService {
    private val phoneBook = PhoneBook()

    fun add() {
        when (RecordTypes.getType(readln())) {
            RecordTypes.PERSON -> addPerson()
            RecordTypes.ORGANIZATION -> addOrganization()
        }
        println("The record added.")
    }

    private fun addPerson() {
        val person = RecordPerson()

        setName(person)
        setSurname(person)
        setBirthDate(person)
        setGender(person)
        setNumber(person)
        setTimeCreated(person)
        setTimeLastEdit(person)

        phoneBook.records.add(person)
    }

    private fun addOrganization() {
        val organization = RecordOrganization()

        setOrganizationName(organization)
        setAddress(organization)
        setNumber(organization)
        setTimeCreated(organization)
        setTimeLastEdit(organization)

        phoneBook.records.add(organization)
    }

    private fun String.isValidPhoneNumber(): Boolean {
        val firstGroupInBrackets = "\\+?\\([a-zA-Z0-9]+\\)[ -]?([a-zA-Z0-9]{2,}[ -]?)*".toRegex()
        val secondGroupInBrackets = "\\+?[a-zA-Z0-9]+[ -]?(\\([a-zA-Z0-9]{2,}\\))*[ -]?([a-zA-Z0-9]{2,}[ -]?)*".toRegex()
        val noBrackets = "\\+?[a-zA-Z0-9]+[ -]?([a-zA-Z0-9]{2,}[ -]?)+".toRegex()
        return this.matches(firstGroupInBrackets) ||
                this.matches(secondGroupInBrackets) ||
                this.matches(noBrackets)
    }


    fun remove() {
        if (phoneBook.records.isEmpty()) {
            println("No records to remove!")
            return
        }

        printList()
        println("Select a record: > ")
        phoneBook.records.removeAt(readln().toInt() - 1)
        println("The record removed!")

    }

    fun edit() {
        if (phoneBook.records.isEmpty()) {
            println("No records to edit!")
            return
        }

        printList()
        println("Select a record: > ")
        val record = phoneBook.records[readln().toInt() - 1]

        if (record is RecordPerson) {
            editRecordPerson(record)
        } else {
            editRecordOrganization(record)
        }

        println("The record updated!")

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
        println("Select a field (address, number): ")
        when (readln()) {
            "address" -> setAddress(record as RecordOrganization)
            "number" -> setNumber(record)
        }
    }

    private fun setName(record: RecordPerson) {
        println("Enter name: ")
        record.name = readln()
    }

    private fun setSurname(record: RecordPerson) {
        println("Enter surname: ")
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
        println("Enter number: ")
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

    fun info() {
        if (phoneBook.records.isEmpty()) {
            println("No records to list!")
            return
        }

        printList()

        print("Enter index to show info: > ")
        val record = phoneBook.records[readln().toInt() - 1]
        if (record is RecordPerson)
            printPerson(record)
        else
            printOrganization(record as RecordOrganization)

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