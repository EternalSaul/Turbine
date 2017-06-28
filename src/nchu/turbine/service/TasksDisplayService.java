package nchu.turbine.service;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.Client.ClientShutdown;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.client.storage.FileCollectionStorage;
import com.turn.ttorrent.client.storage.FileStorage;

import nchu.turbine.interfaces.dao.ICompletedTaskDao;
import nchu.turbine.interfaces.dao.IDownloadingTaskDao;
import nchu.turbine.interfaces.service.ITasksDisplayService;
import nchu.turbine.thread.DownloadingObserver;
import nchu.turbine.view.CompletedTaskPanel;
import nchu.turbine.view.DownloadingTaskPanel;
import nchu.turbine.view.IListBodyPanel;
import nchu.turbine.view.SecondPanel;
import nchu.turbine.view.ThirdPanel;
import nchu.turbine.view.TurbineView;

/**
 * 任务显示服务对象实现
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
@Component
public class TasksDisplayService extends BaseService implements ITasksDisplayService{
	
	@Autowired
	@Qualifier("downloadingTaskPanels")
	Vector<DownloadingTaskPanel> downloadingTasks;		//正在下载任务列表向量组
	
	@Autowired
	@Qualifier("completedTaskPanels")
	Vector<CompletedTaskPanel> completedTasks;		//已完成任务列表向量组
	
	
	@Value("${downloadingPanel}")
	String dp;
	
	@Value("${completedPanel}")		 
	String cp;
	
	IListBodyPanel downloadingPanel;
	IListBodyPanel completedPanel;
	
	@Autowired
	ICompletedTaskDao ctd;
	@Autowired
	IDownloadingTaskDao dtd;
	
	@Override
	public void displayDownloadingTasks() {
		if(downloadingPanel==null)	downloadingPanel=(IListBodyPanel) TurbineView.getContext().getBean(dp);
		
		JPanel jPanel=downloadingPanel.getBodyPanel();
		/*
		 * 1.计算BodyPanel所需要的大小，宽度380固定不变，高度是     任务个数*101
		 * */
		
		//2.正在下载的任务个数
		int downloadingTaskNum=downloadingTasks.size();
		
		jPanel.setPreferredSize(new Dimension(380, downloadingTaskNum*120));
		jPanel.setLayout(new GridLayout((downloadingTaskNum>3?downloadingTaskNum:3), 1, 0, 20));
		
		//3.移除以前的视图
		jPanel.removeAll();
		
		//4.建立新的视图
		for(DownloadingTaskPanel taskPanel:downloadingTasks){
			jPanel.add(taskPanel);
		}
		
		//5.刷新界面
		jPanel.validate();
		jPanel.repaint();
		
	}

	@Override
	public DownloadingTaskPanel addDownloadingTasks(String nameLab, String size, int num, Client client, long createTime,File saveDirectory,File torrent) {
		
		//1.创建	 DownloadingTaskPanel
		DownloadingTaskPanel taskPanel=new DownloadingTaskPanel(nameLab, size, num, client, createTime,saveDirectory,torrent);
		
		//2.添加到正在下载任务向量组
		downloadingTasks.add(taskPanel);
		
		///持久化
		dtd.save(downloadingTasks);
		
		//刷新正在下载任务表
		displayDownloadingTasks();
		
		//创建监视线程
		client.addObserver(new DownloadingObserver(taskPanel));
		
		return taskPanel;
		
	}

	@Override
	public void removeDownloadingTasks(DownloadingTaskPanel taskPanel) {
		taskPanel.getClient().stop();
		
		//1.从正在下载任务向量组，移除目标
		downloadingTasks.remove(taskPanel);
		
		///持久化
		dtd.save(downloadingTasks);
		
		//2.刷新正在下载任务表
		displayDownloadingTasks();
	}

	@Override
	public CompletedTaskPanel addCompletedTasks(String cLabname,String csize,String ccompleteTime,File file) {
		//1.创建	 CompletedTaskPanel 
		CompletedTaskPanel completedTaskPanel= new CompletedTaskPanel(cLabname, csize, ccompleteTime, file);
		//if()
		//2.添加到已经完成任务向量组
		completedTasks.add(completedTaskPanel);
		
		///持久化
		ctd.save(completedTasks);
		
		//刷新下载完成任务表
		displayCompletedTasks();
		
		return completedTaskPanel;
		
	}

	@Override
	public void removeCompletedTasks(CompletedTaskPanel completedTaskPanel) {
		
		//1.从正在下载任务向量组，移除目标
		completedTasks.remove(completedTaskPanel);
		
		///持久化
		ctd.save(completedTasks);
		
		//2.刷新正在下载任务表
		displayCompletedTasks();
	}
	
	@Override
	public void displayCompletedTasks() {
		
				if(completedPanel==null)	 completedPanel=(IListBodyPanel) TurbineView.getContext().getBean(cp);
				
				JPanel jPanel=completedPanel.getBodyPanel();
				/*
				 * 1.计算BodyPanel所需要的大小，宽度380固定不变，高度是     任务个数*101
				 * */
				
				//2.已经完成的任务个数
				int completedTaskNum=completedTasks.size();
				
				jPanel.setPreferredSize(new Dimension(380, completedTaskNum*120));
				jPanel.setLayout(new GridLayout((completedTaskNum>3?completedTaskNum:3), 1, 0, 20));
				
				//3.移除以前的视图
				jPanel.removeAll();
				
				//4.建立新的视图
				for(CompletedTaskPanel completedTaskPanel:completedTasks){
					jPanel.add(completedTaskPanel);
				}
				
				//5.刷新界面
				jPanel.validate();
				jPanel.repaint();
	}
	
	@Override
	public void displayTorrentFilesList(JPanel filesDisplay,List<String> files){
		int num=files.size()+1;
		System.out.println(num);
		System.out.println((num>6?num:6));
		
//		filesDisplay.setPreferredSize(new Dimension(414,num*18));
//		filesDisplay.setSize(414, 121);
//		filesDisplay.setLayout(new GridLayout(6,1,0,2));
		
		filesDisplay.setSize(414, num*20);
		filesDisplay.setLayout(new GridLayout((num>6?num:6),1,0,2));
		
		//移除以前的视图
		filesDisplay.removeAll();
		
		JLabel jLabel=new JLabel("已检测到种子包含以下文件:");
		jLabel.setPreferredSize(new Dimension(400, 20));
		filesDisplay.add(jLabel);
		
		int i=0;
		for(String file:files){
//			if(++i>4){ 
//				jLabel=new JLabel("...");
//				jLabel.setPreferredSize(new Dimension(400, 20));
//				filesDisplay.add(jLabel);
//				break;
//			}
			jLabel=new JLabel(file);
			jLabel.setPreferredSize(new Dimension(400, 20));
			filesDisplay.add(jLabel);
		}

		
		
		filesDisplay.validate();
		filesDisplay.repaint();
	}
	
	@Override
	public void removeTorrentFilesList(JPanel filesDisplay){
		filesDisplay.removeAll();
		filesDisplay.validate();
		filesDisplay.repaint();
	}
}
