package contacts

fun main() {
    val phoneBookService = PhoneBookService()
    while (true) {
        print("\n[menu] Enter action (add, list, search, count, exit): ")
        when (Actions.getAction(readln())) {
            Actions.ADD -> phoneBookService.add()
            Actions.LIST -> phoneBookService.list()
            Actions.SEARCH -> phoneBookService.search()
            Actions.COUNT -> phoneBookService.count()
            Actions.EXIT -> break
        }
    }
    phoneBookService.save()
}
