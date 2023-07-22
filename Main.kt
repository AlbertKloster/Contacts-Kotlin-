package contacts

fun main() {
    val phoneBookService = PhoneBookService()
    while (true) {
        print("Enter action (add, remove, edit, count, list, exit): ")
        when (Actions.getAction(readln())) {
            Actions.ADD -> phoneBookService.add()
            Actions.REMOVE -> phoneBookService.remove()
            Actions.EDIT -> phoneBookService.edit()
            Actions.COUNT -> phoneBookService.count()
            Actions.LIST -> phoneBookService.list()
            Actions.EXIT -> break
        }
    }
}