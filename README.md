# How to run
### Not compiled
* Compile with `make`. The program will automatically run once compiled.

### Already compiled
* Run with `./run.sh` to use the default textfiles stored in the `./example` directory, or `./run.sh <path/to/goods> <path/to/payment>` for use with other textfiles.

* `./run.sh inventory` to see the inventory.

# Documentation
Compile documentation with `make docs`. All documentation files will be stored in the `./documentation` directory.

# Sample runs
### Default behaviour, `./run.sh`
```
Scanned item with ID "book" x 5:
	Name             : Book
	Price            : 50.50 SEK
	VAT              : 6.00%
	Cost (incl. VAT) : 53.53 SEK
	Description      : A piece of literature.

Total cost (incl. VAT) : 267.65 SEK
Total cost of VAT      : 15.15 SEK

freeMoney is not a valid item...

Scanned item with ID "apple" x 10:
	Name             : Apple
	Price            : 4.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 5.59 SEK
	Description      : Keeps doctors away.

Total cost (incl. VAT) : 323.54 SEK
Total cost of VAT      : 21.14 SEK

Scanned item with ID "banana" x 100:
	Name             : Banana
	Price            : 7.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 8.95 SEK
	Description      : Loved by monkeys.

Total cost (incl. VAT) : 1218.42 SEK
Total cost of VAT      : 117.02 SEK

Scanned item with ID "waterBottle" x 10:
	Name             : Bottled Water
	Price            : 14.99 SEK
	VAT              : 25.00%
	Cost (incl. VAT) : 18.74 SEK
	Description      : Filled with water.

Total cost (incl. VAT) : 1405.79 SEK
Total cost of VAT      : 154.49 SEK

Customer pays: 1500.00 SEK
Updated sales log.
Updated external accounting system.
Changes in external inventory system:
	Quantity of banana was decreased by 100 units.
	Quantity of apple was decreased by 10 units.
	Quantity of book was decreased by 5 units.
	Quantity of waterBottle was decreased by 10 units.
Amount in register increased by: 1405.79 SEK
┌──────────────────────────────────────────┐
│ This is your receipt!                    │
├──────────────────────────────────────────┤
│ Time of sale:           May 8 2024 11:09 │
│                                          │
│ Banana          100 x  8.95   894.88 SEK │
│ Apple            10 x  5.59    55.89 SEK │
│ Book              5 x 53.53   267.65 SEK │
│ Bottled Water    10 x 18.74   187.38 SEK │
│                                          │
│ Total cost (incl. VAT):      1405.79 SEK │
│ Total cost of VAT:            154.49 SEK │
│                                          │
│ Payment:                     1500.00 SEK │
│ Change:                        94.21 SEK │
├──────────────────────────────────────────┤
│ Thanks for your purchase!                │
└──────────────────────────────────────────┘
```
### Inventory display, `./run.sh inventory`
```
ID	INFO

banana
	Name             : Banana
	Price            : 7.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 8.95 SEK
	Description      : Loved by monkeys.

apple
	Name             : Apple
	Price            : 4.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 5.59 SEK
	Description      : Keeps doctors away.

book
	Name             : Book
	Price            : 50.50 SEK
	VAT              : 6.00%
	Cost (incl. VAT) : 53.53 SEK
	Description      : A piece of literature.

waterBottle
	Name             : Bottled Water
	Price            : 14.99 SEK
	VAT              : 25.00%
	Cost (incl. VAT) : 18.74 SEK
	Description      : Filled with water.
```
