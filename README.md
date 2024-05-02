# How to run
### Not compiled
* Compile with `make`. The program will automatically run once compiled.

### Already compiled
* Run with `./run.sh` to use the default textfiles stored in the `./example` directory, or `./run.sh <path/to/goods> <path/to/payment>` for use with other textfiles.

* `./run.sh inventory` to see the inventory.

# Documentation
Compile documentation with `make docs`. All documentation files will be stored in the `./documentation` directory.

# Sample run
```
Scanned "Book" x 5:
	Item ID               : book
	Item price            : 50.50 SEK
	Item VAT              : 6.00%
	Item cost (incl. VAT) : 53.53 SEK
	Item description      : A piece of literature.

Total cost (incl. VAT) : 267.65 SEK
Total cost of VAT      : 15.15 SEK

freeMoney is not a valid item...

Scanned "Apple" x 10:
	Item ID               : apple
	Item price            : 4.99 SEK
	Item VAT              : 12.00%
	Item cost (incl. VAT) : 5.59 SEK
	Item description      : Keeps doctors away.

Total cost (incl. VAT) : 323.54 SEK
Total cost of VAT      : 21.14 SEK

Scanned "Banana" x 100:
	Item ID               : banana
	Item price            : 7.99 SEK
	Item VAT              : 12.00%
	Item cost (incl. VAT) : 8.95 SEK
	Item description      : Loved by monkeys.

Total cost (incl. VAT) : 1218.42 SEK
Total cost of VAT      : 117.02 SEK

Scanned "Bottled Water" x 10:
	Item ID               : waterBottle
	Item price            : 14.99 SEK
	Item VAT              : 25.00%
	Item cost (incl. VAT) : 18.74 SEK
	Item description      : Filled with water.

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
│ Time of sale:           May 2 2024 12:41 │
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
