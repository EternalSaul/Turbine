package nchu.turbine.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Dao基类，为了简化上层，代码复用
 * @author Saulxk
 * @param <T>
 * </br>EditDate: 2017-05-23
 */
public class BaseDao<T> {
	
	/**
	 * 取出持久化的对象
	 * @param diretory		持久化文件路径
	 * @return
	 * </br>EditDate: 2017-05-23
	 */
	Vector<T> find(String diretory) {
		Vector<T> ts=null;
		File file=new File(diretory);
		if(file.exists()){
			try {
				FileInputStream fos=new FileInputStream(file);
				ObjectInputStream os=new ObjectInputStream(fos);
				ts=(Vector<T>) os.readObject();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(ts==null){ System.out.println("异常，输出了空"); ts=new Vector<T>();}
			}
		}else{
			System.out.println("没有文件，输出了空");
			ts=new Vector<T>();
		}
		System.out.println("取出了数据");
		return ts;
	}
	
	/**
	 * 持久化操作
	 * @param ts		持久化对象向量
	 * @param diretory	持久化文件路径
	 * @throws IOException
	 * </br>EditDate: 2017-05-23
	 */
	void save(Vector<T> ts,String diretory) throws IOException{
		FileOutputStream fos=new FileOutputStream(diretory);
		ObjectOutputStream os=new ObjectOutputStream(fos);
		os.writeObject(ts);
		os.close();
	}
}
