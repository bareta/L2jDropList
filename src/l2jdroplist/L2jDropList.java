package l2jdroplist;

import Telas.DropList;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;

public class L2jDropList {


    public static void main(String[] args) {
        NativeInterface.open();
        NativeInterface.getConfiguration().setNativeSideRespawnedOnError(false);
           
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DropList().setVisible(true);
                //new NewJFrame().setVisible(true);
            }
        });
        NativeInterface.runEventPump();
    }
}
