# How to run
### Not compiled
* Compile with `make`. The program will automatically run once compiled.

### Already compiled
* Run with `./run.sh` for default behaviour. If you are interested in everything that the program can do run `./run.sh -h`.

# Documentation
Compile documentation with `make docs`. All documentation files will be stored in the `./documentation` directory.

# Sample runs
### Default behaviour, `./run.sh`
```
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/1.Default/goods.txt"
│    Payment from: "examples/1.Default/payment.txt"
╰──────────────────╢
Scanned item with ID "book" x 5:
	Name             : Book
	Price            : 50.50 SEK
	VAT              : 6.00%
	Cost (incl. VAT) : 53.53 SEK
	Description      : A piece of literature.

Total cost (incl. VAT) : 267.65 SEK
Total cost of VAT      : 15.15 SEK

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
Total revenue since start of program: 1405.79 SEK
Updated sales log.
Updated external accounting system.
Changes in external inventory system:
	Quantity of "banana" was decreased by 100 units.
	Quantity of "apple" was decreased by 10 units.
	Quantity of "book" was decreased by 5 units.
	Quantity of "waterBottle" was decreased by 10 units.
Amount in register increased by: 1405.79 SEK
┌──────────────────────────────────────────┐
│ This is your receipt!                    │
├──────────────────────────────────────────┤
│ Time of sale:          May 10 2024 19:20 │
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
### Invalid items, `./run.sh -p examples/2.InvalidItems`
```
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/2.InvalidItems/goods.txt"
│    Payment from: "examples/2.InvalidItems/payment.txt"
╰──────────────────╢
The item ID "invalidItem1" is invalid.

The item ID "invalidItem2" is invalid.

The item ID "invalidItem3" is invalid.

The item ID "invalidItem4" is invalid.

The item ID "invalidItem5" is invalid.

Customer pays: 123.46 SEK
Total revenue since start of program: 0.00 SEK
No change was made to the sales log.
No change was made to the external accounting system.
No change was made to the external inventory system.
┌──────────────────────────────────┐
│ This is your receipt!            │
├──────────────────────────────────┤
│ Time of sale:  May 10 2024 19:20 │
│                                  │
│                                  │
│ Total cost (incl. VAT): 0.00 SEK │
│ Total cost of VAT:      0.00 SEK │
│                                  │
│ Payment:              123.46 SEK │
│ Change:               123.46 SEK │
├──────────────────────────────────┤
│ Thanks for your purchase!        │
└──────────────────────────────────┘
```
### Database unavailable, `./run.sh -p examples/3.DatabaseUnavailable`
Note: This also logs the thrown exception in a file called `sale-exceptions-log.txt` which gets created if it does not already exist.
```
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/3.DatabaseUnavailable/goods.txt"
│    Payment from: "examples/3.DatabaseUnavailable/payment.txt"
╰──────────────────╢
Exception in thread "main" se.kth.iv1350.integration.DatabaseUnavailableException: Database for "external inventory system" is unavailable.
	at se.kth.iv1350.integration.ExternalInventorySystem.retrieveItemInfo(ExternalInventorySystem.java:93)
	at se.kth.iv1350.controller.Controller.scanItem(Controller.java:80)
	at se.kth.iv1350.view.View.scanGoods(View.java:101)
	at se.kth.iv1350.view.View.simulateSale(View.java:165)
	at se.kth.iv1350.view.View.run(View.java:203)
	at se.kth.iv1350.startup.Main.main(Main.java:90)
```
### Insufficient payment, `./run.sh -p examples/4.InsufficientPayment`
```
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/4.InsufficientPayment/goods.txt"
│    Payment from: "examples/4.InsufficientPayment/payment.txt"
╰──────────────────╢
Scanned item with ID "banana" x 99:
	Name             : Banana
	Price            : 7.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 8.95 SEK
	Description      : Loved by monkeys.

Total cost (incl. VAT) : 885.93 SEK
Total cost of VAT      : 94.92 SEK

