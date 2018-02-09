package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.turn.ttorrent.client.Client;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.DownloadingTaskPanel;
import nchu.turbine.view.TurbineView;

/**
 * 删除正在下载任务事件
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class DeleteDownloadingTaskActionListener implements ActionListener{
	/**
	 * BT下载任务客户对象
	 */
	Client client;
	/**
	 * 该事件对应的正在下载任务
	 */
	DownloadingTaskPanel taskPanel;
	
	/**
	 * @param client		该事件对象对应的正在下载任务的BT客户对象
	 * @param taskPanel		该事件对象对应的正在下载任务对象
	 * </br>EditDate: 2017-06-24
	 */
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
