INSERT INTO recipe (id, name, description, preparation_time, cook_time, servings, category)
VALUES ('3e8ee769-1f13-4bec-a5d3-fb7b20007883','Honey Garlic Salmon', 'A simple and flavorful baked salmon recipe with a sweet and savory honey garlic glaze.', 10, 15, 2, 'MAIN_COURSE');

INSERT INTO ingredient (id, recipe_id, name, quantity, unit_of_measure)
VALUES ('e85e996d-9e2e-42e7-8113-92fae0478efd', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 'Salmon fillets', 2, 'each'),
       ('30778e97-e174-4ff7-a9c1-cc74df698863', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 'Olive oil', 1, 'tablespoon'),
       ('c560edb8-c26f-4399-beb3-eaf0cf36deca', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 'Garlic cloves', 2, 'cloves (minced)'),
       ('b851ae1b-84df-40d6-806a-fa234aa5782c', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 'Honey', 1/4, 'cup'),
       ('f143242c-ddf6-4589-ac73-504696d7bc25', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 'Soy sauce', 2, 'tablespoons'),
       ('15d3d9b9-ed11-4eb6-beaf-d6c38f1a972b', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 'Lemon juice', 1, 'tablespoon');

INSERT INTO instruction (id, recipe_id, step, description)
VALUES ('e85e996d-9e2e-42e7-8113-92fae0478efd', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 1, 'Preheat oven to 400°F (200°C). Line a baking sheet with parchment paper.'),
       ('30778e97-e174-4ff7-a9c1-cc74df698863', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 2, 'Pat the salmon fillets dry with paper towels and season generously with salt and pepper.'),
       ('c560edb8-c26f-4399-beb3-eaf0cf36deca', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 3, 'In a small bowl, whisk together olive oil, garlic, honey, soy sauce, and lemon juice.'),
       ('b851ae1b-84df-40d6-806a-fa234aa5782c', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 4, 'Place salmon fillets on the prepared baking sheet. Spoon half of the glaze over the salmon.'),
       ('f143242c-ddf6-4589-ac73-504696d7bc25', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 5, 'Bake for 10-12 minutes, or until the salmon is cooked through and flakes easily with a fork.'),
       ('15d3d9b9-ed11-4eb6-beaf-d6c38f1a972b', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 6, 'Broil on high for an additional 1-2 minutes, if desired, for a crispier top.'),
       ('84069676-40a7-4994-81b9-6b330d77cc9e', '3e8ee769-1f13-4bec-a5d3-fb7b20007883', 7, 'Serve the salmon with the remaining glaze spooned over the top.');
