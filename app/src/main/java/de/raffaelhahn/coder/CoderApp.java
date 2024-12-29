package de.raffaelhahn.coder;

import static android.Manifest.permission.MANAGE_EXTERNAL_STORAGE;

import android.app.Application;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.eclipse.tm4e.core.registry.IThemeSource;

import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver;

public class CoderApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();


        FileProviderRegistry.getInstance().addFileProvider(new AssetsFileResolver(getAssets()));
        ThemeRegistry themeRegistry = ThemeRegistry.getInstance();


        String themePath = "textmate/abyss.json";
        ThemeModel themeModel = new ThemeModel(IThemeSource.fromInputStream(FileProviderRegistry.getInstance().tryGetInputStream(themePath), themePath, null), "Abyss");
        try {
            themeRegistry.loadTheme(themeModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        themeRegistry.setTheme("Abyss");

        GrammarRegistry.getInstance().loadGrammars("textmate/languages.json");


    }


}
