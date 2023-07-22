package contacts

fun main() {
    val phoneBookService = PhoneBookService()
    while (true) {
        print("\nEnter action (add, remove, edit, count, info, exit): ")
        when (Actions.getAction(readln())) {
            Actions.ADD -> phoneBookService.add()
            Actions.REMOVE -> phoneBookService.remove()
            Actions.EDIT -> phoneBookService.edit()
            Actions.COUNT -> phoneBookService.count()
            Actions.INFO -> phoneBookService.info()
            Actions.EXIT -> break
        }
    }
}
