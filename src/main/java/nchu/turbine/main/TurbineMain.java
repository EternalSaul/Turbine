package nchu.turbine.main;

import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.view.MainFrame;
import nchu.turbine.view.TurbineView;

import java.awt.EventQueue;

public class TurbineMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window=(MainFrame) TurbineView.getContext().getBean("mainFrame");
					ITasksDisplayService service= (ITasksDisplayService) TurbineView.getContext().getBean("tasksDisplayService");
					service.displayCompletedTasks();
					service.displayDownloadingTasks();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
