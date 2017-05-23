package nchu.turbine.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import nchu.turbine.event.DeleteCompletedDownloadTaskActionListener;
import nchu.turbine.event.OpenCompletedDownloadTaskActionListener;
import nchu.turbine.interfaces.service.ITasksDisplayService;

public class CompletedTaskPanel extends JPanel{
	JLabel Labname;//������
	JLabel size;//�����С
	JLabel completeTime;//���ʱ��
	File saveDirectory;//�ļ�·��ָ��
	JButton openFile;
	JButton deleteFile;
	/**
	 * @param cLabname				������
	 * @param csize					�����С
	 * @param ccompleteTime			�������ʱ��
	 * @param saveDirectory			�ļ�����·�������ڲ鿴ɾ��
	 * </br>EditDate: 2017-05-21
	 */
	public File getSaveDirectory() {
		return saveDirectory;
	}
	public CompletedTaskPanel(String cLabname,String csize,String ccompleteTime,File saveDirectory){
		this.saveDirectory=saveDirectory;
		init(cLabname, csize, ccompleteTime);
	}
	
	public JButton getOpenFile() {
		return openFile;
	}
	public void setOpenFile(JButton openFile) {
		this.openFile = openFile;
	}
	public JButton getDeleteFile() {
		return deleteFile;
	}
	public void setDeleteFile(JButton deleteFile) {
		this.deleteFile = deleteFile;
	}
	/**
	 * @param cLabname			������
	 * @param csize				�����С
	 * @param ccompleteTime		���ʱ��
	 * </br>EditDate: 2017-05-21
	 */
	public void init(String cLabname,String csize,String ccompleteTime){
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		this.setBounds(10, 23, 380, 100);
		
		JLabel label_1 = new JLabel("\u540D\u5B57\uFF1A");//����
		label_1.setBounds(10, 10, 54, 15);
		this.add(label_1);
		
		Labname = new JLabel(cLabname);
		Labname.setBounds(62, 10, 202, 15);
		this.add(Labname);
		
		size= new JLabel(csize);
		size.setForeground(Color.BLACK);
		size.setBounds(10, 35, 54, 15);
		this.add(size);
		
		openFile = new JButton("\u6253\u5F00\u6587\u4EF6");//���ļ���ť
		openFile.setForeground(Color.BLACK);
		openFile.setBounds(274, 6, 93, 23);
		this.add(openFile);
		openFile.addActionListener(new OpenCompletedDownloadTaskActionListener(saveDirectory));
		completeTime= new JLabel(ccompleteTime);
		completeTime.setBounds(62, 35, 202, 15);
		this.add(completeTime);
		
		deleteFile = new JButton("ɾ���ļ�");//ɾ���ļ�
		deleteFile.setBounds(274, 31, 93, 23);
		this.add(deleteFile);
		deleteFile.addActionListener(new DeleteCompletedDownloadTaskActionListener(this));
	}
	
}
