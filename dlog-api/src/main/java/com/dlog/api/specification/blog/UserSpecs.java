package com.dlog.api.user.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.dlog.api.user.model.User;

public class UserSpecs {
	
	public static Specification<User> searchWith(Map<String, Object> searchKeyword) {
        return (Specification<User>) ((root, query, builder) -> {
            List<Predicate> predicate = getPredicateWithKeyword(searchKeyword, root, builder);
            return builder.and(predicate.toArray(new Predicate[0]));
        });
    }
	
	private static List<Predicate> getPredicateWithKeyword(Map<String, Object> searchKeyword, Root<User> root, CriteriaBuilder builder) {
        List<Predicate> predicate = new ArrayList<>();
        for (String key : searchKeyword.keySet()) {
        	predicate.add(builder.equal(root.get(key), searchKeyword.get(key)));
        }
        return predicate;
    }
}
