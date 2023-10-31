package io.github.mireole.asynconf2023.gui;

import com.github.weisj.darklaf.theme.DarculaTheme;
import com.github.weisj.darklaf.theme.IntelliJTheme;
import com.github.weisj.darklaf.theme.OneDarkTheme;
import com.github.weisj.darklaf.theme.Theme;

public enum Themes {
    INTELLIJ("IntelliJ", new IntelliJTheme()),
    ONE_DARK("One Dark", new OneDarkTheme()),
    DARCULA("Darcula", new DarculaTheme());
    public final String name;
    public final Theme theme;

    Themes(String name, Theme theme) {
        this.name = name;
        this.theme = theme;
    }

    @Override
    public String toString() {
        return name;
    }
}
