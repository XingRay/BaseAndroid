package com.ray.lib.java.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

/**
 * Created by leixing on 2016/10/22.
 */
public class ViewUtil {
    private ViewUtil() {
        throw new UnsupportedOperationException();
    }

    public static void setToSystemLookAndFeel() {
        if(UIManager.getLookAndFeel().isSupportedLookAndFeel()){
            final String platform = UIManager.getSystemLookAndFeelClassName();
            if (!UIManager.getLookAndFeel().getName().equals(platform)) {
                try {
                    UIManager.setLookAndFeel(platform);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public static void setToCenterOfWindow(Component component){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = component.getSize();
        int x = (int)(screenSize.getWidth()-size.getWidth())/2;
        int y = (int)(screenSize.getHeight()-size.getHeight())/2;
        component.setLocation(x, y);
    }
}
