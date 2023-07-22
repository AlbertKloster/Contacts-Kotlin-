# Stage 4/4: Searching
## Description
Our temporary solution in the previous stage was bad because of every time you want to interact with concrete classes you must check the Boolean property then apply different code according to the concrete class. So far so good, you implement this behavior every time you need to. However, in a larger application, there can be 100 places or more where you must do this. And at the end of the day, a new feature request might come in: implement a third type of records, something that represents an automated system with robots serving the calls. You would be very annoyed that you were forced to find all the places where you interact with concrete classes.

The solution to this problem is a <b>polymorphism</b>.

Basically, you need to implement the functionality in one place inside a concrete class. All of the derived classes should implement this method, and in the base class, there should be an abstract method. In the code, you actually call this abstract method and get different code executions in the derived classes.

To solve your problem, you should create several methods:
1. A method that returns all of the possible properties this class is able to change.
2. A method that takes a string that represents a property that the class is able to change and its new value.
3. A method that takes a string representation of the property and returns the value of this property.

After that, you don't need any Boolean workarounds and type casts; the code will be nice and clean.

Also, in this stage, you should implement saving to a file and loading from a file. You can save the Contacts using serialization. You should specify a file you are working with by a command-line argument. This would automatically save the Contacts on the hard drive after each action that modifies data. If you don't specify an argument, then you should create a new Contacts and keep it in memory. If you specify a file that doesn't exist, you should create an empty Contacts and save all changes to the newly created file.

Also, in this stage, you should implement search functionality. For this, you can append all of the values from all of the properties and check if this string contains a search request. It should support regular expressions, too! And, of course, it should be case insensitive.

Use an empty line to separate different actions, like in the example.

## Example
Below is an example of how your output might look. The symbol `>` represents the user input.
```
open phonebook.db

[menu] Enter action (add, list, search, count, exit): > count
The Phone Book has 6 records.

[menu] Enter action (add, list, search, count, exit): > search
Enter search query: > cent
Found 3 results:
1. Central Bank
2. Centurion Adams
3. Decent Pizza Shop

[search] Enter action ([number], back, again): > again
Enter search query: > shop
Found 2 results:
1. Decent Pizza Shop
2. Car shop

[search] Enter action ([number], back, again): > 2
Organization name: Car shop
Address: Wall St. 3
Number: +0 (123) 456-789-9999
Time created: 2018-01-01T00:03
Time last edit: 2018-04-29T11:34

[record] Enter action (edit, delete, menu): > edit
Select a field (name, address, number): > name
Enter name: > New Car Shop
Saved
Organization name: New Car Shop
Address: Wall St. 3
Number: +0 (123) 456-789-9999
Time created: 2018-01-01T00:03
Time last edit: 2018-11-20T11:04

[record] Enter action (edit, delete, menu): > menu

[menu] Enter action (add, list, search, count, exit): > search
Enter search query: > new
Found 1 result:
1. New Car Shop

[search] Enter action ([number], back, again): > back

[menu] Enter action (add, list, search, count, exit): > list
1. New Car Shop
2. Decent Pizza Shop
3. Central Bank
4. Centurion Adams
5. John Smith
6. Alice Wonderlanded

[list] Enter action ([number], back): > 6
Name: Alice
Surname: Wonderlanded
Birth date: [no data]
Gender: F
Number: +123123 (123) 12-23-34-45
Time created: 2018-03-12T11:21
Time last edit: 2018-03-12T11:21

[record] Enter action (edit, delete, menu): > edit
Select a field (name, surname, birth, gender, number): > number
Enter number: > +23 (321) 12-12 12 12
Saved
Name: Alice
Surname: Wonderlanded
Birth date: [no data]
Gender: F
Number: +23 (321) 12-12 12 12
Time created: 2018-03-12T11:21
Time last edit: 2018-11-20T11:07

[record] Enter action (edit, delete, menu): > menu

[menu] Enter action (add, list, search, count, exit): > exit
```