Baraq Assignment

Design a simple e-commerce platform, where you need to have the capability of handling
buyers, orders and products.

The following methods need to be implemented:
Create buyer account:
1. Store buyer name and his address
2. Return created buyerId

Create product:
1. Store product name, inventory, price and pickup address.
2. Return created productId

Create pincode serviceability:
1. Store sourcePincode, destinationPincode and paymentMode serviceability.
It will be a map of source(pickup) and destination pincodes where each pair of pincode can be
serviceable for either cod or prepaid or both.

Create order: String createOrder(String buyerId, String productId, int quantity, String
paymentMode)
1. Check if order is valid based on available inventory and pincode serviceability,
if not valid due to serviceability throw a proper exception and print "Order failed because
pincode is unserviceable" and exit.
if not valid due to insufficient stock throw a proper exception and print "Order failed because
product stock is insufficient" and exit.
2. PaymentMode can be prepaid or cod
3. Assume an order comprises of a single product
4. Return created orderId
Evaluation Criteria:
Correctness
Approach to the Solution
Code Structure
Race condition/Thread Safe
