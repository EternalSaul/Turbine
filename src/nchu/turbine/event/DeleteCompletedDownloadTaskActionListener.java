package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.TurbineView;

/**
 * 删除已完成任务事件
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class DeleteCompletedDownloadTaskActionListener implements ActionListener{
	/**
	 * 对应分已完成任务
	 */
	CompletedTaskPanel taskPanel;
	
	/**
	 * @param taskPanel		该事件对象对应的任务
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
				//操作系统级别删除文件
			}
		}).start();
	}
}
