# Nutri-Goals

## Specifications

This program allows users to create daily meal plans based on their goals, needs, and preferences. Intended to cater 
towards those with particular diets, conditions, and allergies, the user is recommended recipes from a database of 
pre-built meal plans that are applicable to their needs. The user will be able to search for recipes by name and 
general tags. Additionally, the user is able to specify exactly what nutritional information they'd like to track 
based on their individual goals (i.e, calories will not be displayed unless explicitly toggled), intended to be 
accessible towards users of all intents and conditions. Meal plans, recipe information, and tracked nutritional 
information are stored for the user to access as they please.

### A user will set basic personal information when they first create their account:
    1. Username: used to log in to the account
    2. Password: used to log in to the account
    3. Gender: a user's specified gender (can be male and/or female, or other)
    4. Height: the user's height, used to calculate weight goals
    5. Weight: the user's weight, used to calculate various recommended intakes and weight goals
    6. Age: the user's age, used to calculate various recommended intakes based on age-group
    7. Exercise Level: the user's exercise level, measured from not active to very active (1-5)

#### Note: The user will be assigned a unique userID

### A user will further set various preferences after they create their account:
    1. Diet: a user's specified diet (ex: vegetarian, vegan, pescatarian, none)
    2. Allergies: any allergens the user would like to avoid
    3. Conditions: any conditions the user may have that will affect the recipes they would like to be suggested
    4. Tracked Nutrients: the nutritional information the user would like to track (ex: macro/micronutrients)
    5. Weight Goal: a user's specified (or unspecified) weight goal (ex: lose, gain, maintain)

#### Note: The nutrients that the user can choose to track with relation to the meal plans they create:
    a) Macronutrients: calories, carbohydrates, proteins, fats
    b) Micronutrients: calcium, potassium, vitamin C, vitamin D, iron, magnesium, sugar

### Main Design Decisions
...
    
### Proposed Entities
    1. User
        Attributes: UserID, name, password, creationTime, gender, userHeight, weight, age, exerciseLevel
                    diet, trackedNutrients, allergies, conditions, weightGoal, paceType, requiredCalories
    2. Ingredient
        Attributes: ingredientID, name, amount
    3. Recipe
        Attributes: recipeID, recipeName, recipeIngredients, recipeInstructions, recipeType, nutritionalInfo
                    recipeLink
    4. MealPlan
        Attributes: breakfast, lunch, dinner

### User Stories
    1. Alex hears about an app that is able to offer recipes/meal ideas based on the user’s unique goals and 
        preferences. He makes an account using his email and creates a password. After he makes an account, he is met 
        with a list of different goals he can have, which range from iron, calcium, and protein intake as well as 
        specific Vitamin A, B, C intake and so forth(which he can input an amount for if he chooses). He chooses what he
        wants to track, and the goal is added to his account. The app will then recommend daily recipes to meet his 
        daily goals. (Team)
    2. Christine was recently told by her doctor that she is iron deficient. She wants to build meal plans conscious of 
        her deficiency and track her daily and average intakes. She created an account with the food & nutrition 
        management program that keeps track of iron intake. She is shown recipes that are high in iron relative to her 
        goal.
