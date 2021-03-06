package ariafx.tray;

import java.awt.MenuItem;
import java.util.function.BiConsumer;

import ariafx.gui.fxml.control.DownUi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.stage.Stage;

public class TrayUtile {

	static TrayIcon tray;
	// static FxTray tray;
	public static Stage stage;

	static ObservableMap<DownUi, MenuItem> list = FXCollections.observableHashMap();
	// static ObservableMap<DownUi, javafx.scene.control.MenuItem> list =
	// FXCollections.observableHashMap();

	public static void InitTray() {
		// if(PlatformUtil.isMac()){
		try {
			tray = new TrayIcon();
		} catch (UnsupportedOperationException e) {
			System.err.println(e.getMessage());
		}
		// }else{
		// tray = new FxTray();
		// tray.initializeFX();
		// tray.addMain();
		// tray.addMainMouseListener();
		// tray.addList();
		// }

	}

	public static void addTListTray(DownUi downUi) {
		if (list.get(downUi) != null) {
			return;
		}
		// if(PlatformUtil.isMac()){
		// tray.addDownUiToList(downUi);
		if (tray != null) {
			MenuItem item = tray.addtMenuitem(downUi.getFilename(), (e) -> {
				downUi.show();

			});
			list.put(downUi, item);
		} 

		// }
	}

	public static void removeFromListTray(DownUi downUi) {
		if (tray != null) {
			MenuItem item = list.remove(downUi);
			tray.removeMenuitemList(item);
		}
	}

	public static void showAll() {
		list.forEach(new BiConsumer<DownUi, MenuItem>() {
			@Override
			public void accept(DownUi t, MenuItem u) {
				t.show();
			}
		});
	}

}
