package io.github.tundeadetunji;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public final class Machine {
    public static Object getTextFromClipboard() throws IOException, UnsupportedFlavorException {
        return Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);
    }

    public static void sendTextToClipboard(String text) {
        Toolkit.getDefaultToolkit()
                .getSystemClipboard()
                .setContents(
                        new StringSelection(text),
                        null
                );
    }

}
