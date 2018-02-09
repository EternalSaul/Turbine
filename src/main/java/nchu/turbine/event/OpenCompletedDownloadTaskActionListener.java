package nchu.turbine.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * 打开已完成的任务保存路径事件
 * @author Saulxk
 * </br>EditDate: 2017-06-24
 */
public class OpenCompletedDownloadTaskActionListener implements ActionListener{
	/**
	 * 保存的文件路径
	 */
	File saveDirectory;
	
	/**
	 * @param saveDirectory	文件路径
	 * </br>EditDate: 2017-06-24
	 */
	public OpenCompletedDownloadTaskActionListener(File saveDirectory) {
		super();
		this.saveDirectory = saveDirectory;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//点击后，打开该文件所在地址。
		try {
			Runtime.getRuntime().exec("explorer.exe "+saveDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
