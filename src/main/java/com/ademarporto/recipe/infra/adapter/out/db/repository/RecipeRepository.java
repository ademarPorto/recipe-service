package com.ademarporto.recipe.infra.adapter.out.db.repository;

import com.ademarporto.recipe.infra.adapter.out.db.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, UUID>,
        QuerydslPredicateExecutor<RecipeEntity> {


}
