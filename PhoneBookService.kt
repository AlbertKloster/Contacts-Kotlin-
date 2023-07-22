package contacts

class PhoneBookService {
    val phoneBook = PhoneBook()

    fun addRecord() {
        println("Enter the name of the person:")
        val name = readln()

        println("Enter the surname of the person:")
        val surname = readln()

        println("Enter the number:")
        val phoneNumber = readln()

        phoneBook.records.add(Record(name, surname, phoneNumber))

        println("A record created!")
    }

}