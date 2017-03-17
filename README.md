# Android-Shopping-app

##Functions:
* Databinding for the product page
* Animation when floating action button clicked 
* Using retrofit to handle your REST requests 
* Using SQLite to store shopping cart data


##Introduction:
1. MainActivity
  * Use **Retrofit** handle REST requests
  * Validate internet connection and query result
  * Pass valid result to ChildActivity
  
2. ChildActivity
  * **Databinding** for product information
  * Use **Asynctask** pull image from URL
  * **Animation** of "add to cart" action
  * save Id and amount amount of added product for further use
  * Use constraint layout and contrast color in UI design
  * Disable land view for better display
  
3. CartActivity
  * Use SQLite store shopping products info
  * Swipe operation to removing product from cart
