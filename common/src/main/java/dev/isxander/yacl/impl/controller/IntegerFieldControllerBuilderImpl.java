package dev.isxander.yacl.impl.controller;

import dev.isxander.yacl.api.Controller;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl.gui.controllers.slider.IntegerSliderController;
import dev.isxander.yacl.gui.controllers.string.number.IntegerFieldController;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

public class IntegerFieldControllerBuilderImpl extends AbstractControllerBuilderImpl<Integer> implements IntegerFieldControllerBuilder {
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;
    private Function<Integer, Component> formatter = IntegerSliderController.DEFAULT_FORMATTER;

    public IntegerFieldControllerBuilderImpl(Option<Integer> option) {
        super(option);
    }

    @Override
    public IntegerFieldControllerBuilder min(Integer min) {
        this.min = min;
        return this;
    }

    @Override
    public IntegerFieldControllerBuilder max(Integer max) {
        this.max = max;
        return this;
    }

    @Override
    public IntegerFieldControllerBuilder range(Integer min, Integer max) {
        this.min = min;
        this.max = max;
        return this;
    }

    @Override
    public IntegerFieldControllerBuilder valueFormatter(Function<Integer, Component> formatter) {
        this.formatter = formatter;
        return this;
    }

    @Override
    public Controller<Integer> build() {
        return new IntegerFieldController(option, min, max, formatter);
    }
}
