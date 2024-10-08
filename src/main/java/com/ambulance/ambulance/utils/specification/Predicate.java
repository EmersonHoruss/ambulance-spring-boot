package com.ambulance.ambulance.utils.specification;

import lombok.ToString;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
@SuppressWarnings({ "rawtypes", "unchecked" })
public class Predicate {
    private String attribute;
    private String operator;
    private String values;

    private Integer startIndex;
    private Integer endIndex;

    private jakarta.persistence.criteria.Predicate predicate;

    public Predicate(String query, Integer startIndex, Integer endIndex, Root root, CriteriaBuilder cb) {
        Pattern pattern = Pattern.compile("([\\w.]+)<(\\w+)>([\\w,]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            this.attribute = matcher.group(1);
            this.operator = matcher.group(2);
            this.values = matcher.group(3);
        }
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.predicate = this.buildPredicate(root, cb);

    }

    public Predicate(Predicate predicate, Connector connector) {
        this.startIndex = connector.getStartIndex();
        this.endIndex = predicate.getEndIndex();
        this.predicate = predicate.getJavaxPredicate().not();
    }

    public Predicate(Predicate predicate1, Predicate predicate2, Connector connector, CriteriaBuilder cb) {
        this.startIndex = predicate1.getStartIndex();
        this.endIndex = predicate2.getEndIndex();

        jakarta.persistence.criteria.Predicate javaxPredicate1 = predicate1.getJavaxPredicate();
        jakarta.persistence.criteria.Predicate javaxPredicate2 = predicate2.getJavaxPredicate();
        switch (connector.getConnector()) {
            case "and":
                this.predicate = cb.and(javaxPredicate1, javaxPredicate2);
                break;
            case "or":
                this.predicate = cb.or(javaxPredicate1, javaxPredicate2);
                break;
        }
    }

    private jakarta.persistence.criteria.Predicate buildPredicate(Root root, CriteriaBuilder cb) {
        jakarta.persistence.criteria.Predicate predicate = null;
        Path attribute = getAttribute(root);

        switch (this.operator) {
            case "eq":
                predicate = cb.equal(attribute, this.getValue());
                break;
            case "ne":
                predicate = cb.notEqual(attribute, this.getValue());
                break;
            case "ct":
                predicate = cb.like(attribute, "%" + this.getValue() + "%");
                break;
            case "sw":
                predicate = cb.like(attribute, "%" + this.getValue());
                break;
            case "ew":
                predicate = cb.like(attribute, this.getValue() + "%");
                break;
            case "gt":
                predicate = cb.greaterThan(attribute, this.getValue());
                break;
            case "lt":
                predicate = cb.lessThan(attribute, this.getValue());
                break;
            case "ge":
                predicate = cb.greaterThanOrEqualTo(attribute, this.getValue());
                break;
            case "le":
                predicate = cb.lessThanOrEqualTo(attribute, this.getValue());
                break;
        }
        return predicate;
    }

    private Path getAttribute(Root root) {
        if (this.containsNestedEntities()) {
            String[] nestedEntitiesAndAttribute = this.getNestedEntitiesAndAttribute();

            String firstNestedEntity = nestedEntitiesAndAttribute[0];
            Join join = root.join(firstNestedEntity);

            for (Integer i = 1; i < nestedEntitiesAndAttribute.length - 1; i++) {
                String nestedEntity = nestedEntitiesAndAttribute[i];
                join = join.join(nestedEntity);
            }

            String attribute = nestedEntitiesAndAttribute[nestedEntitiesAndAttribute.length - 1];

            return join.get(attribute);
        }

        return root.get(this.attribute);
    }

    private boolean containsNestedEntities() {
        return this.attribute.contains(".");
    }

    private String[] getNestedEntitiesAndAttribute() {
        return this.attribute.split("[.]");
    }

    private String getValue() {
        return this.values;
    }

    private String[] getValues() {
        return this.values.split("[,]");
    }

    public jakarta.persistence.criteria.Predicate getJavaxPredicate() {
        return this.predicate;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public boolean isEndIndex(Integer endIndex) {
        return this.endIndex == endIndex;
    }

    public boolean isStartIndex(Integer startIndex) {
        return this.startIndex == startIndex;
    }
}
