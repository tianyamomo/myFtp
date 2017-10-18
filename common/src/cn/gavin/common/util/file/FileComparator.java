package cn.gavin.common.util.file;

import java.io.File;
import java.util.Comparator;

/****
 * File的Comparator类
 * @version 0.1
 * @author gavin.jiang
 * @date 2017/03/16
 */
public class FileComparator implements Comparator<File>{

	
	
	/*******
	 * 将文件进行排序，文件在前，文件夹在后，按照文件名进行排序
	 */
	@Override
	public int compare(File o1, File o2) { 
		if(o1.isDirectory() && o2.isFile()){
			return -1;
		}
		if(o1.isFile() && o2.isDirectory()){
			return 1;
		}
		return o1.getName().compareTo(o2.getName());
	}

}
