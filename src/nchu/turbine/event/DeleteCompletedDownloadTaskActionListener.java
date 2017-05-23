package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.TurbineView;

public class DeleteCompletedDownloadTaskActionListener implements ActionListener{
	CompletedTaskPanel taskPanel;
	
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
