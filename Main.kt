package contacts

fun main() {
    val phoneBookService = PhoneBookService()
    phoneBookService.addRecord()
    println("A Phone Book with a single record created!")
}