package dev.isxander.yacl.api.controller;

import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.impl.controller.IntegerFieldControllerBuilderImpl;

public interface IntegerFieldControllerBuilder extends NumberFieldControllerBuilder<Integer, IntegerFieldControllerBuilder> {
    static IntegerFieldControllerBuilder create(Option<Integer> option) {
        return new IntegerFieldControllerBuilderImpl(option);
    }
}
