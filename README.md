Grocery Store Project
=======

Sample android application project to showcase different ideas or concepts.

## App

The app is a simulation of an e-commerce application with two main screens: a catalog of products and a shopping-cart. 

![App screenshot](/doc/app.png)

### Use cases

1. Clicking in a product would add the element to the Cart.
2. A small notification will be shown every time that something is added to the Cart.
3. From the Cart the products can be removed by clicking on them.
4. There would be a count indicator in one of the tabs (the cart one) with the number of items included in the Shopping Cart.
5. When something is added/removed from Cart the counter and content of the cart screen must be updated accordingly.


## Concepts

* [Store](/app/src/main/java/de/czyrux/store/core/domain/Store.java) as a backbone for sharing state across the application.

**Full article**: [State propagation in Android with RxJava Subjects](https://medium.com/@czyrux/state-propagation-in-android-with-rxjava-subjects-81db49a0dd8e#.ylft5ryj5)

Flow updates for add2Cart operation:

![add2Cart Flow](/doc/add2Cart_flow.png)



## Version
1.0