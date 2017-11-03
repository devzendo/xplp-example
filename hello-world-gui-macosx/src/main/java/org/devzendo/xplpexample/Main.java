/*
 * Copyright (C) 2008-2017 Matt Gumbley, DevZendo.org http://devzendo.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.devzendo.xplpexample;

import com.apple.eawt.Application;
import org.devzendo.commonapp.gui.Beautifier;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        final String javaLibraryPath = System.getProperty("java.library.path");
        final StringBuilder sb = new StringBuilder();
        sb.append("Hello World!\n");
        sb.append("java.version is " + System.getProperty("java.version") + "\n");
        sb.append("java.library.path is '" + javaLibraryPath + "'\n");
        boolean found = false;
        final String[] paths = javaLibraryPath.split(":");
        for (final String path : paths) {
            final File quaqua = new File(path, "libquaqua.jnilib");
            final boolean exists = quaqua.exists();
            sb.append(quaqua.getAbsolutePath() + "? " + exists + "\n");
            found |= exists;
        }
        sb.append("Quaqua JNI library exists there (for Mac OS X)? " + found);

        try {
            SwingUtilities.invokeAndWait(() -> {
                Beautifier.makeBeautiful();

                final JFrame frame = new JFrame("Cross-Platform Launcher GUI example");
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                final JPanel panel = new JPanel(new BorderLayout());
                frame.add(panel, BorderLayout.CENTER);
                final JTextArea label = new JTextArea(10, 50);
                label.setText(sb.toString());
                panel.add(label, BorderLayout.NORTH);
                frame.pack();

                final Application application = Application.getApplication();
                application.setQuitHandler((quitEvent, quitResponse) -> SwingUtilities.invokeLater(() -> {
                    frame.dispose();
                    System.exit(0);
                }));

                frame.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
