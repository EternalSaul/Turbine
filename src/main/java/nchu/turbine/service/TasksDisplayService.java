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
 * ������ʾ�������ʵ��
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
@Component
public class TasksDisplayService extends BaseService implements ITasksDisplayService{
	
	@Autowired
	@Qualifier("downloadingTaskPanels")
	Vector<DownloadingTaskPanel> downloadingTasks;		//�������������б�������
	
	@Autowired
	@Qualifier("completedTaskPanels")
	Vector<CompletedTaskPanel> completedTasks;		//����������б�������
	
	
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
		 * 1.����BodyPanel����Ҫ�Ĵ�С�����380�̶����䣬�߶���     �������*101
		 * */
		
		//2.�������ص��������
		int downloadingTaskNum=downloadingTasks.size();
		
		jPanel.setPreferredSize(new Dimension(380, downloadingTaskNum*120));
		jPanel.setLayout(new GridLayout((downloadingTaskNum>3?downloadingTaskNum:3), 1, 0, 20));
		
		//3.�Ƴ���ǰ����ͼ
		jPanel.removeAll();
		
		//4.�����µ���ͼ
		for(DownloadingTaskPanel taskPanel:downloadingTasks){
			jPanel.add(taskPanel);
		}
		
		//5.ˢ�½���
		jPanel.validate();
		jPanel.repaint();
		
	}

	@Override
	public DownloadingTaskPanel addDownloadingTasks(String nameLab, String size, int num, Client client, long createTime,File saveDirectory,File torrent) {
		
		//1.����	 DownloadingTaskPanel
		DownloadingTaskPanel taskPanel=new DownloadingTaskPanel(nameLab, size, num, client, createTime,saveDirectory,torrent);
		
		//2.��ӵ�������������������
		downloadingTasks.add(taskPanel);
		
		///�־û�
		dtd.save(downloadingTasks);
		
		//ˢ���������������
		displayDownloadingTasks();
		
		//���������߳�
		client.addObserver(new DownloadingObserver(taskPanel));
		
		return taskPanel;
		
	}

	@Override
	public void removeDownloadingTasks(DownloadingTaskPanel taskPanel) {
		taskPanel.getClient().stop();
		
		//1.�������������������飬�Ƴ�Ŀ��
		downloadingTasks.remove(taskPanel);
		
		///�־û�
		dtd.save(downloadingTasks);
		
		//2.ˢ���������������
		displayDownloadingTasks();
	}

	@Override
	public CompletedTaskPanel addCompletedTasks(String cLabname,String csize,String ccompleteTime,File file) {
		//1.����	 CompletedTaskPanel 
		CompletedTaskPanel completedTaskPanel= new CompletedTaskPanel(cLabname, csize, ccompleteTime, file);
		//if()
		//2.��ӵ��Ѿ��������������
		completedTasks.add(completedTaskPanel);
		
		///�־û�
		ctd.save(completedTasks);
		
		//ˢ��������������
		displayCompletedTasks();
		
		return completedTaskPanel;
		
	}

	@Override
	public void removeCompletedTasks(CompletedTaskPanel completedTaskPanel) {
		
		//1.�������������������飬�Ƴ�Ŀ��
		completedTasks.remove(completedTaskPanel);
		
		///�־û�
		ctd.save(completedTasks);
		
		//2.ˢ���������������
		displayCompletedTasks();
	}
	
	@Override
	public void displayCompletedTasks() {
		
				if(completedPanel==null)	 completedPanel=(IListBodyPanel) TurbineView.getContext().getBean(cp);
				
				JPanel jPanel=completedPanel.getBodyPanel();
				/*
				 * 1.����BodyPanel����Ҫ�Ĵ�С�����380�̶����䣬�߶���     �������*101
				 * */
				
				//2.�Ѿ���ɵ��������
				int completedTaskNum=completedTasks.size();
				
				jPanel.setPreferredSize(new Dimension(380, completedTaskNum*120));
				jPanel.setLayout(new GridLayout((completedTaskNum>3?completedTaskNum:3), 1, 0, 20));
				
				//3.�Ƴ���ǰ����ͼ
				jPanel.removeAll();
				
				//4.�����µ���ͼ
				for(CompletedTaskPanel completedTaskPanel:completedTasks){
					jPanel.add(completedTaskPanel);
				}
				
				//5.ˢ�½���
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
		
		//�Ƴ���ǰ����ͼ
		filesDisplay.removeAll();
		
		JLabel jLabel=new JLabel("�Ѽ�⵽���Ӱ��������ļ�:");
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
