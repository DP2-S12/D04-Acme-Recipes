package acme.features.chef.quantity;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.cookingItem.CookingItem;
import acme.entities.quantity.Quantity;
import acme.entities.recipe.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;


@Repository
public interface ChefQuantityRepository extends AbstractRepository{

	@Query("select t from Recipe t where t.id =:id")
	Recipe findOneRecipeById(int id);
	
	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findChefByUserId(int id);
	
	@Query("select i from CookingItem i")
	List<CookingItem> findManyCookingItem();
	
	@Query("select i from CookingItem i where i.id = :id")
	CookingItem findOneCookingItemById(int id);
	
	@Query("select distinct(q) from Quantity q where q.recipe.id = :masterId")
	Collection<Quantity> findQuantityByRecipeId(int masterId);
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);
	
	@Query("select i from CookingItem i WHERE i.code = :code")
	CookingItem findCookingItemByCode(String code);
	
}