Customer pays: 0.99 SEK
The payment of 0.99 SEK was not enough to cover the whole cost of 885.93 SEK for the entire sale.
Payment missing: 884.94 SEK
```
### Multiple sales in a row, `./run.sh -p examples/monke1 -p examples/monke2 -p examples/monke3`
```
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/monke1/goods.txt"
│    Payment from: "examples/monke1/payment.txt"
╰──────────────────╢
Scanned item with ID "banana" x 1:
	Name             : Banana
	Price            : 7.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 8.95 SEK
	Description      : Loved by monkeys.

Total cost (incl. VAT) : 8.95 SEK
Total cost of VAT      : 0.96 SEK

Customer pays: 10.00 SEK
Total revenue since start of program: 8.95 SEK
Updated sales log.
Updated external accounting system.
Changes in external inventory system:
	Quantity of "banana" was decreased by 1 units.
Amount in register increased by: 8.95 SEK
┌──────────────────────────────────┐
│ This is your receipt!            │
├──────────────────────────────────┤
│ Time of sale:  May 10 2024 19:39 │
│                                  │
│ Banana       1 x 8.95   8.95 SEK │
│                                  │
│ Total cost (incl. VAT): 8.95 SEK │
│ Total cost of VAT:      0.96 SEK │
│                                  │
│ Payment:               10.00 SEK │
│ Change:                 1.05 SEK │
├──────────────────────────────────┤
│ Thanks for your purchase!        │
└──────────────────────────────────┘
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/monke2/goods.txt"
│    Payment from: "examples/monke2/payment.txt"
╰──────────────────╢
Scanned item with ID "banana" x 10:
	Name             : Banana
	Price            : 7.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 8.95 SEK
	Description      : Loved by monkeys.

Total cost (incl. VAT) : 89.49 SEK
Total cost of VAT      : 9.59 SEK

Customer pays: 100.00 SEK
Total revenue since start of program: 98.44 SEK
Updated sales log.
Updated external accounting system.
Changes in external inventory system:
	Quantity of "banana" was decreased by 10 units.
Amount in register increased by: 89.49 SEK
┌───────────────────────────────────┐
│ This is your receipt!             │
├───────────────────────────────────┤
│ Time of sale:   May 10 2024 19:39 │
│                                   │
│ Banana      10 x 8.95   89.49 SEK │
│                                   │
│ Total cost (incl. VAT): 89.49 SEK │
│ Total cost of VAT:       9.59 SEK │
│                                   │
│ Payment:               100.00 SEK │
│ Change:                 10.51 SEK │
├───────────────────────────────────┤
│ Thanks for your purchase!         │
└───────────────────────────────────┘
╭───────────────────────────────╢
│ Starting new sale simulation!
│    Goods   from: "examples/monke3/goods.txt"
│    Payment from: "examples/monke3/payment.txt"
╰──────────────────╢
Scanned item with ID "banana" x 100:
	Name             : Banana
	Price            : 7.99 SEK
	VAT              : 12.00%
	Cost (incl. VAT) : 8.95 SEK
	Description      : Loved by monkeys.

Total cost (incl. VAT) : 894.88 SEK
Total cost of VAT      : 95.88 SEK

Customer pays: 1000.00 SEK
Total revenue since start of program: 993.32 SEK
Updated sales log.
Updated external accounting system.
Changes in external inventory system:
	Quantity of "banana" was decreased by 100 units.
Amount in register increased by: 894.88 SEK
┌────────────────────────────────────┐
│ This is your receipt!              │
├────────────────────────────────────┤
│ Time of sale:    May 10 2024 19:39 │
│                                    │
│ Banana     100 x 8.95   894.88 SEK │
│                                    │
│ Total cost (incl. VAT): 894.88 SEK │
│ Total cost of VAT:       95.88 SEK │
│                                    │
│ Payment:               1000.00 SEK │
│ Change:                 105.12 SEK │
├────────────────────────────────────┤
│ Thanks for your purchase!          │
└────────────────────────────────────┘
```
After this sale a file called `total-revenue-from-latest-run.txt` will contain this:
```
Total revenue after sale #1: 8.95 SEK
Total revenue after sale #2: 98.44 SEK
Total revenue after sale #3: 993.32 SEK
```
This demonstrates the purpose of the class TotalRevenueFileOutput, which was specified in Part a of Task 2 for Seminar 4.
