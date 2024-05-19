CREATE TABLE public.recipe (
    id                    uuid          not       null,
    name                  varchar (255) not       null,
    description           text,
    preparation_time      integer                 null,
    cook_time             integer                 null,
    servings              integer                 null,
    vegetarian            boolean  DEFAULT false  null,
    category              varchar (255)           null,

    CONSTRAINT recipe_pkey PRIMARY KEY (id)
);
