CREATE TABLE public.ingredient (
    id                    uuid          not null,
    recipe_id             uuid          not null,
    name                  varchar (255) not null,
    quantity              numeric,
    unit_of_measure       varchar (255) null,

    CONSTRAINT ingredient_pkey PRIMARY KEY (id)
);

ALTER TABLE public.ingredient add CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES public.recipe(id) on delete CASCADE;
