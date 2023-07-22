# Stage 3/4: Upgrade your contacts
## Description
In this stage, you will write a program that can store not only information about people but also organizations.

In the real app, you can also store phone numbers of different companies, like your favorite pizza shop or your bank. These organizations don't have a name or a surname; they have an organization name and an address.

Additionally, a person can have a birth date and gender, but a company can't have that.

Let's use inheritance to solve this issue. There should be a base class containing information relevant to both an organization and a person, like a phone number. And there should be two classes that inherit this base class: a class that represents an organization and a class that represents a person.

The list of records should contain both people and organizations. You can accomplish that if the list with records contains elements of the base class, not the specific classes.

There is one problem with that: how can you edit the properties that correspond to specific classes, like the address of an organization? One of the solutions is to create a final Boolean property isPerson in the base class. After that, when editing the record, first check this property, then cast to the corresponding class and then edit the property. This is a bad, workaround solution, but we will write a more general solution in the next stage.

Also, in this stage, you can improve the base class so that it has more than one property. You should implement properties that store the date of this record creation and the date of the last edit.

> Use an empty line to separate different actions, like in the example.

## Example
Below is an example of how your output might look. The symbol `>` represents the user input.
```
Enter action (add, remove, edit, count, info, exit): > add
Enter the type (person, organization): > person
Enter the name: > John
Enter the surname: > Smith
Enter the birth date: >
Bad birth date!
Enter the gender (M, F): >
Bad gender!
Enter the number: > +0 (123) 456-789-ABcd
The record added.

Enter action (add, remove, edit, count, info, exit): > add
Enter the type (person, organization): > organization
Enter the organization name: > Pizza Shop
Enter the address: > Wall St. 1
Enter the number: > +0 (123) 456-789-9999
The record added.

Enter action (add, remove, edit, count, info, exit): > info
1. John Smith
2. Pizza Shop
Enter index to show info: > 2
Organization name: Pizza shop
Address: Wall St. 1
Number: +0 (123) 456-789-9999
Time created: 2018-01-01T00:00
Time last edit: 2018-01-01T00:00

Enter action (add, remove, edit, count, info, exit): > edit
1. John Smith
2. Pizza Shop
Select a record: > 1
Select a field (name, surname, birth, gender, number): > number
Enter number: > (123) 234 345-456
The record updated!

Enter action (add, remove, edit, count, info, exit): > info
1. John Smith
2. Pizza Shop
Select a record: > 1
Name: John
Surname: Smith
Birth date: [no data]
Gender: [no data]
Number: (123) 234 345-456
Time created: 2018-01-01T00:00
Time last edit: 2018-01-01T00:01

Enter action (add, remove, edit, count, info, exit): > edit
1. John Smith
2. Pizza Shop
Select a record: > 2
Select a field (address, number): > address
Enter address: > Wall St. 7
The record updated!

Enter action (add, remove, edit, count, info, exit): > info
1. John Smith
2. Pizza Shop
Enter index to show info: > 2
Organization name: Pizza shop
Address: Wall St. 7
Number: +0 (123) 456-789-9999
Time created: 2018-01-01T00:00
Time last edit: 2018-01-01T00:02

Enter action (add, remove, edit, count, info, exit): > exit
```
