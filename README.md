# Nutri-Goals
Specifications:

This program will allow users to get daily meal plans and track micro/macronutrients based on their goals. These goals may include wanting to adjust how much calcium they intake on a daily basis, or they want to keep track of the amount of iron (for health reasons), and so forth. The user will be recommended recipes(from a database of already built meal plans), which they can then search by name and get the nutritional value., and general tags; keeping track of their nutrient, recipe and streak history.
The user could potentially track: calories, carbohydrates, proteins and fats(macronutrients), iron, vitamin B12, magnesium(micronutrients).

User Stories:

Alex hears about an app that is able to offer recipes/meal ideas based on the user’s unique goals and preferences. He makes an account using his email and creates a password. After he makes an account, he is met with a list of different goals he can have, which range from iron, calcium, and protein intake as well as specific Vitamin A, B, C intake and so forth(which he can input an amount for if he chooses). He chooses what he wants to track, and the goal is added to his account. The app will then recommend daily recipes to meet his daily goals. (Team) /n

Christine was recently told by her doctor that she is iron deficient. She wants to build meal plans conscious of her deficiency and track her daily and average intakes. She created an account with the food & nutrition management program that keeps track of iron intake. She is shown recipes that are high in iron relative to her goal.

Proposed (Main) Entities for the Domain: /n

User
(Attributes): UserID, email, password, dietary restrictions

MealPlan
(Attributes): MealplanID, Recipes, UserID

Recipe
(Attributes):RecipeID, name, ingredients, calories
