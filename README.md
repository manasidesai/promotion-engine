# promotion-engine

A spring boot application for a simple promotion engine. The promotion engine calculates the total order value after applying promotions on the cart items.

### Promotion

Promotions are defined by implementing the Promotion interface. 

Currently two promotion types are defined. 
1. QuantityBasedSinglePromotion - It finds matching cart item from the cart and applies the promotional price on given quantity. 
2. MixedTypePromotion - It finds all matching cart item from the cart. Applies the promotional price only when all promotional items found in the cart. 

Additional promotional classes can be added by implementing the base Promotion interface and defining their behaviour.

### PromotionConfig

A configuration class to initialise list of promotions. 
As per scenarios provided in the coding test, 3 different promotion classes and 4 SKU cart items have been initialised. 

### PromotionEngine

It calculates the total order value after applying promotions.

The getCartTotal() method receives list of cart items.
1.It calculates count of the each cart item and saves in the map.
2.Receives a list of promotions from the configuration class PromotionConfig.
3.For each promotion type it calls the applyPromotion() method and receives a list of checkout items with promotion/non promotional prices applied 
4.It calculates total of all promotional and non promotional prices and returns the bill total.

### Tests

QuantityBasedSinglePromotionTest - To test the applyPromotion() method of the QuantityBasedSinglePromotion class

MixedTypePromotionTest - To test the applyPromotion() method of the MixedTypePromotion class

PromotionEngineTest - To test the getCartTotal() method of the PromotionEngine class

### How to test the project
1. Download the project from the github
2. Import project in the IDE Eclipse/Intellij
3. Run the project using 'mvn install' command. Alternatively test the project by running 'mvn test' command.


