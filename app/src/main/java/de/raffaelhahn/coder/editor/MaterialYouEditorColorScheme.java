package de.raffaelhahn.coder.editor;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.google.android.material.color.MaterialColors;

import org.eclipse.tm4e.core.internal.theme.Theme;
import org.eclipse.tm4e.core.registry.IThemeSource;

import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.widget.CodeEditor;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;

public class MaterialYouEditorColorScheme  extends EditorColorScheme implements ThemeRegistry.ThemeChangeListener {

    private Theme theme;

    @Deprecated
    private IThemeSource themeSource;

    private ThemeModel currentTheme;

    private final ThemeRegistry themeRegistry;
    private static Context context;

    public MaterialYouEditorColorScheme(ThemeRegistry themeRegistry, ThemeModel themeModel) throws Exception {
        this.themeRegistry = themeRegistry;

        currentTheme = themeModel;
    }

    public static MaterialYouEditorColorScheme create(Context context) throws Exception {
        ThemeRegistry themeRegistry = ThemeRegistry.getInstance();
        MaterialYouEditorColorScheme.context = context;
        return new MaterialYouEditorColorScheme(themeRegistry, themeRegistry.getCurrentThemeModel());
    }

    public void setTheme(ThemeModel themeModel) {
        currentTheme = themeModel;
        super.colors.clear();
        this.theme = themeModel.getTheme();
        this.themeSource = themeModel.getThemeSource();
        applyDefault();
    }

    @Override
    public void onChangeTheme(ThemeModel newTheme) {
        setTheme(newTheme);
    }

    @Override
    public void applyDefault() {
        super.applyDefault();

        if (themeRegistry != null && !themeRegistry.hasListener(this)) {
            themeRegistry.addListener(this);
        }
        for (int i = START_COLOR_ID; i <= END_COLOR_ID; i++) {
            applyMaterialYouTheme(i);
        }
    }

    private final static int PRIMARY_TEXT_COLOR_DEFAULT_LIGHT = 0xff424242;
    private final static int PRIMARY_TEXT_COLOR_DEFAULT_DARK = 0xfff5f5f5;
    private final static int BACKGROUND_COLOR_LIGHT = 0xfffefefe;
    private final static int BACKGROUND_COLOR_DARK = 0xff212121;
    private final static int SECONDARY_TEXT_COLOR_LIGHT = 0xff616161;
    private final static int SECONDARY_TEXT_COLOR_DARK = 0xffeeeeee;

    private void applyMaterialYouTheme(int type) {
        int color = 0;
        switch(type) {
            case LINE_NUMBER:
            case LINE_NUMBER_CURRENT:
                color = getMaterialColor(com.google.android.material.R.attr.colorAccent);
                break;
            case LINE_NUMBER_BACKGROUND:
            case LINE_DIVIDER:
                color = getMaterialColor(com.google.android.material.R.attr.colorSurface);
                break;
            case STATIC_SPAN_BACKGROUND:
            case WHOLE_BACKGROUND:
            case LINE_NUMBER_PANEL_TEXT:
            case COMPLETION_WND_BACKGROUND:
            case COMPLETION_WND_CORNER:
                color = getMaterialColor(com.google.android.material.R.attr.colorSurface);
                break;
            case OPERATOR:
                color = 0xFF0066D6;
                break;
            case STATIC_SPAN_FOREGROUND:
            case TEXT_NORMAL:
                color = getMaterialColor(com.google.android.material.R.attr.colorOnSurface);
                break;
            case SELECTION_INSERT:
                color = 0xdd536dfe;
                break;
            case UNDERLINE:
                color = getMaterialColor(com.google.android.material.R.attr.colorError);
                break;
            case SELECTION_HANDLE:
                color = 0xff536dfe;
                break;
            case ANNOTATION:
            case SIGNATURE_TEXT_HIGHLIGHTED_PARAMETER:
            case IDENTIFIER_NAME:
                color = 0xFF03A9F4;
                break;
            case CURRENT_LINE:
                color = 0x10000000;
                break;
            case SELECTED_TEXT_BACKGROUND:
            case FUNCTION_CHAR_BACKGROUND_STROKE:
                color = 0x2D3F51B5;
                break;
            case KEYWORD:
                color = 0xFF2196F3;
                break;
            case COMMENT:
                color = getMaterialColor(com.google.android.material.R.attr.colorOutline);
                break;
            case LITERAL:
                color = 0xFF008080;
                break;
            case SCROLL_BAR_THUMB:
                color = 0xffd8d8d8;
                break;
            case SCROLL_BAR_THUMB_PRESSED:
                color = 0xFF27292A;
                break;
            case BLOCK_LINE:
                color = 0xffdddddd;
                break;
            case LINE_BLOCK_LABEL:
            case SCROLL_BAR_TRACK:
            case TEXT_SELECTED:
            case STRIKETHROUGH:
                color = 0;
                break;
            case LINE_NUMBER_PANEL:
                color = 0xdd000000;
                break;
            case BLOCK_LINE_CURRENT:
            case SIDE_BLOCK_LINE:
                color = 0xff999999;
                break;
            case IDENTIFIER_VAR:
                color = 0xff546e7a;
                break;
            case FUNCTION_NAME:
                color = 0xffe040fb;
                break;
            case MATCHED_TEXT_BACKGROUND:
                color = 0xffffff00;
                break;
            case NON_PRINTABLE_CHAR:
                color = 0xeecccccc;
                break;
            case PROBLEM_ERROR:
                color = 0xaaff0000;
                break;
            case PROBLEM_WARNING:
                color = 0xaafff100;
                break;
            case PROBLEM_TYPO:
                color = 0x6600ff11;
                break;
            case HIGHLIGHTED_DELIMITERS_FOREGROUND:
                color = 0xdd000000;
                break;
            case HIGHLIGHTED_DELIMITERS_UNDERLINE:
                color = 0xff3f51b5;
                break;
            case HIGHLIGHTED_DELIMITERS_BACKGROUND:
                color = 0x1D000000;
                break;
            case COMPLETION_WND_TEXT_PRIMARY:
            case COMPLETION_WND_TEXT_SECONDARY:
            case TEXT_INLAY_HINT_FOREGROUND:
                color = isDark() ? 0xffffffff : 0xff000000;
                break;
            case COMPLETION_WND_ITEM_CURRENT:
                color = 0xffeeeeee;
                break;
            case SNIPPET_BACKGROUND_EDITING:
                color = 0xffcccccc;
                break;
            case SNIPPET_BACKGROUND_RELATED:
                color = 0xaadddddd;
                break;
            case SNIPPET_BACKGROUND_INACTIVE:
                color = 0x66dddddd;
                break;
            case SIGNATURE_TEXT_NORMAL:
                color = isDark() ? 0xffeeeeee : 0xff000000;
                break;
            case STICKY_SCROLL_DIVIDER:
                color = isDark() ? 0x99eeeeee : 0x99000000;
                break;
            case TEXT_INLAY_HINT_BACKGROUND:
                color = isDark() ? 0xffeeeeee : 0x1D000000;
                break;
            case HARD_WRAP_MARKER:
                color = !isDark() ? 0xffeeeeee : 0x1D000000;
                break;
            case DIAGNOSTIC_TOOLTIP_BRIEF_MSG:
                color = isDark() ? PRIMARY_TEXT_COLOR_DEFAULT_DARK : PRIMARY_TEXT_COLOR_DEFAULT_LIGHT;
                break;
            case DIAGNOSTIC_TOOLTIP_DETAILED_MSG:
                color = isDark() ? SECONDARY_TEXT_COLOR_DARK : SECONDARY_TEXT_COLOR_LIGHT;
                break;
            case SIGNATURE_BACKGROUND:
            case DIAGNOSTIC_TOOLTIP_BACKGROUND:
                color = isDark() ? BACKGROUND_COLOR_DARK : BACKGROUND_COLOR_LIGHT;
                break;
            case DIAGNOSTIC_TOOLTIP_ACTION:
                color = 0xff42A5F5;
        }
        setColor(type, color);
    }


