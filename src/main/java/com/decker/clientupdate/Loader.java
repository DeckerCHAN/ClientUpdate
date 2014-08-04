package com.decker.clientupdate;

import com.decker.clientupdate.core.UpdateCore;

/**
 *
 * @author decker
 */
public class Loader {

    public static void main(String[] args) {
        try {

            UpdateCore.getInstance().process();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);

    }
}
