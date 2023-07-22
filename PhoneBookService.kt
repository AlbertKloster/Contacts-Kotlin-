package contacts

class PhoneBookService {
    private val phoneBook = PhoneBook()

    fun add() {
        print("Enter the name: ")
        val name = readln()

        print("Enter the surname: ")
        val surname = readln()

        print("Enter the number: ")
        val input = readln()
        val number = if (input.isValidPhoneNumber()) input else ""
        val record = Record(name, surname, number)
        if (record.number == "")
            println("Wrong number format!")
        phoneBook.records.add(record)

        println("The record added.")
    }

    private fun String.isValidPhoneNumber(): Boolean {
        val firstGoupInBrackets = "\\+?\\([a-zA-Z0-9]+\\)[ -]?([a-zA-Z0-9]{2,}[ -]?)*".toRegex()
        val secondGroupInBrackets = "\\+?[a-zA-Z0-9]+[ -]?(\\([a-zA-Z0-9]{2,}\\))*[ -]?([a-zA-Z0-9]{2,}[ -]?)*".toRegex()
        val noBrackets = "\\+?[a-zA-Z0-9]+[ -]?([a-zA-Z0-9]{2,}[ -]?)+".toRegex()
        return this.matches(firstGoupInBrackets) ||
                this.matches(secondGroupInBrackets) ||
                this.matches(noBrackets)
    }


    fun remove() {
        if (phoneBook.records.isEmpty()) {
            println("No records to remove!")
            return
        }

        list()
        println("Select a record: > ")
        phoneBook.records.removeAt(readln().toInt() - 1)
        println("The record removed!")

    }

    fun edit() {
        if (phoneBook.records.isEmpty()) {
            println("No records to edit!")
            return
        }

        list()
        println("Select a record: > ")
        val record = phoneBook.records[readln().toInt() - 1]

        println("Select a field (name, surname, number): ")
        when (readln()) {
            "name" -> setName(record)
            "surname" -> setSurname(record)
            "number" -> setNumber(record)
        }

        println("The record updated!")

    }

    private fun setName(record: Record) {
        println("Enter name: ")
        record.name = readln()
    }

    private fun setSurname(record: Record) {
        println("Enter surname: ")
        record.surname = readln()
    }

    private fun setNumber(record: Record) {
        println("Enter number: ")
        val input = readln()
        val number = if (input.isValidPhoneNumber()) input else ""
        if (number.isEmpty())
            println("Wrong number format!")
        record.number = number
    }

    fun count() {
        println("The Phone Book has ${phoneBook.records.count()} records.")
    }

    fun list() {
        if (phoneBook.records.isEmpty()) {
            println("No records to list!")
            return
        }

        for (i in phoneBook.records.indices) {
            println("${i + 1}. ${phoneBook.records[i].name} ${phoneBook.records[i].surname}, ${if (phoneBook.records[i].number.isEmpty()) "[no number]" else phoneBook.records[i].number}")
        }
    }

}