
package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantity.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefQuantityDeleteService implements AbstractDeleteService<Chef, Quantity> {

	@Autowired
	protected ChefQuantityRepository repository;


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		final int cookingItemId = request.getModel().getInteger("id");
		final int chefId = request.getPrincipal().getActiveRoleId();
		final Quantity quantity = this.repository.findOneQuantityById(cookingItemId);
		return quantity.getRecipe().getChef().getId() == chefId;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors,"number", "amount");

	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "amount");
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		final int id = request.getModel().getInteger("id");
		final Quantity res = this.repository.findQuantityById(id);
		return res;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}

