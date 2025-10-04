package de.raffaelhahn.coder.editor;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import de.raffaelhahn.coder.R;
import io.github.rosemoe.sora.event.ContentChangeEvent;
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme;
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage;
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry;
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel;
import io.github.rosemoe.sora.widget.CodeEditor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CodeEditorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodeEditorFragment extends Fragment {

    private static final String ARG_FILE_PATH = "paramFilePath";

    private String path;

    private CodeEditor codeEditor;

    public CodeEditorFragment() {
        // Required empty public constructor
    }

    public static CodeEditorFragment newInstance(String filePath) {
        CodeEditorFragment fragment = new CodeEditorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FILE_PATH, filePath);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            path = getArguments().getString(ARG_FILE_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        codeEditor = view.findViewById(R.id.codeEditor);
        codeEditor.setTypefaceText(Typeface.MONOSPACE);
        codeEditor.setNonPrintablePaintingFlags(
                CodeEditor.FLAG_DRAW_WHITESPACE_LEADING | CodeEditor.FLAG_DRAW_LINE_SEPARATOR | CodeEditor.FLAG_DRAW_WHITESPACE_IN_SELECTION);
        try {
            codeEditor.setColorScheme(TextMateColorScheme.create(ThemeRegistry.getInstance()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        var languageScopeName = "text.html.basic"; // The scope name of target language
        var language = TextMateLanguage.create(
                languageScopeName, true /* true for enabling auto-completion */
        );
        codeEditor.setEditorLanguage(language);

        loadFile(path);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        codeEditor.release();
    }

    public void loadFile(String path) {
        long startA = System.currentTimeMillis();
        this.path = path;
        getArguments().putString(ARG_FILE_PATH, path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            codeEditor.setText(content);
            codeEditor.subscribeEvent(ContentChangeEvent.class, (event, unsubscribe) -> {
                if(path == null) {
                    return;
                }
                try {
                    Files.write(Paths.get(path), codeEditor.getText().toString().getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long endA = System.currentTimeMillis();
        Log.d("CodeEditorFragment","Time method: " + (endA - startA) + "ms");
    }
}