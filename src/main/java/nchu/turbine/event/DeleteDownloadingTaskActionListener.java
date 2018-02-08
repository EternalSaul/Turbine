package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.turn.ttorrent.client.Client;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.DownloadingTaskPanel;
import nchu.turbine.view.TurbineView;

/**
 * ɾ���������������¼�
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class DeleteDownloadingTaskActionListener implements ActionListener{
	/**
	 * BT��������ͻ�����
	 */
	Client client;
	/**
	 * ���¼���Ӧ��������������
	 */
	DownloadingTaskPanel taskPanel;
	
	/**
	 * @param client		���¼������Ӧ���������������BT�ͻ�����
	 * @param taskPanel		���¼������Ӧ�����������������
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
