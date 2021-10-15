# Tech Stack
This project uses:
- java 11
- spring boot 2.5.5
- maven
- in memory DB set up with H2, and Spring JPA

# Ideas and Improvements
For a city-autocomplete system, these are a few features that I believe would enhance the overall experience:
- Implement user accounts, so the user can keep track of `recent searches`, `favorites`
- Implement the ability to search by `phone area code`, `airport codes`
- Implement the notion of distance, given a location. Like a `near me`

For improved scoring, a more thorough algorithm based on fuzzy search and the user's previous searches.

For scalability issues, we could cache the results.

For the billing system, monthly or yearly subscriptions could be offered. The subscriptions could also involve tiers.
Implementing these changes to the current code base won't involve any major refactoring, but rather an addition of the 
feature. A separate DB would be necessary.
