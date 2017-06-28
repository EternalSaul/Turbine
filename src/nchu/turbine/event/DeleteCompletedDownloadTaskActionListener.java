package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.TurbineView;

/**
 * ɾ������������¼�
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class DeleteCompletedDownloadTaskActionListener implements ActionListener{
	/**
	 * ��Ӧ�����������
	 */
	CompletedTaskPanel taskPanel;
	
	/**
	 * @param taskPanel		���¼������Ӧ������
	 * </br>EditDate: 2017-06-24
	 */
	public DeleteCompletedDownloadTaskActionListener(CompletedTaskPanel taskPanel) {
		super();
		this.taskPanel = taskPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new Thread(new Runnable() {
			public void run() {
				ITasksDisplayService displayService=(ITasksDisplayService) TurbineView.getContext().getBean("tasksDisplayService");
				displayService.removeCompletedTasks(taskPanel);
				//����ϵͳ����ɾ���ļ�
			}
		}).start();
	}
}
