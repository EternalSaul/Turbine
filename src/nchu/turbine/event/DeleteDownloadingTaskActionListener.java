package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.turn.ttorrent.client.Client;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.DownloadingTaskPanel;
import nchu.turbine.view.TurbineView;

public class DeleteDownloadingTaskActionListener implements ActionListener{
	Client client;
	DownloadingTaskPanel taskPanel;
	
	public DeleteDownloadingTaskActionListener(Client client, DownloadingTaskPanel taskPanel) {
		this.client = client;
		this.taskPanel = taskPanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		new Thread(new Runnable() {
			public void run() {
				ITasksDisplayService displayService=(ITasksDisplayService) TurbineView.getContext().getBean("tasksDisplayService");
				displayService.removeDownloadingTasks(taskPanel);
				client.stop();
			}
		}).start();
	}
}
