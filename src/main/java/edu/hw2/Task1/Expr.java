package edu.hw2.Task1;

public sealed interface Expr {
    double evalute();

    public record Constant(double number) implements Expr {
        @Override
        public double evalute() {
            return number;
        }
    }

    public record Negate(Expr constant) implements Expr {
        @Override
        public double evalute() {
            return -constant.evalute();
        }
    }

    public record Exponent(Expr constant, int power) implements Expr {
        @Override
        public double evalute() {
            return Math.pow(constant.evalute(), power);
        }
    }

    public record Addition(Expr firstConstant, Expr secondConstant) implements Expr {
        @Override
        public double evalute() {
            return firstConstant.evalute() + secondConstant.evalute();
        }
    }

    public record Multiplication(Expr firstConstant, Expr secondConstant) implements Expr {
        @Override
        public double evalute() {
            return firstConstant.evalute() * secondConstant.evalute();
        }
    }
}
