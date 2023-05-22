package dev.isxander.yacl.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.TabButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.components.tabs.Tab;
import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class ScrollableNavigationBar extends TabNavigationBar {
    private static final int NAVBAR_MARGIN = 28;

    private static final Font font = Minecraft.getInstance().font;

    private int scrollOffset;
    private int maxScrollOffset;

    public ScrollableNavigationBar(int i, TabManager tabManager, Iterable<Tab> iterable) {
        super(i, tabManager, iterable);
    }

    @Override
    public void arrangeElements() {
        int noScrollWidth = this.width - NAVBAR_MARGIN*2;
        int minimumSize = tabButtons.stream()
                .map(AbstractWidget::getMessage)
                .mapToInt(label -> font.width(label) + 3)
                .min().orElse(0);
        int singleTabWidth = Math.max(noScrollWidth / Math.min(this.tabButtons.size(), 3), minimumSize);
        for (TabButton tabButton : this.tabButtons) {
            tabButton.setWidth(singleTabWidth);
        }

        this.layout.arrangeElements();
        this.layout.setY(0);
        this.scrollOffset = 0;

        int allTabsWidth = singleTabWidth * this.tabButtons.size();
        this.layout.setX(Math.max((this.width - allTabsWidth) / 2, NAVBAR_MARGIN));
        this.maxScrollOffset = Math.max(0, allTabsWidth - noScrollWidth);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        this.setScrollOffset(this.scrollOffset - (int)(amount*10));
        return true;
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseY <= 24;
    }

    public void setScrollOffset(int scrollOffset) {
        layout.setX(layout.getX() + this.scrollOffset);
        this.scrollOffset = Mth.clamp(scrollOffset, 0, maxScrollOffset);
        layout.setX(layout.getX() - this.scrollOffset);
    }

    @Override
    public void setFocused(@Nullable GuiEventListener child) {
        super.setFocused(child);
        if (child instanceof TabButton tabButton) {
            this.ensureVisible(tabButton);
        }
    }

    protected void ensureVisible(TabButton tabButton) {
        if (tabButton.getX() < NAVBAR_MARGIN) {
            this.setScrollOffset(this.scrollOffset - (NAVBAR_MARGIN - tabButton.getX()));
        } else if (tabButton.getX() + tabButton.getWidth() > this.width - NAVBAR_MARGIN) {
            this.setScrollOffset(this.scrollOffset + (tabButton.getX() + tabButton.getWidth() - (this.width - NAVBAR_MARGIN)));
        }
    }
}