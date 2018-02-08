package nchu.turbine.dao;

import java.io.IOException;
import java.util.Vector;

import javax.swing.filechooser.FileSystemView;

import org.springframework.stereotype.Component;

import nchu.turbine.event.DeleteCompletedDownloadTaskActionListener;
import nchu.turbine.event.DeleteDownloadingTaskActionListener;
import nchu.turbine.event.OpenCompletedDownloadTaskActionListener;
import nchu.turbine.interfaces.dao.ICompletedTaskDao;
import nchu.turbine.view.CompletedTaskPanel;

@Component
public class CompletedTaskDao extends BaseDao<CompletedTaskPanel> implements ICompletedTaskDao{
	final static String diretory=FileSystemView.getFileSystemView().getDefaultDirectory()+"/Turbine/turbineC.tur";
	@Override
	public Vector<CompletedTaskPanel> find() {
		Vector<CompletedTaskPanel> vector=super.find(diretory);
		for(CompletedTaskPanel taskPanel:vector){
			taskPanel.getOpenFile().addActionListener(new OpenCompletedDownloadTaskActionListener(taskPanel.getSaveDirectory()));
			taskPanel.getDeleteFile().addActionListener(new DeleteCompletedDownloadTaskActionListener(taskPanel));
		}
		return vector;
	}

	@Override
	public void save(Vector<CompletedTaskPanel> ts) {
		try {
			System.out.println(ts.size());
			super.save(ts, diretory);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
