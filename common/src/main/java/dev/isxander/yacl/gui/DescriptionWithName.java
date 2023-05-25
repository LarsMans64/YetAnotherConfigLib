package dev.isxander.yacl.gui;

import dev.isxander.yacl.api.OptionDescription;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public record DescriptionWithName(Component name, OptionDescription description) {
    public static DescriptionWithName of(Component name, OptionDescription description) {
        return new DescriptionWithName(name.copy().withStyle(ChatFormatting.BOLD), description);
    }
}
