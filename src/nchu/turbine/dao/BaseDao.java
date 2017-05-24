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
 * Dao���࣬Ϊ�˼��ϲ㣬���븴��
 * @author Saulxk
 * @param <T>
 * </br>EditDate: 2017-05-23
 */
public class BaseDao<T> {
	
	/**
	 * ȡ���־û��Ķ���
	 * @param diretory		�־û��ļ�·��
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
				if(ts==null){ System.out.println("�쳣������˿�"); ts=new Vector<T>();}
			}
		}else{
			System.out.println("û���ļ�������˿�");
			ts=new Vector<T>();
		}
		System.out.println("ȡ��������");
		return ts;
	}
	
	/**
	 * �־û�����
	 * @param ts		�־û���������
	 * @param diretory	�־û��ļ�·��
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
