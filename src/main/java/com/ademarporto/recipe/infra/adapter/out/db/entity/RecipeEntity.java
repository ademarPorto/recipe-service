package com.ademarporto.recipe.infra.adapter.out.db.entity;


import com.ademarporto.recipe.domain.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "recipe")
public class RecipeEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    private String name;
    private String description;
    private Integer preparationTime;
    private Integer cookTime;
    private Integer servings;
    private boolean vegetarian;
    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name="recipe_id", nullable = false)
    private Set<IngredientEntity> ingredientEntities;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name="recipe_id", nullable = false)
    private Set<InstructionEntity> instructionEntities;

}
