package nchu.turbine.interfaces.service;

import java.io.File;

import com.turn.ttorrent.client.Client;

import nchu.turbine.view.DownloadingTaskPanel;

public interface IDownloadService {

	void startdownload(File torrentfile, File directory);

	void startdownload(Client client, DownloadingTaskPanel taskPanel, File directory);

}
