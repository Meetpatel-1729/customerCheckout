# customerCheckout
A java program uses LinkedQueue which assigns the customers in a a optimized way in which it will take less time for checkout

## Implementation
- Created a customer class which keeps the track of number of items, is customer eligible for express checkout, and also total serve time
- Read the file and created a arraylist which stores all the customer objects
- Created a Arraylist of type linkedQueue which adds the customer based on their eligibility of express checkout or regular checkout
- Calculate the total time for each queue and arange customers in a way which reduces the time for the checkout as much as possible
- At the end it will print all the queues with customer lined up (Just like any retail store i.e. Walmart) with their checkout time
- In the second part of the program, it will print total number of customers in each queue at every minute interval until all the queues are empty
