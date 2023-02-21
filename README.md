# mozio-pizza
Simple pizza ordering app developed as a hiring task for Mozio Group

“Pizza delivery
A small pizza delivery company was looking for growth and wanted to develop their own pizza delivery app. They hired you to develop this app.

In order to keep this small, we'll only work on a small portion of that app: the menu and flavor selection, plus a confirmation screen.

Your task:

Find a JSON file with pizza flavors here: http://static.mozio.com/mobile/tests/pizzas.json

Write an app that has the following features:

- A menu of pizza flavors (read from the JSON above) where the user can select the flavors they want. A pizza can have one flavor (full pizza of the same flavor) or two flavors (half/half)
- To keep the scope small, only one pizza can be chosen at a time. No need for a shopping cart
- Flavors have their own prices. Total pizza price is calculated based on the price of each half  (i.e. if a flavor costs $10, a half of this flavor will cost $5 and a full pizza of this flavor $10)
- A button to finish ordering, which takes the user to an "order successful" screen. Simply show a summary of the selected pizza and the final price
- This scope includes only the flavor selection, pricing and confirmation. Extras are not necessarily a pro
- Bonus: Write unit tests for your code

Here's what we will be evaluating in your solution:

- This project is a broad definition by design. We're less concerned about a very specific spec and more interested in the solution you come up with
- App works as per the scope above and doesn't crash
- General code quality, documentation, project structure...

Here's what we will NOT be evaluating:

- UI/UX. Your app will provide UI, but using unstyled/raw components is perfectly acceptable (and encouraged). No need to hire a designer :). However, do make an effort to make it easy enough to use from a user's perspective.
- Any extra features not described above

Note
This is a native development role. Please send your test written natively in Java/ Kotlin.
