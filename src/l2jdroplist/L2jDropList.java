package l2jdroplist;

import Telas.DropList;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import de.javasoft.plaf.synthetica.SyntheticaLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;
import javax.swing.UIManager;

public class L2jDropList {


    public static void main(String[] args) {
        NativeInterface.open();
        NativeInterface.getConfiguration().setNativeSideRespawnedOnError(false);
        
        UIManager.put ("FileChooser.lookInLabelText", "Consulte:");   
        UIManager.put ("FileChooser.lookInLabelMnemonic", "o");   
        UIManager.put ("FileChooser.fileNameLabelText", "Nome do arquivo:");   
        UIManager.put ("FileChooser.fileNameLabelMnemonic", "N");   
        UIManager.put ("FileChooser.filesOfTypeLabelText", "Arquivos do tipo:");   
        UIManager.put ("FileChooser.filesOfTypeLabelMnemonic", "t");   
        UIManager.put ("FileChooser.upFolderToolTipText", "Um Nível Acima");   
        UIManager.put ("FileChooser.upFolderAccessibleName", "Para Cima");   
        UIManager.put ("FileChooser.homeFolderToolTipText", "Inicio");   
        UIManager.put ("FileChooser.homeFolderAccessibleName", "Inicio");   
        UIManager.put ("FileChooser.newFolderToolTipText", "Criar uma Nova Pasta");   
        UIManager.put ("FileChooser.newFolderAccessibleName", "Nova Pasta");   
        UIManager.put ("FileChooser.listViewButtonToolTipText", "Lista");   
        UIManager.put ("FileChooser.listViewButtonAccessibleName", "Lista");   
        UIManager.put ("FileChooser.detailsViewButtonToolTipText", "Detalhes");   
        UIManager.put ("FileChooser.detailsViewButtonAccessibleName", "Detalhes");         
        UIManager.put ("FileChooser.cancelButtonText", "Cancelar");   
        UIManager.put ("FileChooser.cancelButtonMnemonic", "C");   
        UIManager.put("FileChooser.cancelButtonToolTipText","Cancelar");  
        UIManager.put ("FileChooser.openButtonText", "Abrir");   
        UIManager.put ("FileChooser.openButtonMnemonic", "A");   
        UIManager.put("FileChooser.saveButtonText","Salvar");  
        UIManager.put("FileChooser.saveButtonToolTipText","Salvar Arquivo");
        UIManager.put("FileChooser.saveInLabelText","Salvar em:");      
      
        final String[] li = { "Licensee=RapidShare AG", "LicenseRegistrationNumber=102001", "Product=Synthetica", "LicenseType=Enterprise Site License", "ExpireDate=--.--.----", "MaxVersion=2.999.999" };
        UIManager.put("Synthetica.license.info", li);
        UIManager.put("Synthetica.button.hover.animation.type",0);

        UIManager.put("Synthetica.text.antialias", false);
        UIManager.put("Synthetica.font.respectSystemDPI", true);
        UIManager.put("Synthetica.animation.enabled", false);

        UIManager.put("Synthetica.opaque",false);
        UIManager.put("Synthetica.license.key", "075DD94D-50F2C1E8-B7628175-3031CB2D-F8E8033E");
        UIManager.put("Synthetica.extendedFileChooser.rememberPreferences", Boolean.FALSE);
        SyntheticaLookAndFeel.setLookAndFeel(SyntheticaSimple2DLookAndFeel.class.getName());

   
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
