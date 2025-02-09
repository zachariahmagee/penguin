package zm.penguin.interactions;

@FunctionalInterface
public interface BooleanCondition {
    boolean evaluate();

    static BooleanCondition alwaysTrue() {
        return () -> true;
    }

    static BooleanCondition alwaysFalse() {
        return () -> false;
    }

    static BooleanCondition negate(BooleanCondition condition) {
        return () -> !condition.evaluate();
    }

    static BooleanCondition and(BooleanCondition c1, BooleanCondition c2) {
        return () -> c1.evaluate() && c2.evaluate();
    }

    static BooleanCondition or(BooleanCondition c1, BooleanCondition c2) {
        return () -> c1.evaluate() || c2.evaluate();
    }
}
