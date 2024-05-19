CREATE TABLE public.instruction (
    id                    uuid          not null,
    recipe_id             uuid          not null,
    step                  integer       not null,
    description           text,

    CONSTRAINT instruction_pkey PRIMARY KEY (id)
);

ALTER TABLE public.instruction add CONSTRAINT fk_recipe_id FOREIGN KEY (recipe_id) REFERENCES public.recipe(id) on delete CASCADE;
