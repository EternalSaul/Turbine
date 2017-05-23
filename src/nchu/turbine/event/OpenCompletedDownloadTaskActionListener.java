package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class OpenCompletedDownloadTaskActionListener implements ActionListener{
	File saveDirectory;
	
	public OpenCompletedDownloadTaskActionListener(File saveDirectory) {
		super();
		this.saveDirectory = saveDirectory;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//����󣬴򿪸��ļ����ڵ�ַ��
		try {
			Runtime.getRuntime().exec("explorer.exe "+saveDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