    @ColorInt
    private Integer getMaterialColor(@AttrRes int attrId) {
        return MaterialColors.getColorOrNull(context, attrId);
    }

    private void applyMaterialYouThemeA() {
        setColor(LINE_DIVIDER, Color.TRANSPARENT);

        setColor(SELECTION_INSERT, Color.parseColor("#ddbb88"));
        setColor(SELECTED_TEXT_BACKGROUND, Color.parseColor("#770811"));
        setColor(NON_PRINTABLE_CHAR, Color.parseColor("#103050"));
        setColor(CURRENT_LINE, Color.parseColor("#082050"));

        setColor(WHOLE_BACKGROUND, Color.parseColor("#000c18"));
        setColor(LINE_NUMBER_BACKGROUND, Color.parseColor("#000c18"));

        setColor(LINE_NUMBER, Color.parseColor("#406385"));
        setColor(LINE_NUMBER_CURRENT, Color.parseColor("#80a2c2"));
        setColor(TEXT_NORMAL, Color.parseColor("#6688cc"));
        /*setColor(COMPLETION_WND_BACKGROUND, Color.parseColor(completionWindowBackground));
        setColor(COMPLETION_WND_ITEM_CURRENT, Color.parseColor(completionWindowBackgroundCurrent));
        setColor(HIGHLIGHTED_DELIMITERS_FOREGROUND, Color.parseColor(highlightedDelimetersForeground));
        setColor(DIAGNOSTIC_TOOLTIP_BACKGROUND, Color.parseColor(tooltipBackground));
        setColor(DIAGNOSTIC_TOOLTIP_BRIEF_MSG, Color.parseColor(tooltipBriefMessageColor));
        setColor(DIAGNOSTIC_TOOLTIP_DETAILED_MSG, Color.parseColor(tooltipDetailedMessageColor));
        setColor(DIAGNOSTIC_TOOLTIP_ACTION, Color.parseColor(tooltipActionColor));*/
        setColor(BLOCK_LINE, Color.parseColor("#002952"));
        setColor(BLOCK_LINE_CURRENT, Color.parseColor("#204972"));



    }

    @Override
    public boolean isDark() {
        var superIsDark = super.isDark();
        if (superIsDark) {
            return true;
        }
        if (currentTheme != null) {
            return currentTheme.isDark();
        }
        return false;
    }

    @Override
    public int getColor(int type) {
        if (type >= 255) {
            // Cache colors in super class
            var superColor = super.getColor(type);
            if (superColor == 0) {
                if (theme != null) {
                    String color = theme.getColor(type - 255);
                    var newColor = color != null ? Color.parseColor(color) : super.getColor(TEXT_NORMAL);
                    super.colors.put(type, newColor);
                    return newColor;
                }
                return super.getColor(TEXT_NORMAL);
            } else {
                return superColor;
            }
        }
        return super.getColor(type);
    }

    @Override
    public void detachEditor(@NonNull CodeEditor editor) {
        super.detachEditor(editor);
        themeRegistry.removeListener(this);
    }

    @Override
    public void attachEditor(@NonNull CodeEditor editor) {
        super.attachEditor(editor);
        try {
            themeRegistry.loadTheme(currentTheme);
        } catch (Exception e) {
            //throw new RuntimeException(e);
        }
        setTheme(currentTheme);
    }


    @Deprecated
    public IThemeSource getThemeSource() {
        return themeSource;
    }
}
