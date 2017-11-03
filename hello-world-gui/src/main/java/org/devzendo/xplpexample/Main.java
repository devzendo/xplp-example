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

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Hello World!\n");
        sb.append("java.version is " + System.getProperty("java.version") + "\n");

        try {
            SwingUtilities.invokeAndWait(() -> {
                final JFrame frame = new JFrame("Cross-Platform Launcher GUI example");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                final JPanel panel = new JPanel(new BorderLayout());
                frame.add(panel, BorderLayout.CENTER);
                final JTextArea label = new JTextArea(10, 50);
                label.setText(sb.toString());
                panel.add(label, BorderLayout.NORTH);
                frame.pack();

                frame.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
