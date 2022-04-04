package com.georgeisaev.axualpokemonapi.web;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomSearchVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    GenericSearchSpecBuilder<T> builder;

    public CustomSearchVisitor() {
        builder = new GenericSearchSpecBuilder<>();
    }

    @Override
    public Specification<T> visit(AndNode node, Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(OrNode node, Void param) {
        return builder.createSpecification(node);
    }

    @Override
    public Specification<T> visit(ComparisonNode node, Void params) {
        return builder.createSpecification(node);
    }

}
